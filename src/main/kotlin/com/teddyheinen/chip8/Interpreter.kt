package com.teddyheinen.chip8

import java.util.*
import kotlin.experimental.and


class Interpreter (val state: EmuState): Decoder {
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun jmp(address: Int) {
        state.pc = address;
    }

    override fun call(address: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun jeq(reg: Int, value: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun jneq(reg: Int, value: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun jeqr(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(reg: Int, value: Int) {
        state.registers[reg] = value.toByte();
    }

    override fun add(reg: Int, value: Int) {
        state.registers[reg] = (state.registers[reg] + value).toByte();
    }

    override fun setr(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun or(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun and(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun xor(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addr(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sub(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun shr(reg1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subb(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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