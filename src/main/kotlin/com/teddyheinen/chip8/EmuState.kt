package com.teddyheinen.chip8

data class EmuState(val ram: ByteArray = ByteArray(4096),
                    val registers: ByteArray = ByteArray(16),
                    var pc: Int = 0x200,
                    var index: Int = 0,
                    var sp: Int = 0,
                    val stack: IntArray = IntArray(16),
                    var delay: Int = 0,
                    var sound: Int = 0,
                    val keys: ByteArray = ByteArray(16),
                    val programSize: Int = 0,
                    val screen: Screen = Screen(),
                    var updateScreen: Boolean = false,
                    var waitForKey: Int = -1,
                    val speed: Float = 500F)