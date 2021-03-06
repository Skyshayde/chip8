package com.teddyheinen.chip8

class Disassembler(): Decoder {


    val builder = StringBuilder()
    override fun before(opCode: Int, address: Int) {
        builder.append("addr: 0x${address.hex}, op: 0x${opCode.hex}, ")
    }

    override fun unknown(opCode: Int, address: Int) {
        builder.append("Unknown opcode addr: 0x${address.hex}, op: 0x${opCode.hex}")
    }

    override fun clear() {
        builder.line("clear")
    }

    override fun ret() {
        builder.line("ret")
    }

    override fun jmp(address: Int) {
        builder.line("jmp 0x${address.hex}")
    }

    override fun call(address: Int) {
        builder.line("call 0x${address.hex}")
    }

    override fun skipEqual(reg: Int, value: Int) {
        builder.line("skipEqual v${reg.hex}, 0x${value.hex}")
    }

    override fun skipNotEqual(reg: Int, value: Int) {
        builder.line("skipNotEqual v${reg.hex}, 0x${value.hex}")
    }

    override fun skipEqualRegister(reg1: Int, reg2: Int) {
        builder.line("skipEqualRegister v${reg1.hex}, v${reg2.hex}")
    }

    override fun set(reg: Int, value: Int) {
        builder.line("set v${reg.hex}, 0x${value.hex}")
    }

    override fun add(reg: Int, value: Int) {
        builder.line("add v${reg.hex}, 0x${value.hex}")
    }

    override fun copy(reg1: Int, reg2: Int) {
        builder.line("copy v${reg1.hex}, v${reg2.hex}")
    }

    override fun or(reg1: Int, reg2: Int) {
        builder.line("or v${reg1.hex}, v${reg2.hex}")
    }

    override fun and(reg1: Int, reg2: Int) {
        builder.line("and v${reg1.hex}, v${reg2.hex}")
    }

    override fun xor(reg1: Int, reg2: Int) {
        builder.line("xor v${reg1.hex}, v${reg2.hex}")
    }

    override fun addr(reg1: Int, reg2: Int) {
        builder.line("addr v${reg1.hex}, v${reg2.hex}")
    }

    override fun subr(reg1: Int, reg2: Int) {
        builder.line("subr v${reg1.hex}, v${reg2.hex}")
    }

    override fun shr(reg1: Int) {
        builder.line("shr v${reg1.hex}")
    }

    override fun subn(reg1: Int, reg2: Int) {
        builder.line("subn v${reg1.hex}, v${reg2.hex}")
    }

    override fun shl(reg1: Int) {
        builder.line("shl v${reg1.hex}")
    }

    override fun skipNotEqualRegister(reg1: Int, reg2: Int) {
        builder.line("skipNotEqualRegister v${reg1.hex}, v${reg2.hex}")
    }

    override fun seti(value: Int) {
        builder.line("seti 0x${value.hex}")
    }

    override fun jmpv0(address: Int) {
        builder.line("jmpv0 0x${address.hex}")
    }

    override fun rand(reg: Int, value: Int) {
        builder.line("rand v${reg.hex}, 0x${value.hex}")
    }

    override fun draw(reg1: Int, reg2: Int, value: Int) {
        builder.line("draw v${reg1.hex}, v${reg2.hex}, 0x${value.hex}")
    }

    override fun jkey(reg: Int) {
        builder.line("jkey v${reg.hex}")
    }

    override fun jnkey(reg: Int) {
        builder.line("jnkey v${reg.hex}")
    }

    override fun getdelay(reg: Int) {
        builder.line("getdelay v${reg.hex}")
    }

    override fun waitkey(reg: Int) {
        builder.line("waitkey v${reg.hex}")
    }

    override fun setdelay(reg: Int) {
        builder.line("setdelay v${reg.hex}")
    }

    override fun setsound(reg: Int) {
        builder.line("setsound v${reg.hex}")
    }

    override fun addi(reg: Int) {
        builder.line("addi v${reg.hex}")
    }

    override fun spritei(reg: Int) {
        builder.line("spritei v${reg.hex}")
    }

    override fun bcd(reg: Int) {
        builder.line("bcd v${reg.hex}")
    }

    override fun push(reg: Int) {
        builder.line("push v0-v${reg.hex}")
    }

    override fun pop(reg: Int) {
        builder.line("pop v0-v${reg.hex}")
    }

    override fun toString(): String {
        return builder.toString()
    }
}