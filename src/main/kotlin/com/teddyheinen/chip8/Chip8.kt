package com.teddyheinen.chip8

import java.io.BufferedInputStream
import java.io.DataInputStream
import java.io.FileInputStream

fun main(args: Array<String>) {
    val state = loadRom("roms/maze.ch8")
    println(disassemble(state))
}

fun loadRom(rom: String): EmuState {
    return DataInputStream(BufferedInputStream(FileInputStream(rom))).use {
        val rom = it.readBytes()
        val state = EmuState(programSize = rom.size)
        System.arraycopy(rom, 0, state.ram, state.pc, rom.size)
        state
    }
}

fun disassemble(emuState: EmuState): String {
    val decoder = Disassembler()
    for (addr in 0x200..(0x200 + emuState.programSize - 1) step 2) {
        val msb = emuState.ram[addr]
        val lsb = emuState.ram[addr + 1]
        decode(decoder, addr, msb, lsb)
    }
    return decoder.toString()
}

fun decode(decoder: Decoder, address: Int, msb: Byte, lsb: Byte) {
    val opCode = (msb.toInt() shl 8 or lsb.toInt().and(0xff)).and(0xffff)
    decoder.before(opCode, address)
    when (msb.high) {
        0x0 -> {
            when (msb.toInt() shl 8 or lsb.toInt()) {
                0x00e0 -> decoder.clear()
                0x00ee -> decoder.ret()
                else -> decoder.unknown(opCode, address)
            }
        }
        0x1 -> decoder.jmp(address(msb, lsb))
        0x2 -> decoder.call(address(msb, lsb))
        0x3 -> decoder.skipEqual(msb.low, lsb.toInt())
        0x4 -> decoder.skipNotEqual(msb.low, lsb.toInt())
        0x5 -> decoder.skipEqualRegister(msb.low, lsb.high)
        0x6 -> decoder.set(msb.low, lsb.toInt())
        0x7 -> decoder.add(msb.low, lsb.toInt())
        0x8 -> {
            val reg1 = msb.low
            val reg2 = lsb.high
            when (lsb.low) {
                0x0 -> decoder.copy(reg1, reg2)
                0x1 -> decoder.or(reg1, reg2)
                0x2 -> decoder.and(reg1, reg2)
                0x3 -> decoder.xor(reg1, reg2)
                0x4 -> decoder.addr(reg1, reg2)
                0x5 -> decoder.subr(reg1, reg2)
                0x6 -> decoder.shr(reg1)
                0x7 -> decoder.subn(reg1, reg2)
                0xe -> decoder.shl(reg1)
                else -> decoder.unknown(opCode, address)
            }
        }
        0x9 -> decoder.jneqr(msb.low, lsb.high)
        0xa -> decoder.seti(address(msb, lsb))
        0xb -> decoder.jmpv0(address(msb, lsb))
        0xc -> decoder.rand(msb.low, lsb.toInt())
        0xd -> decoder.draw(msb.low, lsb.high, lsb.low)
        0xe -> {
            when (lsb.toInt() or 0xff) {
                0x9e -> decoder.jkey(msb.low)
                0xa1 -> decoder.jnkey(msb.low)
                else -> decoder.unknown(opCode, address)
            }
        }
        0xf -> {
            val reg = msb.low
            when (lsb.toInt() or 0xff) {
                0x07 -> decoder.getdelay(reg)
                0x0a -> decoder.waitkey(reg)
                0x15 -> decoder.setdelay(reg)
                0x18 -> decoder.setsound(reg)
                0x1e -> decoder.addi(reg)
                0x29 -> decoder.spritei(reg)
                0x33 -> decoder.bcd(reg)
                0x55 -> decoder.push(reg)
                0x65 -> decoder.pop(reg)
                else -> decoder.unknown(opCode, address)
            }
        }
        else -> decoder.unknown(opCode, address)


    }

}