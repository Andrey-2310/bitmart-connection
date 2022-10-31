package com.example.websocket.service.websocket

import com.example.websocket.domain.CreateSessionEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import org.springframework.web.socket.WebSocketHttpHeaders
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import java.net.URI
import java.util.concurrent.ConcurrentHashMap

@Service
class WebSocketSessionHandler(
    private val bitMarkWebSocketClient: WebSocketClient,
    private val webSocketClient: StandardWebSocketClient
) {

    private val sessionMap = ConcurrentHashMap<String, WebSocketSession>()

    @EventListener
    fun createConnection(createSessionEvent: CreateSessionEvent) {
        val session = webSocketClient.doHandshake(bitMarkWebSocketClient, defaultHeaders, URI.create(URL)).get()
        session.attributes[INSTRUMENT_KEY] = createSessionEvent.instrument
        sessionMap[createSessionEvent.instrument] = session
    }

    companion object {
        private const val URL = "wss://ws-manager-compress.bitmart.com/api?protocol=1.1"
        private val defaultHeaders = WebSocketHttpHeaders()
        const val INSTRUMENT_KEY = "instrument"
        const val BTC_USDT = "BTC_USDT"
    }
}
