package com.example.websocket.dto

data class SubscribeEvent(
    val op: String,
    val args: List<String>,
)
