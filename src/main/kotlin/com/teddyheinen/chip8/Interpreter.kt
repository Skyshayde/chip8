package com.teddyheinen.chip8

import java.util.*
import kotlin.experimental.and
import kotlin.experimental.or
import kotlin.experimental.xor


class Interpreter(val state: EmuState) : Decoder {
    override fun before(opCode: Int, address: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unknown(opCode: Int, address: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun ret() {
        state.pc = state.stack.last();
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
        state.registers[reg1] = state.registers[reg1].or(state.registers[reg2]);
        state.pc += 2;
    }

    override fun and(reg1: Int, reg2: Int) {
        state.registers[reg1] = state.registers[reg1].and(state.registers[reg2]);
        state.pc += 2;
    }

    override fun xor(reg1: Int, reg2: Int) {
        state.registers[reg1] = state.registers[reg1].xor(state.registers[reg2]);
        state.pc += 2;
    }

    override fun addr(reg1: Int, reg2: Int) {
        state.registers[reg1] = (state.registers[reg1] + state.registers[reg2]).toByte();
        state.pc += 2;
    }

    override fun subr(reg1: Int, reg2: Int) {
        state.registers[reg1] = (state.registers[reg1] - state.registers[reg2]).toByte();
        state.registers[0xF] = if (state.registers[reg1] > state.registers[reg2]) 1 else 0
        state.pc += 2;
    }

    override fun shr(reg1: Int) {
        state.registers[0xf] = state.registers[reg1].and(0x1)
        state.registers[reg1] = (state.registers[reg1] / 2).toByte();
        state.pc += 2;
    }

    override fun subn(reg1: Int, reg2: Int) {
        state.registers[reg1] = (state.registers[reg2] - state.registers[reg1]).toByte();
        state.registers[0xF] = if (state.registers[reg2] > state.registers[reg1]) 1 else 0
        state.pc += 2;
    }

    override fun shl(reg1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun jneqr(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun seti(value: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun jmpv0(address: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rand(reg: Int, value: Int) {
        val b: ByteArray = ByteArray(1);
        Random().nextBytes(b)
        state.registers[reg] = b[0].and(value.toByte());
        state.pc += 2
    }

    override fun draw(reg1: Int, reg2: Int, value: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun jkey(reg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun jnkey(reg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getdelay(reg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun waitkey(reg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setdelay(reg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setsound(reg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addi(reg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun spritei(reg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bcd(reg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun push(reg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pop(reg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}