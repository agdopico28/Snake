/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.snake;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

/**
 *
 * @author alu10701951
 */
public class Board extends javax.swing.JPanel {

    public static final int NUM_ROWS = 20;
    public static final int NUM_COLS = 20;

    private Timer timer;
    private Timer timerToAppear;
    private Timer timerToDisappear;
    private Snake snake;
    private Food food;
    private FoodFactory foodFactory;
    private MyKeyAdapter keyAdapter;
    private int deltaTime;
    private List<Direction> movements;
    private boolean gameOver;

    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != Direction.RIGHT) {

                        snake.setDirection(Direction.LEFT);
                        movements.add(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != Direction.LEFT) {
                        snake.setDirection(Direction.RIGHT);
                        movements.add(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != Direction.DOWN) {
                        snake.setDirection(Direction.UP);
                        movements.add(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != Direction.UP) {
                        snake.setDirection(Direction.DOWN);
                        movements.add(Direction.DOWN);

                    }
                    break;
                default:
                    break;
            }
            repaint();
        }
    }

    public Board() {
        initComponents();
        myInit();
    }

    private void myInit() {
        snake = new Snake(Direction.RIGHT, 4);
        foodFactory = new FoodFactory();
        food = generateFood();
        movements = new ArrayList<>();
        gameOver = false;
        keyAdapter = new MyKeyAdapter();
        addKeyListener(keyAdapter);
        setFocusable(true);
        timer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tick();
            }
        });

        deltaTime = 500;
        timer.start();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.printSnake(g, squareWidth(), squareHeight());
        food.printFood(g, squareWidth(), squareHeight());
        if(gameOver){
            paintGameOver(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void tick() {
        if(movements.size() != 0){
        Direction dir= movements.get(0);
        snake.setDirection(dir);
        movements.remove(0);
    }
        if (snake.canMove()) {
            snake.move();
            if (snake.eatFood(food)) {
                food = generateFood();
            }
        } else {
            timer.stop();
            gameOver = true;
        }
        repaint();
    }

    private Food generateFood() {
        return foodFactory.getFood(snake);
    }
    
    public void setDeltaTime() {
        /*switch (ConfigData.instance.getlevel()) {
            case 0:*/
        deltaTime = 500;
        /*break;
            case 1:
                deltaTime = 300;
                break;
            case 2:
                deltaTime = 150;
                break;
            default:
                throw new AssertionError();
        }*/
        timer.setDelay(deltaTime);
    }

    public int squareWidth() {
        return getWidth() / Board.NUM_COLS;
    }

    public int squareHeight() {
        return getHeight() / Board.NUM_ROWS;
    }
    
    private void paintGameOver(Graphics g){
        //G
        Util.drawSquare(g, 3, 3, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 3, 4, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 3, 5, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 3, 6, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 4, 3, squareWidth(), squareHeight(), SquareType.GAMEOVER);
         Util.drawSquare(g, 5, 3, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 6, 3, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 7, 3, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 8, 3, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 8, 4, squareWidth(), squareHeight(), SquareType.GAMEOVER);
         Util.drawSquare(g, 8, 5, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 8, 6, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 7, 6, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 6, 6, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 6, 5, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        //A
        Util.drawSquare(g, 4, 7, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 5, 7, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 6, 7, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 7, 7, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 8, 7, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 4, 10, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 5, 10, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 6, 10, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 7, 10, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 8, 10, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 3, 8, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 3, 9, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 6, 8, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 6, 9, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        //M
        Util.drawSquare(g, 3, 11, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 3, 14, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 4, 11, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 5, 11, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 6, 11, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 7, 11, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 8, 11, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 4, 14, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 5, 14, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 6, 14, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 7, 14, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 8, 14, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 4, 12, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 4, 13, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        //E
        Util.drawSquare(g, 3, 15, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 3, 16, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 3, 17, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 3, 18, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 4, 15, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 5, 15, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 6, 15, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 7, 15, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 8, 15, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 8, 16, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 8, 17, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 8, 18, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 6, 16, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        //O
        Util.drawSquare(g, 11, 2, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 12, 2, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 13, 2, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 14, 2, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 15, 3, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 15, 4, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 10, 3, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 10, 4, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 11, 5, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 12, 5, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 13, 5, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 14, 5, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        //V
        Util.drawSquare(g, 10, 6, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 11, 6, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 12, 6, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 13, 6, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 10, 9, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 11, 9, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 12, 9, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 13, 9, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 14, 7, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 15, 7, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 14, 8, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 15, 8, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        //E
        Util.drawSquare(g, 10, 10, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 10, 11, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 10, 12, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 11, 10, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 12, 10, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 13, 10, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 14, 10, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 15, 10, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 13, 11, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 15, 11, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 15, 12, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 15, 13, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        Util.drawSquare(g, 10, 13, squareWidth(), squareHeight(), SquareType.GAMEOVER);
        //R
        Util.drawSquare(g, 10, 14, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 10, 15, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 10, 16, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 10, 17, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 11, 14, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 11, 17, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 12, 14, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 12, 17, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 13, 14, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 13, 15, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 13, 16, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 13, 17, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 14, 14, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 14, 16, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
         Util.drawSquare(g, 15, 14, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        Util.drawSquare(g, 15, 17, squareWidth(), squareHeight(), SquareType.GAMEOVER2);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
