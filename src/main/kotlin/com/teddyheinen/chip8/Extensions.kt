package com.teddyheinen.chip8

val Int.hex: String get() = Integer.toHexString(this)
val Byte.hex: String get() = Integer.toHexString(this.toInt())
val Byte.high: Int get() = (this.toInt() and 0xf0) shr 4 // wipe second half (lower 4 bits) of octet and then shift right
val Byte.low: Int get() = this.toInt() and 0xf // wipe first half (higher 4 bits) of octet
fun address(msb: Byte, lsb: Byte) = ((msb.toInt() and 0xf) shl 8) or (lsb.toInt() and 0xff)
fun StringBuilder.line(line: String) = this.append(line + "\n")
