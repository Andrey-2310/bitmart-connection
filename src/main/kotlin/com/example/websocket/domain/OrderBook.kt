package com.example.websocket.domain

data class OrderBook(
    val asks: List<Order>,
    val bids: List<Order>,
    val timestamp: Long,
    val instrument: String,
)
