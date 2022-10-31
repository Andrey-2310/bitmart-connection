package com.example.websocket.service.orderbook

import com.example.websocket.config.amqp.NotificationService
import com.example.websocket.domain.OrderBook
import com.example.websocket.domain.OrderBookDiff
import com.example.websocket.dto.OrderBookData
import com.example.websocket.service.orderbook.mapper.OrderBookMapper
import com.example.websocket.service.websocket.WebSocketClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class OrderBookManager(
    private val orderBookMapper: OrderBookMapper,
//    private val orderBookOutput: OrderBookOutput,
    private val notificationService: NotificationService
) {

    private val log = LoggerFactory.getLogger(WebSocketClient::class.java)

    private lateinit var orderBook: OrderBook
    private var i = -1

    fun refreshOrderBook(orderBookData: OrderBookData) {
        val newOrderBook = orderBookMapper.toOrderBook(orderBookData)

//        notificationService.sendNotification(orderBookOutput.sendOrderBook(), newOrderBook)
//        notificationService.sendNotification(orderBookOutput.sendOrderBookChange(), compareOrderBooks(newOrderBook))
        if (i == 0 || i == 50) {
            log.info("Best Bid Price ${getBestBidPrice()}")
            log.info("Best Ask Price to ${getBestAskPrice()}")
            log.info("7 Bid index Price to ${getBidByIndex(7)}")
            log.info("7 Ask index Price to ${getAskByIndex(7)}")
            log.info("Spread ${getSpread()}")
            log.info("Order Slice ${getOrderBookSlice(7)}")
            log.info("Subtract ${compareOrderBooks(newOrderBook)}")
        }

        orderBook = newOrderBook
//        log.info("Order Book refreshed to ${orderBook.timestamp}")
        i++
    }

    fun getBestBidPrice() = orderBook.bids.first().price

    fun getBestAskPrice() = orderBook.asks.first().price

    fun getBidByIndex(index: Int) = orderBook.bids[index]

    fun getAskByIndex(index: Int) = orderBook.asks[index]

    fun getSpread() = getBestAskPrice() - getBestBidPrice()

    fun getOrderBookSlice(depth: Int) = orderBook.run {
        OrderBook(
            asks = asks.subList(0, depth),
            bids = bids.subList(0, depth),
            timestamp = timestamp,
            instrument = instrument
        )
    }

    fun compareOrderBooks(newOrderBook: OrderBook) = OrderBookDiff(
        newAsks = newOrderBook.asks.subtract(orderBook.asks.toSet()),
        removedAsks = orderBook.asks.subtract(newOrderBook.asks.toSet()),
        newBids = newOrderBook.bids.subtract(orderBook.bids.toSet()),
        removedBids = orderBook.bids.subtract(newOrderBook.bids.toSet())
    )
}
