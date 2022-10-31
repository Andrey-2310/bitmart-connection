package com.example.websocket.config

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationEvent
import org.springframework.stereotype.Component

@Component
internal class ContextUtilsInitializer(context: ApplicationContext) {
    init {
        applicationContext = context
    }
}

internal lateinit var applicationContext: ApplicationContext

fun ApplicationEvent.publish() {
    applicationContext.publishEvent(this)
}
