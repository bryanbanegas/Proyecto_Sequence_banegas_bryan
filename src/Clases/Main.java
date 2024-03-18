/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author adalb
 */
import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class Main extends JFrame {
    private JLabel label;
    private int secondsRemaining;

    public Main() {
        setLayout(null);  
        Border border=BorderFactory.createLineBorder(Color.BLACK, 2);
        label=new JLabel();
        label.setBounds(100,100,50,76);
        label.setBorder(border);
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagesTablero/Tokens8.png")));
        add(label);
        
    }

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}

