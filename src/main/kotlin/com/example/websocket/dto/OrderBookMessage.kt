package com.example.websocket.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class OrderBookMessage(
    val table: String,
    @JsonProperty("data")
    val orderBookData: List<OrderBookData>,
)
