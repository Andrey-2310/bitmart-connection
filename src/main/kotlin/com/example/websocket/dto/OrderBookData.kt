package com.example.websocket.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class OrderBookData(
    val asks: List<List<BigDecimal>>,
    val bids: List<List<BigDecimal>>,
    @JsonProperty("symbol")
    val instrument: String,
    @JsonProperty("ms_t")
    val timestamp: Long
)
