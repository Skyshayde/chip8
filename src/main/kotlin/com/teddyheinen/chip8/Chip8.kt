package com.teddyheinen.chip8

import java.io.BufferedInputStream
import java.io.DataInputStream
import java.io.FileInputStream

fun main(args: Array<String>) {
    val state = loadRom("roms/pong.ch8")
    println(state.ram[0x200])
}

fun loadRom(rom: String): EmuState {
    return DataInputStream(BufferedInputStream(FileInputStream(rom))).use {
        val state = EmuState()
        val rom = it.readBytes()
        System.arraycopy(rom,0,state.ram,state.pc,rom.size)
        state
    }
}