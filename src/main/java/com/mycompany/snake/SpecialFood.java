/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;

import java.awt.Graphics;

/**
 *
 * @author alu10701951
 */
public class SpecialFood extends Food{
    private int row, col;
    
    public SpecialFood(int row, int col) {
        super(row, col);
        this.row = row;
        this.col = col;
    }
    
     public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    
    public void printSpecialFood(Graphics g, int squareWidth, int squareHeight){
        SquareType squareType = SquareType.SPECIAL_FOOD;
        Util.drawSquare(g, row, col, squareWidth, squareHeight, squareType);
    }
}
