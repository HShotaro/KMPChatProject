package com.example.kmpchatproject.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
