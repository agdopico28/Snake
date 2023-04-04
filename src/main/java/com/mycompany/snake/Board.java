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
    private SpecialFood specialFood;
    private MyKeyAdapter keyAdapter;
    private int deltaTime;

    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != Direction.RIGHT) {

                        snake.setDirection(Direction.LEFT);

                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != Direction.LEFT) {
                        snake.setDirection(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != Direction.DOWN) {
                        snake.setDirection(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != Direction.UP) {
                        snake.setDirection(Direction.DOWN);

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
        food = generateFood();
        changeSpecialFood();
        keyAdapter = new MyKeyAdapter();
        addKeyListener(keyAdapter);
        setFocusable(true);
        timer = new Timer(250, new ActionListener() {
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
        if (specialFood != null) {
            specialFood.printSpecialFood(g, squareWidth(), squareHeight());
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void tick() {
        if (snake.canMove()) {
            snake.move();
            if (snake.eatFood(food)) {
                snake.incremet();
                food = generateFood();
            } else if (snake.eatFood(specialFood)) {
                specialFood = null;
                for (int i = 0; i < 3; i++) {
                    snake.incremet();
                }
            }
        } else {
            timer.stop();
        }
        repaint();
    }

    private Food generateFood() {
        boolean isGenerate = false;
        int row = 0;
        int col = 0;
        while (!isGenerate) {
            row = (int) (Math.random() * NUM_ROWS);
            col = (int) (Math.random() * NUM_COLS);
            if (!snake.containSnake(row, col)) {
                isGenerate = true;
            }
        }
        Food food = new Food(row, col);
        return food;
    }

    public void changeSpecialFood() {
        int timerToSleep = (int) ((Math.random() * 20000) + 10000);
        timerToAppear = new Timer(timerToSleep, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                SpecialFood newSpecialFood = generateSpecialFood();
                specialFood = newSpecialFood;
                timerToAppear.stop();
                timerToDisappear = new Timer(10000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        specialFood = null;
                        timerToDisappear.stop();
                        changeSpecialFood();
                    }
                });
                timerToDisappear.start();
            }
        });
        timerToAppear.start();
    }

    private SpecialFood generateSpecialFood() {
        boolean isGenerate = false;
        int row = 0;
        int col = 0;
        while (!isGenerate) {
            row = (int) (Math.random() * NUM_ROWS);
            col = (int) (Math.random() * NUM_COLS);
            if (!snake.containSnake(row, col)) {
                isGenerate = true;
            }
        }
        SpecialFood specialFood = new SpecialFood(row, col);
        return specialFood;
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
