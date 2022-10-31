package com.example.websocket.service.websocket

import com.example.websocket.domain.CreateSessionEvent
import com.example.websocket.service.websocket.WebSocketSessionHandler.Companion.BTC_USDT
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class WebSocketStarter(
    private val webSocketSessionHandler: WebSocketSessionHandler
) {

    @PostConstruct
    fun startWebsocketConnection() {
        webSocketSessionHandler.createConnection(CreateSessionEvent(this, BTC_USDT))
    }
}
