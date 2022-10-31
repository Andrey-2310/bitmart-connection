package com.example.websocket.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.client.standard.StandardWebSocketClient

@Configuration
class WebsocketConfiguration {

    @Bean
    fun createStandardWebSocketClient() = StandardWebSocketClient()
}
