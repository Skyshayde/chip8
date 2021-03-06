package com.teddyheinen.chip8

interface Decoder {
    fun before(opCode: Int, address: Int)
    fun unknown(opCode: Int, address: Int)
    fun clear()
    fun ret()
    fun jmp(address: Int)
    fun call(address: Int)
    fun skipEqual(reg: Int, value: Int)
    fun skipNotEqual(reg: Int, value: Int)
    fun skipEqualRegister(reg1: Int, reg2: Int)
    fun set(reg: Int, value: Int)
    fun add(reg: Int, value: Int)
    fun copy(reg1: Int, reg2: Int)
    fun or (reg1: Int, reg2: Int)
    fun and (reg1: Int, reg2: Int)
    fun xor (reg1: Int, reg2: Int)
    fun addr (reg1: Int, reg2: Int)
    fun subr (reg1: Int, reg2: Int)
    fun shr (reg1: Int)
    fun subn (reg1: Int, reg2: Int)
    fun shl (reg1: Int)
    fun skipNotEqualRegister (reg1: Int, reg2: Int)
    fun seti (value: Int)
    fun jmpv0 (address: Int)
    fun rand (reg: Int, value: Int)
    fun draw (reg1: Int, reg2: Int, value: Int)
    fun jkey (reg: Int)
    fun jnkey (reg: Int)
    fun getdelay (reg: Int)
    fun waitkey (reg: Int)
    fun setdelay (reg: Int)
    fun setsound (reg: Int)
    fun addi (reg: Int)
    fun spritei (reg: Int)
    fun bcd (reg: Int)
    fun push (reg: Int)
    fun pop (reg: Int)
}