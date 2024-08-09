package demo;
import javax.swing.*;

public class MySnake{
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setBounds(600, 100, 700, 900);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MyPanel());
        frame.setVisible(true);
    }

}