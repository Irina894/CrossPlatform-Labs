package com.example.lab1

class JsPlatform : Platform {
    override val name: String = "Web"
}

actual fun getPlatform(): Platform = JsPlatform()