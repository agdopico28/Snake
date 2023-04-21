/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;

import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author alu10701951
 */
public class Food extends Node{
    public static final int POINT = 5;
    
    public Food(Snake snake) {
        super(0,0);
        Random r = new Random();
        int row = r.nextInt(Board.NUM_ROWS);
        int col = r.nextInt(Board.NUM_COLS);
        while(snake.containSnake(row, col)){
            row = r.nextInt(Board.NUM_ROWS);
            col = r.nextInt(Board.NUM_COLS);
        }
        setRow(row);
        setCol(col);
    }

    
    
    public void printFood(Graphics g, int squareWidth, int squareHeight){
        SquareType squareType = SquareType.FOOD;
        Util.drawSquare(g, getRow(), getCol(), squareWidth, squareHeight, squareType);
    }
    
    public void remove(){
        
    }
    
    public int getPoints(){
        return POINT;
    }

    public int nodesWhenEat(){
        return 1;
    }
   
}
