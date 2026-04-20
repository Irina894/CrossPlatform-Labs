package com.example.lab1

class WasmJsPlatform : Platform {
    override val name: String = "Web"
}

actual fun getPlatform(): Platform = WasmJsPlatform()