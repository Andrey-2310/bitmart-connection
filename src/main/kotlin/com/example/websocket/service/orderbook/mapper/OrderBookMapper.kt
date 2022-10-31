package com.example.websocket.service.orderbook.mapper

import com.example.websocket.domain.Order
import com.example.websocket.domain.OrderBook
import com.example.websocket.dto.OrderBookData
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class OrderBookMapper {

    fun toOrderBook(orderBookData: OrderBookData) = orderBookData.run {
        OrderBook(
            asks = asks.map(::toOrder), // .sortedBy { it.price }) is not required as asks are sent in ASC order by BitMark,
            bids = bids.map(::toOrder), // .sortedByDescending { it.price }), is not required as bids are sent in DESC order by BitMark,
            timestamp = timestamp,
            instrument = instrument
        )
    }

    private fun toOrder(orderData: List<BigDecimal>) = Order(
        price = orderData[PRICE_ONDEX],
        quantity = orderData[QUANTITY_INDEX]
    )

    companion object {
        private val PRICE_ONDEX = 0
        private val QUANTITY_INDEX = 1
    }
}
