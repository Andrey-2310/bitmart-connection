package com.example.websocket

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
//@EnableBinding(OrderBookOutput::class)
//@EnableIntegration
class WebsocketApplication

fun main(args: Array<String>) {
    runApplication<WebsocketApplication>(*args)
}
