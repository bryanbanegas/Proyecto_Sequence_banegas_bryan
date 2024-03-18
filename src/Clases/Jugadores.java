/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author adalb
 */
public class Jugadores implements Serializable {
    String equipo;
    String jugador;
    String ficha;
    
    public Jugadores(String equipo, String jugador, String ficha){
        this.equipo=equipo;
        this.jugador=jugador;
        this.ficha=ficha;
    }
    
    public String getEquipo(){
        return equipo;
    }
    
    public String getJugador(){
        return jugador;
    }
    
    public String getFicha(){
        return ficha;
    }
    
    public void setFicha(String ficha){
        this.ficha=ficha;
    }
    
    public void guardarJugadores(Jugadores[] jugadores, String nombreArchivo) {
        try{
            FileOutputStream file=new FileOutputStream(nombreArchivo);
            ObjectOutputStream objectOut=new ObjectOutputStream(file);
            objectOut.writeObject(jugadores);
            objectOut.close();
            file.close();
        }catch(IOException e){
            System.out.println("error");
        }
    }
    
    public Jugadores[] leerJugadores(String nombreArchivo) {
        Jugadores[] lista=null;
        try{
            FileInputStream fileIn = new FileInputStream(nombreArchivo);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            lista=(Jugadores[])objectIn.readObject();
            objectIn.close();
            fileIn.close();
        }catch(IOException | ClassNotFoundException e) {
            System.out.println("error");
        }
        return lista;
    }
}
