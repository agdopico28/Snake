/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;

import java.awt.Graphics;
import java.util.Calendar;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author alu10701951
 */
public class SpecialFood extends Food{
    private int timeVisible;
    private long startingTime;
    
    public SpecialFood(Snake snake) {
        super(snake);
        Random r = new Random();
        timeVisible = r.nextInt(10)*1000+3000;
        startingTime = Calendar.getInstance().getTimeInMillis();
    }
 
    @Override
    public void printFood(Graphics g, int squareWidth, int squareHeight){
        SquareType squareType = SquareType.SPECIAL_FOOD;
        Util.drawSquare(g, getRow(), getCol(), squareWidth, squareHeight, squareType);
    }
    
    @Override
    public void remove(){
       
    }
    
    public boolean hasToBeErased(){
        if(Calendar.getInstance().getTimeInMillis() -
                startingTime >= timeVisible){
            return true;
        }
        return false;
    }
    
    @Override
    public int getPoints(){
        return POINT;
    }
    
    @Override
    public int nodesWhenEat(){
        return 3;
    }
}
