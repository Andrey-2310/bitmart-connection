package com.example.websocket.service.websocket

import com.example.websocket.config.publish
import com.example.websocket.domain.CreateSessionEvent
import com.example.websocket.dto.OrderBookMessage
import com.example.websocket.dto.SubscribeEvent
import com.example.websocket.service.orderbook.OrderBookManager
import com.example.websocket.service.websocket.WebSocketSessionHandler.Companion.BTC_USDT
import com.example.websocket.service.websocket.WebSocketSessionHandler.Companion.INSTRUMENT_KEY
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.PongMessage
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Service
class WebSocketClient(
    private val mapper: ObjectMapper,
    private val orderBookManager: OrderBookManager
//    private lateinit var webSocketSessionHandler: WebSocketSessionHandler
) : TextWebSocketHandler() {

    private val log = LoggerFactory.getLogger(WebSocketClient::class.java)

    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
        log.info("Connection established for session with instrument ${session.attributes[INSTRUMENT_KEY]}")
        session.sendMessage(
            TextMessage(
                mapper.writeValueAsString(
                    SubscribeEvent(
                        op = "subscribe",
                        args = listOf("spot/depth50:$BTC_USDT")
                    )
                )
            )
        )
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val response = mapper.readValue(message.payload, OrderBookMessage::class.java)
//        log.info("Receive text message for session with instrument ${session.attributes[INSTRUMENT_KEY]}: $response")
        orderBookManager.refreshOrderBook(response.orderBookData.first())
    }

    override fun handlePongMessage(session: WebSocketSession, message: PongMessage) {
        log.info("Receive Pong message for session with instrument ${session.attributes[INSTRUMENT_KEY]} ")
    }

    override fun handleTransportError(session: WebSocketSession, exception: Throwable) {
        log.error("Transport Error occurred for session with instrument ${session.attributes[INSTRUMENT_KEY]} ")
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        log.info("Connection closed for session with instrument ${session.attributes[INSTRUMENT_KEY]} ")
        CreateSessionEvent(this, session.attributes[INSTRUMENT_KEY] as String).publish()
//        webSocketSessionHandler.createConnection(session.attributes[INSTRUMENT_KEY] as String)
    }
}
