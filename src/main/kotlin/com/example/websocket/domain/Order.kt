package com.example.websocket.domain

import java.math.BigDecimal

data class Order(
    val price: BigDecimal,
    val quantity: BigDecimal
)
