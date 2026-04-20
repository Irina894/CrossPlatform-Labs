package com.example.lab1

class JvmPlatform : Platform {
    override val name: String = "Desktop"
}

actual fun getPlatform(): Platform = JvmPlatform()