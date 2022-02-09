package me.roberto88480.snakegame;

import javax.swing.*;

public class SnakePanel extends JPanel {
    private SnakeFieldContent content;
    public SnakeFieldContent getSnakeFieldContent(){
        return content;
    }
    public SnakePanel(){
        super();
        content = SnakeFieldContent.EMPTY;
    }
}
