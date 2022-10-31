package com.example.websocket.domain

import org.springframework.context.ApplicationEvent

class CreateSessionEvent(source: Any, val instrument: String) : ApplicationEvent(source)
