package com.example.kmpchatproject

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform