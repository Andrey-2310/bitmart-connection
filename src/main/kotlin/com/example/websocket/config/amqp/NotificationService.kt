package com.example.websocket.config.amqp

import org.springframework.messaging.MessageChannel
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class NotificationService {

    fun sendNotification(messageChannel: MessageChannel, message: Any) {
        messageChannel.send(MessageBuilder.withPayload(message).build())
    }
}
