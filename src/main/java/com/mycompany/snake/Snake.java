/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alu10701951
 */
public class Snake {
    private List<Node> list;
    private Direction direction;
    private int initialNode;
    
    public Snake(Direction direction, int initialNode) {
        this.direction = direction;
        this.initialNode = initialNode;
        initilSnake(initialNode);
    }

    private void initilSnake(int initialNode1) {
        int rows = Board.NUM_ROWS;
        int cols = Board.NUM_COLS;
        int count = cols* 1/4;
        list = new ArrayList<>();
        for (int i = 0; i < initialNode1; i++) {
            Node node = new Node(rows/2, count);
            list.add(node);
            count--;
        }
    }
    
    public void printSnake(Graphics g, int squareWidth, int squareHeight){
        boolean isHead = true;
        for(int i = 0; i < list.size(); i++){
            Node nodePrint = list.get(i);
            if(isHead){
                SquareType squareType = SquareType.HEAD;
                Util.drawSquare(g, nodePrint.getRow(), nodePrint.getCol(), squareWidth, squareHeight, squareType);
                isHead = false;
            }else{
                 SquareType squareType = SquareType.BODY;
                Util.drawSquare(g, nodePrint.getRow(), nodePrint.getCol(), squareWidth, squareHeight, squareType);
            }
        }
    }
    
    public boolean containSnake(int row,int col){
        for(int i = 0; i < list.size(); i++){
            Node nodeContains = list.get(i);
            if(row == nodeContains.getRow() && col == nodeContains.getCol() ){
                return true;
            }
        }
        return false;
    }
    
    
}
