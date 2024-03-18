/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author adalb
 */
public class Usuarios implements Serializable  {
    public String user;
    public String nombre;
    public String contra;
    public Date fecha;
    public int puntos;
    public static  ArrayList<String> historial;
    
    public Usuarios(String user, String nombre, String contra, Date fecha, int puntos, ArrayList<String> historial){
        this.user=user;
        this.nombre=nombre;
        this.contra=contra;
        this.fecha=fecha;
        this.puntos=puntos;
        this.historial=historial;
    }
    
    public String getUser(){
        return user;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getContraseña(){
        return contra;
    }
    
    public Date getFecha(){
        return fecha;
    }
    
    public int getPuntos(){
        return puntos;
    }
    
    public ArrayList<String> getHistorial(){
        return historial;
    }
    
    public void addHistorial(String historial){
        this.historial.add(historial);
    }
    
    public int addPuntos(){
        return puntos=puntos+3;
    }
}
