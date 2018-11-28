package com.teddyheinen.chip8

import java.util.*
import kotlin.experimental.*


class Interpreter(val state: EmuState) : Decoder {
    override fun before(opCode: Int, address: Int) {
        state.updateScreen = false
    }

    override fun unknown(opCode: Int, address: Int) {
        throw UnsupportedOperationException("Operation 0x${opCode.hex} does not match with an implemented operation.")
    }

    override fun clear() {
        for (i in 0..state.screen.screen.size) {
            for (j in 0..state.screen.screen[i].size) {
                state.screen.screen[i][j] = 0
            }
        }
        state.updateScreen = true;
    }

    override fun ret() {
        state.pc = state.stack[state.sp]
        state.sp--;
    }

    override fun jmp(address: Int) {
        state.pc = address;
    }

    override fun call(address: Int) {
        state.sp++;
        state.stack[state.sp] = state.pc
        state.pc = address;
    }

    override fun skipEqual(reg: Int, value: Int) {
        if (state.registers[reg] == value.toByte()) {
            state.pc += 2;
        }
        state.pc += 2;
    }

    override fun skipNotEqual(reg: Int, value: Int) {
        if (state.registers[reg] != value.toByte()) {
            state.pc += 2;
        }
        state.pc += 2;
    }

    override fun skipEqualRegister(reg1: Int, reg2: Int) {
        if (state.registers[reg1] == state.registers[reg2]) {
            state.pc += 2;
        }
        state.pc += 2;
    }

    override fun skipNotEqualRegister(reg1: Int, reg2: Int) {
        if (state.registers[reg1] != state.registers[reg2]) {
            state.pc += 2;
        }
        state.pc += 2;
    }

    override fun set(reg: Int, value: Int) {
        state.registers[reg] = value.toByte();
        state.pc += 2;
    }

    override fun add(reg: Int, value: Int) {
        state.registers[reg] = (state.registers[reg] + value).toByte();
        state.pc += 2;
    }

    override fun copy(reg1: Int, reg2: Int) {
        state.registers[reg1] = state.registers[reg2]
        state.pc += 2;
    }

    override fun or(reg1: Int, reg2: Int) {
        state.registers[reg1] = state.registers[reg1] or state.registers[reg2];
        state.pc += 2;
    }

    override fun and(reg1: Int, reg2: Int) {
        state.registers[reg1] = state.registers[reg1] and state.registers[reg2];
        state.pc += 2;
    }

    override fun xor(reg1: Int, reg2: Int) {
        state.registers[reg1] = state.registers[reg1] xor state.registers[reg2];
        state.pc += 2;
    }

    override fun addr(reg1: Int, reg2: Int) {
        val sum: Int = (state.registers[reg1] + state.registers[reg2])
        state.registers[reg1] = (sum and 255).toByte();
        state.registers[0xf] = if (sum > 255) 1 else 0
        state.pc += 2;
    }

    override fun subr(reg1: Int, reg2: Int) {
        state.registers[0xF] = if (state.registers[reg1] > state.registers[reg2]) 1 else 0
        state.registers[reg1] = (state.registers[reg1] - state.registers[reg2]).toByte();
        state.pc += 2;
    }

    override fun shr(reg1: Int) {
        state.registers[0xf] = state.registers[reg1].and(0x1)
        state.registers[reg1] = (state.registers[reg1].toInt() shr 1).toByte()
        state.pc += 2;
    }

    override fun subn(reg1: Int, reg2: Int) {
        state.registers[reg1] = (state.registers[reg2] - state.registers[reg1]).toByte();
        state.registers[0xF] = if (state.registers[reg2] > state.registers[reg1]) 1 else 0
        state.pc += 2;
    }

    override fun shl(reg1: Int) {
        state.registers[0xf] = state.registers[reg1].and(0x1)
        state.registers[reg1] = (state.registers[reg1].toInt() shl 1).toByte();
        state.pc += 2;
    }


    override fun seti(value: Int) {
        state.index = value;
        state.pc += 2
    }

    override fun jmpv0(address: Int) {
        state.pc += address.toByte();
    }

    override fun rand(reg: Int, value: Int) {
        state.registers[reg] = (Random().nextInt(256) and value).toByte();
        state.pc += 2
    }

    override fun draw(reg1: Int, reg2: Int, value: Int) {
        for (i in 0..value - 1) {
            val b: String = state.ram[state.index + i].toString(2).removePrefix("-").padStart(8,"0".toCharArray()[0])
            for (j in 0..b.length-1) {
                val bit: Int = b[j].toInt()
                val x: Int = (state.registers[reg1] + j) % 64
                val y: Int = (state.registers[reg2] + i) % 32
                val original = state.screen.screen[x][y]
                val pixel = original xor bit.toByte()
                state.screen.screen[x][y] = pixel
                if (original.toInt() == 1) state.registers[0xf] = 1
            }
        }
        state.pc += 2
        state.updateScreen = true
    }

    override fun jkey(reg: Int) {
        if (state.keys[state.registers[reg].toInt()] != 0.toByte()) {
            state.pc += 2
        }
        state.pc += 2
    }

    override fun jnkey(reg: Int) {
        if (state.keys[state.registers[reg].toInt()] == 0.toByte()) {
            state.pc += 2
        }
        state.pc += 2
    }

    override fun getdelay(reg: Int) {
        state.registers[reg] = state.delay.toByte()
        state.pc += 2
    }

    override fun waitkey(reg: Int) {
        // TODO need to actually implement this on the rest of the emulator
        state.waitForKey = reg
    }

    override fun setdelay(reg: Int) {
        state.delay = state.registers[reg].toInt()
        state.pc += 2
    }

    override fun setsound(reg: Int) {
        state.sound = state.registers[reg].toInt()
        state.pc += 2
    }

    override fun addi(reg: Int) {
        state.index += state.registers[reg]
        state.pc += 2
    }

    override fun spritei(reg: Int) {
        state.index = state.registers[reg] * 5
        if(state.index >= 80) state.index = 0
        state.pc += 2
    }

    override fun bcd(reg: Int) {
        var index: Int = state.index
        for (i in state.registers[reg].toString()) {
            state.ram[index] = i.toByte()
            index++
        }
        state.pc += 2
    }

    override fun push(reg: Int) {
        for (i in 0..reg) {
            state.ram[state.index + i] = state.registers[i]
        }
        state.pc += 2
    }

    override fun pop(reg: Int) {
        for (i in 0..reg) {
            state.registers[i] = state.ram[state.index + i]
        }
        state.pc += 2
    }

}