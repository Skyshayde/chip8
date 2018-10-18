package com.teddyheinen.chip8

fun Int.toHex() = Integer.toHexString(this)
fun Byte.toHex() = Integer.toHexString(this.toInt())
fun Byte.high() = (this.toInt() and 0xf0) shr 4 // wipe second half (lower 4 bits) of octet and then shift right
fun Byte.low() = this.toInt() and 0xf // wipe first half (higher 4 bits) of octet
fun address(msb: Byte, lsb: Byte) = ((msb.toInt() and 0xf) shl 8) or (lsb.toInt() and 0xff)
