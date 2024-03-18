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
import java.util.ArrayList;

/**
 *
 * @author adalb
 */
public class GuradarConfiguracion implements Serializable {
    private int cantidadJugadores,cantidadCartas,paraIndex;
    private boolean configuracion;
    private String efectoDeReyes,efectoDeReinas;

    public GuradarConfiguracion(int cantidadJugadores, int cantidadCartas, int paraIndex, boolean configuracion, String efectoDeReyes, String efectoDeReinas) {
        this.cantidadJugadores = cantidadJugadores;
        this.cantidadCartas = cantidadCartas;
        this.paraIndex = paraIndex;
        this.configuracion = configuracion;
        this.efectoDeReyes = efectoDeReyes;
        this.efectoDeReinas = efectoDeReinas;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public int getCantidadCartas() {
        return cantidadCartas;
    }

    public int getParaIndex() {
        return paraIndex;
    }

    public boolean getConfiguracion() {
        return configuracion;
    }

    public String getEfectoDeReyes() {
        return efectoDeReyes;
    }

    public String getEfectoDeReinas() {
        return efectoDeReinas;
    }
    
    public void guardarConfiguracion(GuradarConfiguracion configuracion, String nombreArchivo) {
        try{
            FileOutputStream file=new FileOutputStream(nombreArchivo);
            ObjectOutputStream objectOut=new ObjectOutputStream(file);
            objectOut.writeObject(configuracion);
            objectOut.close();
            file.close();
        }catch(IOException e){
            System.out.println("error");
        }
    }
    
    public GuradarConfiguracion leerConfiguracion(String nombreArchivo) {
        GuradarConfiguracion confi=null;
        try{
            FileInputStream fileIn=new FileInputStream(nombreArchivo);
            ObjectInputStream objectIn=new ObjectInputStream(fileIn);
            confi=(GuradarConfiguracion)objectIn.readObject();
            objectIn.close();
            fileIn.close();
        }catch(IOException | ClassNotFoundException e) {
            System.out.println("error");
        }
        return confi;
    }            
}
