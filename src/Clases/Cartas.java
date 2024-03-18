/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import javax.swing.JButton;

/**
 *
 * @author adalb
 */
public class Cartas extends JButton {
    public String card;
    public String player;
    public String color;
    public boolean sePuedeBloquer;
    
    public Cartas(String card, String player, String color, boolean sePuedeBloquer){
        this.card=card;
        this.player=player;
        this.color=color;
        this.sePuedeBloquer=sePuedeBloquer;
    }
    
    public String getCard(){
        return card;
    }
    
    public String getPlayer(){
        return player;
    }
    
    public String getColor(){
        return color;
    }
    
    public void changeCard(String card){
        this.card=card;
    }
    
    public void changeColor(String color){
        this.color=color;
    }
    
    public boolean getSePuedeBloquer(){
        return sePuedeBloquer;
    }
    
    public void yaNoSePuedeBloquear(){
        sePuedeBloquer=false;
    }
}
