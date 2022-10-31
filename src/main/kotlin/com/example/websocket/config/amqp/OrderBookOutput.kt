//package com.example.websocket.config.amqp
//
//import org.springframework.cloud.stream.annotation.Output
//import org.springframework.messaging.MessageChannel
//
//interface OrderBookOutput {
//
//    @Output(ORDER_BOOK_OUTPUT)
//    fun sendOrderBook(): MessageChannel
//
//    @Output(ORDER_BOOK_CHANGE_OUTPUT)
//    fun sendOrderBookChange(): MessageChannel
//
//    companion object {
//        private const val ORDER_BOOK_OUTPUT = "orderBook"
//        private const val ORDER_BOOK_CHANGE_OUTPUT = "orderBookChange"
//
//    }
//}
