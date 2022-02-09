package me.roberto88480.snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class SnakeGui extends JFrame implements KeyListener {
    private final SnakePanel[][] display;
    private SnakeDirection direction;
    private final int width;
    private final int height;
    private final Timer timer;
    private int snakeSize = 1;
    private final LinkedList<SnakePosition> snakePositions;
    public SnakeGui(int width, int height){
        super("Snake");
        this.width = width;
        this.height = height;
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.GRAY);
        setLayout(new GridLayout(width, height,1,1));
        setResizable(true);
        display = new SnakePanel[width][height];
        for (int i = 0; i < display.length; i++){
            for (int j = 0; j < display[i].length; j++){
                display[i][j] = new SnakePanel();
                display[i][j].setBackground(Color.BLACK);
                add(display[i][j]);
            }
        }
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
        snakePositions = new LinkedList<>();
        snakePositions.add(new SnakePosition(width/2, height/2));
        display[snakePositions.getFirst().x][snakePositions.getFirst().y].setBackground(Color.WHITE);
        this.timer = new Timer(500, this::timerAction);
        this.timer.setRepeats(true);
        this.timer.start();
        this.direction = SnakeDirection.RIGHT;
    }

    private void timerAction(ActionEvent e) {
        SnakePosition pos = snakePositions.getFirst();
        switch (direction){
            case RIGHT -> {
                if (snakePositions.getFirst().y+1 < width){
                    display[pos.x][pos.y+1].setBackground(Color.WHITE);
                    snakePositions.addFirst(new SnakePosition(pos.x, pos.y+1));
                }
            }
            case LEFT -> {
                if (snakePositions.getFirst().y-1 >= 0){
                    display[pos.x][pos.y-1].setBackground(Color.WHITE);
                    snakePositions.addFirst(new SnakePosition(pos.x, pos.y-1));
                }
            }
            case UP -> {
                if (snakePositions.getFirst().x-1 >= 0){
                    display[pos.x-1][pos.y].setBackground(Color.WHITE);
                    snakePositions.addFirst(new SnakePosition(pos.x-1, pos.y));
                }
            }
            case DOWN -> {
                if (snakePositions.getFirst().x+1 < height){
                    display[pos.x+1][pos.y].setBackground(Color.WHITE);
                    snakePositions.addFirst(new SnakePosition(pos.x+1, pos.y));
                }
            }
        }

        if (snakePositions.size()>snakeSize){
            SnakePosition a = snakePositions.removeLast();
            display[a.x][a.y].setBackground(Color.BLACK);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> this.direction = SnakeDirection.RIGHT;
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> this.direction = SnakeDirection.LEFT;
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> this.direction = SnakeDirection.DOWN;
            case KeyEvent.VK_UP, KeyEvent.VK_W -> this.direction = SnakeDirection.UP;
            default -> {}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
