package com.teddyheinen.chip8

import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel

class Screen: JPanel() {

    var screen: Array<ByteArray> = emptyArray()

    init {
        size = Dimension(64 * 8, 32 * 8)
        background = Color.BLACK
    }

    fun draw(screen: Array<ByteArray>) {
        this.screen = screen
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.color = Color.WHITE
        for(i in 0..screen.size-1) {
            for(j in 0..screen[i].size-1) {
                if(screen[i][j] != 0.toByte()) g.fillRect(i * 8,j * 8,8,8)
            }
        }
    }
}