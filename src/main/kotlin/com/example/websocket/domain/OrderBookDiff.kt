package com.example.websocket.domain

data class OrderBookDiff(
    val newAsks: Set<Order>,
    val removedAsks: Set<Order>,
    val newBids: Set<Order>,
    val removedBids: Set<Order>,
)
