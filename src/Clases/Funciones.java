/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import static Clases.JuegoSequence.barajCantidadLabel;
import static Clases.JuegoSequence.cartasMano;
import static Clases.JuegoSequence.fichas;
import static Clases.JuegoSequence.ganeAmarillo;
import static Clases.JuegoSequence.ganeAzul;
import static Clases.JuegoSequence.ganeRojo;
import static Clases.JuegoSequence.ganeVerde;
import static Clases.JuegoSequence.mano;
import static Clases.JuegoSequence.minutesRemaining;
import static Clases.JuegoSequence.secondsRemaining;
import static Clases.JuegoSequence.tablero;
import static Clases.JuegoSequence.turnoActual;
import static Clases.JuegoSequence.ultimaCarta;
import static Clases.Usuarios.historial;
import static Main.Menu_Principal.jugadores;
import static Main.Crear_Usuario.usersGuardados;
import static Main.Menu_Principal.confi;
import static Main.Menu_Principal.jList1;
import static Main.Menu_Principal.modelo;
import java.awt.Color;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 *
 * @author adalb
 */
public class Funciones {
    public static String[][] cartas;
    public static String[] baraja;
    public static String carta,equipoActual,fichaActual;
    public static int turnos,barajaCantidad,posCarta,posJugador;
    public static boolean cambiar;
    
    public Funciones(){
        cartas=new String[10][10];
        baraja=new String[104];
        turnos=0;
        carta="";
        equipoActual="";
        fichaActual="";
        cambiar=true;
    }
    
    public boolean siExiste(String user){
        for (Usuarios usuario : usersGuardados) {
            if (usuario.getUser().equals(user)) {
                System.out.println("Usuario encontrado: "+user);
                return true;
            }
        }
        return false;
    }
    
    public static  void guardarUsers(ArrayList<Usuarios> usuarios, String nombreArchivo) {
        try{
            FileOutputStream file=new FileOutputStream(nombreArchivo);
            ObjectOutputStream objectOut=new ObjectOutputStream(file);
            objectOut.writeObject(usuarios);
            objectOut.close();
            file.close();
        }catch(IOException e){
            System.out.println("error");
        }
    }
    
    public static ArrayList<Usuarios> leerUsers(String nombreArchivo) {
        ArrayList<Usuarios> lista = null;
        try{
            FileInputStream fileIn = new FileInputStream(nombreArchivo);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            lista = (ArrayList<Usuarios>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        }catch(IOException | ClassNotFoundException e) {
            System.out.println("error");
        }
        return lista;
    }
    
    public void mostrarHistorial(String user){
        for(int indice=0;indice<usersGuardados.size();indice++){
            if(usersGuardados.get(indice).getUser().equals(user)){
                for(int contar=historial.size()-1;contar>=0;contar--){
                    modelo.addElement(usersGuardados.get(indice).getHistorial().get(contar));
                    jList1.setModel(modelo);
                }
            }
        }
    }
    
    private void intercambiarCarta(int fila){
        ImageIcon imagen1=new ImageIcon(getClass().getResource("/ImagesTablero/"+mano[fila].getCard()+".jpg"));
        Image Scalecard1=imagen1.getImage().getScaledInstance(30, 46, Image.SCALE_SMOOTH);
        imagen1=new ImageIcon(Scalecard1);
        mano[posCarta].setIcon(imagen1);
        mano[posCarta].changeCard(mano[fila].getCard());
        ImageIcon imagen2=new ImageIcon(getClass().getResource("/ImagesTablero/"+carta+".jpg"));
        Image Scalecard2=imagen2.getImage().getScaledInstance(30, 46, Image.SCALE_SMOOTH);
        imagen2=new ImageIcon(Scalecard2);
        mano[fila].setIcon(imagen2);
        mano[fila].changeCard(carta);
        carta="";
    }
    
    public void intercambiarFicha(int indice){
        String ficha=jugadores[indice].getFicha();
        String equipo=jugadores[indice].getEquipo();
        if(carta.contains("King")){
            if(confi.getEfectoDeReyes().equals("Intercambiar ficha")){
                for(int contar=0;contar<confi.getCantidadJugadores();contar++){
                    if(jugadores[contar].getEquipo().equals(equipoActual)){
                        jugadores[contar].setFicha(ficha);
                    }else{
                        jugadores[contar].setFicha(fichaActual);
                    }
                }
                JOptionPane.showMessageDialog(null, "El "+equipoActual+" recibio la ficha "+ficha+" y el "+equipo+" recibio la ficha "+fichaActual);
                equipoActual="";
                fichaActual="";
                carta="";
            }
        }else if(carta.contains("Queen")){
            if(confi.getEfectoDeReinas().equals("Intercambiar ficha")){
                for(int contar=0;contar<confi.getCantidadJugadores();contar++){
                    if(jugadores[contar].getEquipo().equals(equipoActual)){
                        jugadores[contar].setFicha(ficha);
                    }else{
                        jugadores[contar].setFicha(fichaActual);
                    }
                }
                JOptionPane.showMessageDialog(null, "El "+equipoActual+" recibio la ficha "+ficha+" y el "+equipo+" recibio la ficha "+fichaActual);
                equipoActual="";
                fichaActual="";
                carta="";
            }
        }
    }
    
    public void siguienteTurno(){
        cambiar=true;
        turnos++;
        if(turnos==confi.getCantidadJugadores()){
            turnos=0;
        }
        turnoActual=jugadores[turnos].getJugador();
        JOptionPane.showMessageDialog(null, "Es turno de: "+turnoActual);
        minutesRemaining=2;
        secondsRemaining=0;
    }
    
    public void ultimaCartaJugada(){
        ImageIcon imagen=new ImageIcon(getClass().getResource("/ImagesTablero/"+carta+".jpg"));
        Image Scalecard=imagen.getImage().getScaledInstance(200, 304, Image.SCALE_SMOOTH);
        imagen=new ImageIcon(Scalecard);
        ultimaCarta.setIcon(imagen);
    }
    
    public void ocultarCartas(){
        int cantidad=confi.getCantidadJugadores()*confi.getCantidadCartas();
        for(int indice=0;indice<cantidad;indice++){
            if(mano[indice].getPlayer().equals(turnoActual)){
                ImageIcon imagen=new ImageIcon(getClass().getResource("/ImagesTablero/"+mano[indice].getCard()+".jpg"));
                Image Scalecard=imagen.getImage().getScaledInstance(30, 46, Image.SCALE_SMOOTH);
                imagen=new ImageIcon(Scalecard);
                mano[indice].setIcon(imagen);
            }else{
                mano[indice].setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagesTablero/oculto.png")));
            }
        }
    }
    
    public void cambiarCarta(){
        cartasMano++;
        int cantidad=cartasMano+1;
        if(cartasMano==104){
            cantidad=confi.getCantidadJugadores()*confi.getCantidadCartas();
            cartasMano=0;
            Cartas();
        }
        barajaCantidad=104-cantidad;
        barajCantidadLabel.setText(barajaCantidad+"/104");
        ImageIcon imagen=new ImageIcon(getClass().getResource("/ImagesTablero/"+baraja[cartasMano]+".jpg"));
        Image Scalecard=imagen.getImage().getScaledInstance(30, 46, Image.SCALE_SMOOTH);
        imagen=new ImageIcon(Scalecard);
        mano[posCarta].setIcon(imagen);
    }
    
    private boolean confirmarTurno(int fila){
        if(mano[fila].getPlayer().equals(turnoActual)){
            return true;
        }
        return false;
    }
    
    public void JugarEnFicha(int fila, int columna){
        if(carta.equals("Jack of hearts")){
            if(fichas[fila][columna].getSePuedeBloquer()){
                tablero[fila][columna].changeColor("");
                fichas[fila][columna].setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "Ya no se puede bloquear esta casilla.");
            }
            carta="";
        }else if(carta.equals("Jack of spades")){
            if(fichas[fila][columna].getSePuedeBloquer()){
                tablero[fila][columna].changeColor("");
                fichas[fila][columna].setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "Ya no se puede bloquear esta casilla.");
            }
            carta="";
        }else{
            JOptionPane.showMessageDialog(null, "Esta casilla ya esta siendo usada.");
            carta="";
        }
        for(int f=0;f<10;f++){
            for(int c=0;c<10;c++){
                if(fichas[f][c].isVisible()){
                    if(fichas[f][c].getSePuedeBloquer()){
                        Border  border=BorderFactory.createLineBorder(Color.BLACK, 2);
                        fichas[f][c].setBorder(border);
                    }
                }else{
                    Border  border=BorderFactory.createLineBorder(Color.BLACK, 2);
                    tablero[f][c].setBorder(border);
                }
            }
        }
    }
     
    public void JugarEnMano(int indice,int fila){
        if(carta.contains("King")){
            if(confi.getEfectoDeReyes().equals("Intercambiar carta")){
                if(cambiar){
                    intercambiarCarta(fila);
                    cambiar=false;
                }
            }
        }else if(carta.contains("Queen")){
            if(confi.getEfectoDeReinas().equals("Intercambiar carta")){
                if(cambiar){
                    intercambiarCarta(fila);
                    cambiar=false;
                }
            }
        }else{
            if(confirmarTurno(fila)){
                carta=mano[fila].getCard();
                if(carta.contains("Jack of clubs")){
                    JOptionPane.showMessageDialog(null, "Puedes colocar esta carta en una casilla que no este bloqueada.");
                    for(int f=0;f<10;f++){
                        for(int c=0;c<10;c++){
                            if(fichas[f][c].isVisible()){

                            }else{
                                if(tablero[f][c].getSePuedeBloquer()){
                                    Border  border=BorderFactory.createLineBorder(Color.RED, 2);
                                    tablero[f][c].setBorder(border);
                                }
                            }
                        }
                    }
                }else if(carta.contains("Jack of diamonds")){
                    JOptionPane.showMessageDialog(null, "Puedes colocar esta carta en una casilla que no este bloqueada.");
                    for(int f=0;f<10;f++){
                        for(int c=0;c<10;c++){
                            if(fichas[f][c].isVisible()){

                            }else{
                                if(tablero[f][c].getSePuedeBloquer()){
                                    Border  border=BorderFactory.createLineBorder(Color.RED, 2);
                                    tablero[f][c].setBorder(border);
                                }
                            }
                        }
                    }
                }else if(carta.contains("Jack of hearts")){
                    JOptionPane.showMessageDialog(null, "Puedes eliminar una ficha en el tablero.");
                    for(int f=0;f<10;f++){
                        for(int c=0;c<10;c++){
                            if(fichas[f][c].isVisible()){
                                if(fichas[f][c].getSePuedeBloquer()){
                                    Border  border=BorderFactory.createLineBorder(Color.RED, 2);
                                    fichas[f][c].setBorder(border);
                                }
                            }
                        }
                    }
                }else if(carta.contains("Jack of spades")){
                    JOptionPane.showMessageDialog(null, "Puedes eliminar una ficha en el tablero.");
                    for(int f=0;f<10;f++){
                        for(int c=0;c<10;c++){
                            if(fichas[f][c].isVisible()){
                                if(fichas[f][c].getSePuedeBloquer()){
                                    Border  border=BorderFactory.createLineBorder(Color.RED, 2);
                                    fichas[f][c].setBorder(border);
                                }
                            }
                        }
                    }
                }else{
                    equipoActual=jugadores[indice].getEquipo();
                    fichaActual=jugadores[indice].getFicha();
                    posCarta=fila;
                    posJugador=indice;
                    for(int f=0;f<10;f++){
                        for(int c=0;c<10;c++){
                            if(tablero[f][c].getCard().equals(carta)){
                                if(fichas[f][c].isVisible()){
                                    Border  border=BorderFactory.createLineBorder(Color.RED, 2);
                                    fichas[f][c].setBorder(border);
                                }else{
                                    Border  border=BorderFactory.createLineBorder(Color.RED, 2);
                                    tablero[f][c].setBorder(border);
                                }
                            }else{
                                if(fichas[f][c].isVisible()){
                                    Border  border=BorderFactory.createLineBorder(Color.BLACK, 2);
                                    fichas[f][c].setBorder(border);
                                }else{
                                    Border  border=BorderFactory.createLineBorder(Color.BLACK, 2);
                                    tablero[f][c].setBorder(border);
                                }
                            }
                        }
                    }
                    if(carta.contains("King")){
                        if(confi.getEfectoDeReyes().equals("Intercambiar carta")){
                            if(cambiar){
                                JOptionPane.showMessageDialog(null, "Puede intercambiar esta carta con otra.");
                            }else{
                                JOptionPane.showMessageDialog(null, "Solo se puede intercambiar cartas una vez por turno.");
                            }
                        }else if(confi.getEfectoDeReyes().equals("Intercambiar ficha")){
                            JOptionPane.showMessageDialog(null, "Toque el nametag del jugador al que le quiere quitar la ficha.");
                        }
                    }else if(carta.contains("Queen")){
                        if(confi.getEfectoDeReinas().equals("Intercambiar carta")){
                            if(cambiar){
                                JOptionPane.showMessageDialog(null, "Puede intercambiar esta carta con otra.");
                            }else{
                                JOptionPane.showMessageDialog(null, "Solo se puede intercambiar cartas una vez por turno.");
                            }
                        }else if(confi.getEfectoDeReinas().equals("Intercambiar ficha")){
                            JOptionPane.showMessageDialog(null, "Toque el nametag del jugador al que le quiere quitar la ficha.");
                        }
                    }
                }
            }else{
                carta="";
                JOptionPane.showMessageDialog(null, "No es su turno de jugar");
            }
        }
    }
    
    public void JugarEnTablero(int fila, int columna){
        if(carta.equals("")){
            JOptionPane.showMessageDialog(null, "Haga click en una carta para agarrarla.");
        }else{
            if(carta.equals("Jack of clubs")){
                tablero[fila][columna].changeColor(jugadores[posJugador].getFicha());
                fichas[fila][columna].setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagesTablero/"+jugadores[posJugador].getFicha()+".png")));
                fichas[fila][columna].setVisible(true);
                fichas[fila][columna].yaNoSePuedeBloquear();
                carta="";
            }else if(carta.equals("Jack of diamonds")){
                tablero[fila][columna].changeColor(jugadores[posJugador].getFicha());
                fichas[fila][columna].setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagesTablero/"+jugadores[posJugador].getFicha()+".png")));
                fichas[fila][columna].setVisible(true); 
                fichas[fila][columna].yaNoSePuedeBloquear();
                carta="";
            }else{
                if(tablero[fila][columna].getCard().equals(carta)){
                    ultimaCartaJugada();
                    tablero[fila][columna].changeColor(jugadores[posJugador].getFicha());
                    fichas[fila][columna].setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagesTablero/"+jugadores[posJugador].getFicha()+".png")));
                    fichas[fila][columna].setVisible(true);
                    carta="";
                }else{
                    JOptionPane.showMessageDialog(null, "Carta Incorrecta");
                }
            }
            siguienteTurno();
            cambiarCarta();
            ocultarCartas();
            for(int f=0;f<10;f++){
                for(int c=0;c<10;c++){
                    if(tablero[f][c].getSePuedeBloquer()==true){
                        if(fichas[f][c].isVisible()){
                            Border  border=BorderFactory.createLineBorder(Color.BLACK, 2);
                            fichas[f][c].setBorder(border);
                        }else{
                            Border  border=BorderFactory.createLineBorder(Color.BLACK, 2);
                            tablero[f][c].setBorder(border);
                        }
                    }
                }
            }
        }
    }
    
    public void haGanado(){
        Date fecha=new Date();
        int rojos=0,verdes=0,azules=0,amarillos=0;
        Border  border=BorderFactory.createLineBorder(Color.GREEN, 2);
        for(int fila=0;fila<10;fila++){
            for(int columna=0;columna<10;columna++){
                try{
                    rojos=0;
                    verdes=0;
                    azules=0;
                    amarillos=0;
                    if(fila==0&&columna==0){
                        tablero[fila][columna].changeColor(tablero[fila+1][columna].getColor());
                    }else if(fila==0&&columna==9){
                        tablero[fila][columna].changeColor(tablero[fila+1][columna].getColor());
                    }else if(fila==9&&columna==0){
                        tablero[fila][columna].changeColor(tablero[fila+1][columna].getColor());
                    }else if(fila==9&&columna==9){
                        tablero[fila][columna].changeColor(tablero[fila+1][columna].getColor());
                    }
                    for(int contar=0;contar<5;contar++){
                        if(tablero[fila][columna].getColor().equals(tablero[fila+contar][columna].getColor())){
                            if(tablero[fila][columna].getSePuedeBloquer()&&tablero[fila+contar][columna].getSePuedeBloquer()){
                                if(tablero[fila][columna].getColor().equals("rojo")){
                                    rojos++;
                                }else if(tablero[fila][columna].getColor().equals("verde")){
                                    verdes++;
                                }else if(tablero[fila][columna].getColor().equals("azul")){
                                    azules++;
                                }else if(tablero[fila][columna].getColor().equals("amarillo")){
                                    amarillos++;
                                }
                            }
                        }
                    }
                }catch(Exception e){
            
                }
                if(rojos==5||verdes==5||azules==5||amarillos==5){
                    tablero[fila][columna].setBorder(border);
                    tablero[fila+1][columna].setBorder(border);
                    tablero[fila+2][columna].setBorder(border);
                    tablero[fila+3][columna].setBorder(border);
                    tablero[fila+4][columna].setBorder(border);
                    tablero[fila][columna].yaNoSePuedeBloquear();
                    tablero[fila+1][columna].yaNoSePuedeBloquear();
                    tablero[fila+2][columna].yaNoSePuedeBloquear();
                    tablero[fila+3][columna].yaNoSePuedeBloquear();
                    tablero[fila+4][columna].yaNoSePuedeBloquear();
                    fichas[fila][columna].yaNoSePuedeBloquear();
                    fichas[fila+1][columna].yaNoSePuedeBloquear();
                    fichas[fila+2][columna].yaNoSePuedeBloquear();
                    fichas[fila+3][columna].yaNoSePuedeBloquear();
                    fichas[fila+4][columna].yaNoSePuedeBloquear();
                    if(tablero[fila][columna].getColor().equals("rojo")){
                        ganeRojo++;
                    }else if(tablero[fila][columna].getColor().equals("verde")){
                        ganeVerde++;
                    }else if(tablero[fila][columna].getColor().equals("azul")){
                        ganeAzul++;
                    }else if(tablero[fila][columna].getColor().equals("amarillo")){
                        ganeAmarillo++;
                    }
                }
                try{
                    rojos=0;
                    verdes=0;
                    azules=0;
                    amarillos=0;
                    if(fila==0&&columna==0){
                        tablero[fila][columna].changeColor(tablero[fila][columna+1].getColor());
                    }else if(fila==0&&columna==9){
                        tablero[fila][columna].changeColor(tablero[fila][columna+1].getColor());
                    }else if(fila==9&&columna==0){
                        tablero[fila][columna].changeColor(tablero[fila][columna+1].getColor());
                    }else if(fila==9&&columna==9){
                        tablero[fila][columna].changeColor(tablero[fila][columna+1].getColor());
                    }
                    for(int contar=0;contar<5;contar++){
                        if(tablero[fila][columna].getColor().equals(tablero[fila][columna+contar].getColor())){
                            if(tablero[fila][columna].getSePuedeBloquer()&&tablero[fila][columna+contar].getSePuedeBloquer()){
                                if(tablero[fila][columna].getColor().equals("rojo")){
                                    rojos++;
                                }else if(tablero[fila][columna].getColor().equals("verde")){
                                    verdes++;
                                }else if(tablero[fila][columna].getColor().equals("azul")){
                                    azules++;
                                }else if(tablero[fila][columna].getColor().equals("amarillo")){
                                    amarillos++;
                                }
                            }
                        }
                    }
                }catch(Exception e){
            
                }
                if(rojos==5||verdes==5||azules==5||amarillos==5){
                    tablero[fila][columna].setBorder(border);
                    tablero[fila][columna+1].setBorder(border);
                    tablero[fila][columna+2].setBorder(border);
                    tablero[fila][columna+3].setBorder(border);
                    tablero[fila][columna+4].setBorder(border);
                    tablero[fila][columna].yaNoSePuedeBloquear();
                    tablero[fila][columna+1].yaNoSePuedeBloquear();
                    tablero[fila][columna+2].yaNoSePuedeBloquear();
                    tablero[fila][columna+3].yaNoSePuedeBloquear();
                    tablero[fila][columna+4].yaNoSePuedeBloquear();
                    fichas[fila][columna].yaNoSePuedeBloquear();
                    fichas[fila][columna+1].yaNoSePuedeBloquear();
                    fichas[fila][columna+2].yaNoSePuedeBloquear();
                    fichas[fila][columna+3].yaNoSePuedeBloquear();
                    fichas[fila][columna+4].yaNoSePuedeBloquear();
                    if(tablero[fila][columna].getColor().equals("rojo")){
                        ganeRojo++;
                    }else if(tablero[fila][columna].getColor().equals("verde")){
                        ganeVerde++;
                    }else if(tablero[fila][columna].getColor().equals("azul")){
                        ganeAzul++;
                    }else if(tablero[fila][columna].getColor().equals("amarillo")){
                        ganeAmarillo++;
                    }
                }
                try{
                    rojos=0;
                    verdes=0;
                    azules=0;
                    amarillos=0;
                    if(fila==0&&columna==0){
                        tablero[fila][columna].changeColor(tablero[fila+1][columna+1].getColor());
                    }else if(fila==0&&columna==9){
                        tablero[fila][columna].changeColor(tablero[fila+1][columna+1].getColor());
                    }else if(fila==9&&columna==0){
                        tablero[fila][columna].changeColor(tablero[fila+1][columna+1].getColor());
                    }else if(fila==9&&columna==9){
                        tablero[fila][columna].changeColor(tablero[fila+1][columna+1].getColor());
                    }
                    for(int contar=0;contar<5;contar++){
                        if(tablero[fila][columna].getColor().equals(tablero[fila+contar][columna+contar].getColor())){
                            if(tablero[fila][columna].getSePuedeBloquer()&&tablero[fila+contar][columna+contar].getSePuedeBloquer()){
                                if(tablero[fila][columna].getColor().equals("rojo")){
                                    rojos++;
                                }else if(tablero[fila][columna].getColor().equals("verde")){
                                    verdes++;
                                }else if(tablero[fila][columna].getColor().equals("azul")){
                                    azules++;
                                }else if(tablero[fila][columna].getColor().equals("amarillo")){
                                    amarillos++;
                                }
                            }
                        }
                    }
                }catch(Exception e){
            
                }
                if(rojos==5||verdes==5||azules==5||amarillos==5){
                    tablero[fila][columna].setBorder(border);
                    tablero[fila+1][columna+1].setBorder(border);
                    tablero[fila+2][columna+2].setBorder(border);
                    tablero[fila+3][columna+3].setBorder(border);
                    tablero[fila+4][columna+4].setBorder(border);
                    tablero[fila][columna].yaNoSePuedeBloquear();
                    tablero[fila+1][columna+1].yaNoSePuedeBloquear();
                    tablero[fila+2][columna+2].yaNoSePuedeBloquear();
                    tablero[fila+3][columna+3].yaNoSePuedeBloquear();
                    tablero[fila+4][columna+4].yaNoSePuedeBloquear();
                    fichas[fila][columna].yaNoSePuedeBloquear();
                    fichas[fila+1][columna+1].yaNoSePuedeBloquear();
                    fichas[fila+2][columna+2].yaNoSePuedeBloquear();
                    fichas[fila+3][columna+3].yaNoSePuedeBloquear();
                    fichas[fila+4][columna+4].yaNoSePuedeBloquear();
                    if(tablero[fila][columna].getColor().equals("rojo")){
                        ganeRojo++;
                    }else if(tablero[fila][columna].getColor().equals("verde")){
                        ganeVerde++;
                    }else if(tablero[fila][columna].getColor().equals("azul")){
                        ganeAzul++;
                    }else if(tablero[fila][columna].getColor().equals("amarillo")){
                        ganeAmarillo++;
                    }
                }
                try{
                    rojos=0;
                    verdes=0;
                    azules=0;
                    amarillos=0;
                    if(fila==0&&columna==0){
                        tablero[fila][columna].changeColor(tablero[fila+1][columna-1].getColor());
                    }else if(fila==0&&columna==9){
                        tablero[fila][columna].changeColor(tablero[fila+1][columna-1].getColor());
                    }else if(fila==9&&columna==0){
                        tablero[fila][columna].changeColor(tablero[fila+1][columna-1].getColor());
                    }else if(fila==9&&columna==9){
                        tablero[fila][columna].changeColor(tablero[fila+1][columna-1].getColor());
                    }
                    for(int contar=0;contar<5;contar++){
                        if(tablero[fila][columna].getColor().equals(tablero[fila+contar][columna-contar].getColor())){
                            if(tablero[fila][columna].getSePuedeBloquer()&&tablero[fila+contar][columna-contar].getSePuedeBloquer()){
                                if(tablero[fila][columna].getColor().equals("rojo")){
                                    rojos++;
                                }else if(tablero[fila][columna].getColor().equals("verde")){
                                    verdes++;
                                }else if(tablero[fila][columna].getColor().equals("azul")){
                                    azules++;
                                }else if(tablero[fila][columna].getColor().equals("amarillo")){
                                    amarillos++;
                                }
                            }
                        }
                    }
                }catch(Exception e){
            
                }
                if(rojos==5||verdes==5||azules==5||amarillos==5){
                    tablero[fila][columna].setBorder(border);
                    tablero[fila+1][columna-1].setBorder(border);
                    tablero[fila+2][columna-2].setBorder(border);
                    tablero[fila+3][columna-3].setBorder(border);
                    tablero[fila+4][columna-4].setBorder(border);
                    tablero[fila][columna].yaNoSePuedeBloquear();
                    tablero[fila+1][columna-1].yaNoSePuedeBloquear();
                    tablero[fila+2][columna-2].yaNoSePuedeBloquear();
                    tablero[fila+3][columna-3].yaNoSePuedeBloquear();
                    tablero[fila+4][columna-4].yaNoSePuedeBloquear();
                    fichas[fila][columna].yaNoSePuedeBloquear();
                    fichas[fila+1][columna-1].yaNoSePuedeBloquear();
                    fichas[fila+2][columna-2].yaNoSePuedeBloquear();
                    fichas[fila+3][columna-3].yaNoSePuedeBloquear();
                    fichas[fila+4][columna-4].yaNoSePuedeBloquear();
                    if(tablero[fila][columna].getColor().equals("rojo")){
                        ganeRojo++;
                    }else if(tablero[fila][columna].getColor().equals("verde")){
                        ganeVerde++;
                    }else if(tablero[fila][columna].getColor().equals("azul")){
                        ganeAzul++;
                    }else if(tablero[fila][columna].getColor().equals("amarillo")){
                        ganeAmarillo++;
                    }
                }
            }
        }
        if(ganeRojo==2){
            String equipoGanador="";
            for(int indice=0;indice<usersGuardados.size();indice++){
                for(int contar=0;contar<confi.getCantidadJugadores();contar++){
                    if(usersGuardados.get(indice).getUser().equals(jugadores[contar].getJugador())){
                        if(jugadores[contar].getFicha().equals("rojo")){
                            equipoGanador=jugadores[contar].getEquipo();
                            usersGuardados.get(indice)
                                    .addHistorial(jugadores[contar].getJugador()+" Gano usando las fichas de color: "+jugadores[contar].getFicha()+" en "+fecha);
                        }else{
                            usersGuardados.get(indice)
                                    .addHistorial(jugadores[contar].getJugador()+" Perdio usando las fichas de color: "+jugadores[contar].getFicha()+" en "+fecha);
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "GANO EL "+equipoGanador+"!!!");
        }else if(ganeVerde==2){
            String equipoGanador="";
            for(int indice=0;indice<usersGuardados.size();indice++){
                for(int contar=0;contar<confi.getCantidadJugadores();contar++){
                    if(usersGuardados.get(indice).getUser().equals(jugadores[contar].getJugador())){
                        if(jugadores[contar].getFicha().equals("verde")){
                            equipoGanador=jugadores[contar].getEquipo();
                            usersGuardados.get(indice)
                                    .addHistorial(jugadores[contar].getJugador()+" Gano usando las fichas de color: "+jugadores[contar].getFicha()+" en "+fecha);
                        }else{
                            usersGuardados.get(indice)
                                    .addHistorial(jugadores[contar].getJugador()+" Perdio usando las fichas de color: "+jugadores[contar].getFicha()+" en "+fecha);
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "GANO EL "+equipoGanador+"!!!");
        }else if(ganeAzul==2){
            String equipoGanador="";
            for(int indice=0;indice<usersGuardados.size();indice++){
                for(int contar=0;contar<confi.getCantidadJugadores();contar++){
                    if(usersGuardados.get(indice).getUser().equals(jugadores[contar].getJugador())){
                        if(jugadores[contar].getFicha().equals("azul")){
                            equipoGanador=jugadores[contar].getEquipo();
                            usersGuardados.get(indice)
                                    .addHistorial(jugadores[contar].getJugador()+" Gano usando las fichas de color: "+jugadores[contar].getFicha()+" en "+fecha);
                        }else{
                            usersGuardados.get(indice)
                                    .addHistorial(jugadores[contar].getJugador()+" Perdio usando las fichas de color: "+jugadores[contar].getFicha()+" en "+fecha);
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "GANO EL "+equipoGanador+"!!!");
        }else if(ganeAmarillo==2){
            String equipoGanador="";
            for(int indice=0;indice<usersGuardados.size();indice++){
                for(int contar=0;contar<confi.getCantidadJugadores();contar++){
                    if(usersGuardados.get(indice).getUser().equals(jugadores[contar].getJugador())){
                        if(jugadores[contar].getFicha().equals("amarillo")){
                            equipoGanador=jugadores[contar].getEquipo();
                            usersGuardados.get(indice)
                                    .addHistorial(jugadores[contar].getJugador()+" Gano usando las fichas de color: "+jugadores[contar].getFicha()+" en "+fecha);
                        }else{
                            usersGuardados.get(indice)
                                    .addHistorial(jugadores[contar].getJugador()+" Perdio usando las fichas de color: "+jugadores[contar].getFicha()+" en "+fecha);
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "GANO EL "+equipoGanador+"!!!");
        }
    }
    
    public void Cartas(){
        cartas[0][0]="comodin";
        cartas[0][1]="2 of spades";
        cartas[0][2]="3 of spades";
        cartas[0][3]="4 of spades";
        cartas[0][4]="5 of spades";
        cartas[0][5]="6 of spades";
        cartas[0][6]="7 of spades";
        cartas[0][7]="8 of spades";
        cartas[0][8]="9 of spades";
        cartas[0][9]="comodin";
        
        cartas[1][0]="6 of clubs";
        cartas[1][1]="5 of clubs";
        cartas[1][2]="4 of clubs";
        cartas[1][3]="3 of clubs";
        cartas[1][4]="2 of clubs";
        cartas[1][5]="Ace of hearts";
        cartas[1][6]="King of hearts";
        cartas[1][7]="Queen of hearts";
        cartas[1][8]="10 of hearts";
        cartas[1][9]="10 of spades";
        
        cartas[2][0]="7 of clubs";
        cartas[2][1]="Ace of spades";
        cartas[2][2]="2 of diamonds";
        cartas[2][3]="3 of diamonds";
        cartas[2][4]="4 of diamonds";
        cartas[2][5]="5 of diamonds";
        cartas[2][6]="6 of diamonds";
        cartas[2][7]="7 of diamonds";
        cartas[2][8]="9 of diamonds";
        cartas[2][9]="Queen of spades";
        
        cartas[3][0]="8 of clubs";
        cartas[3][1]="King of spades";
        cartas[3][2]="6 of clubs";
        cartas[3][3]="5 of clubs";
        cartas[3][4]="4 of clubs";
        cartas[3][5]="3 of clubs";
        cartas[3][6]="2 of clubs";
        cartas[3][7]="8 of diamonds";
        cartas[3][8]="8 of hearts";
        cartas[3][9]="King of spades";
        
        cartas[4][0]="9 of clubs";
        cartas[4][1]="Queen of spades";
        cartas[4][2]="7 of clubs";
        cartas[4][3]="6 of hearts";
        cartas[4][4]="5 of hearts";
        cartas[4][5]="4 of hearts";
        cartas[4][6]="Ace of hearts";
        cartas[4][7]="9 of diamonds";
        cartas[4][8]="7 of hearts";
        cartas[4][9]="Ace of spades";
        
        cartas[5][0]="10 of clubs";
        cartas[5][1]="10 of spades";
        cartas[5][2]="8 of clubs";
        cartas[5][3]="7 of hearts";
        cartas[5][4]="2 of hearts";
        cartas[5][5]="3 of hearts";
        cartas[5][6]="King of hearts";
        cartas[5][7]="10 of diamonds";
        cartas[5][8]="6 of hearts";
        cartas[5][9]="2 of diamonds";
        
        cartas[6][0]="Queen of clubs";
        cartas[6][1]="9 of spades";
        cartas[6][2]="9 of clubs";
        cartas[6][3]="8 of hearts";
        cartas[6][4]="9 of hearts";
        cartas[6][5]="10 of hearts";
        cartas[6][6]="Queen of hearts";
        cartas[6][7]="Queen of diamonds";
        cartas[6][8]="5 of hearts";
        cartas[6][9]="3 of diamonds";
        
        cartas[7][0]="King of clubs";
        cartas[7][1]="8 of spades";
        cartas[7][2]="10 of clubs";
        cartas[7][3]="Queen of clubs";
        cartas[7][4]="King of clubs";
        cartas[7][5]="Ace of clubs";
        cartas[7][6]="Ace of diamonds";
        cartas[7][7]="King of diamonds";
        cartas[7][8]="4 of hearts";
        cartas[7][9]="4 of diamonds";
        
        cartas[8][0]="Ace of clubs";
        cartas[8][1]="7 of spades";
        cartas[8][2]="6 of spades";
        cartas[8][3]="5 of spades";
        cartas[8][4]="4 of spades";
        cartas[8][5]="3 of spades";
        cartas[8][6]="2 of spades";
        cartas[8][7]="2 of hearts";
        cartas[8][8]="3 of hearts";
        cartas[8][9]="5 of diamonds";
        
        cartas[9][0]="comodin";
        cartas[9][1]="Ace of diamonds";
        cartas[9][2]="King of diamonds";
        cartas[9][3]="Queen of diamonds";
        cartas[9][4]="10 of diamonds";
        cartas[9][5]="9 of diamonds";
        cartas[9][6]="8 of diamonds";
        cartas[9][7]="7 of diamonds";
        cartas[9][8]="6 of diamonds";
        cartas[9][9]="comodin";
    }
    
    public void Baraja(){
        Set<Integer>numerosGenerados=new HashSet<>();
        Random random=new Random();
        String carta;
        baraja[0]="Ace of clubs";
        baraja[1]="2 of clubs";
        baraja[2]="3 of clubs";
        baraja[3]="4 of clubs";
        baraja[4]="5 of clubs";
        baraja[5]="6 of clubs";
        baraja[6]="7 of clubs";
        baraja[7]="8 of clubs";
        baraja[8]="9 of clubs";
        baraja[9]="10 of clubs";
        baraja[10]="Jack of clubs";
        baraja[11]="Queen of clubs";
        baraja[12]="King of clubs";
        baraja[13]="Ace of clubs";
        baraja[14]="2 of clubs";
        baraja[15]="3 of clubs";
        baraja[16]="4 of clubs";
        baraja[17]="5 of clubs";
        baraja[18]="6 of clubs";
        baraja[19]="7 of clubs";
        baraja[20]="8 of clubs";
        baraja[21]="9 of clubs";
        baraja[22]="10 of clubs";
        baraja[23]="Jack of clubs";
        baraja[24]="Queen of clubs";
        baraja[25]="King of clubs";
        
        baraja[26]="Ace of spades";
        baraja[27]="2 of spades";
        baraja[28]="3 of spades";
        baraja[29]="4 of spades";
        baraja[30]="5 of spades";
        baraja[31]="6 of spades";
        baraja[32]="7 of spades";
        baraja[33]="8 of spades";
        baraja[34]="9 of spades";
        baraja[35]="10 of spades";
        baraja[36]="Jack of spades";
        baraja[37]="Queen of spades";
        baraja[38]="King of spades";
        baraja[39]="Ace of spades";
        baraja[40]="2 of spades";
        baraja[41]="3 of spades";
        baraja[42]="4 of spades";
        baraja[43]="5 of spades";
        baraja[44]="6 of spades";
        baraja[45]="7 of spades";
        baraja[46]="8 of spades";
        baraja[47]="9 of spades";
        baraja[48]="10 of spades";
        baraja[49]="Jack of spades";
        baraja[50]="Queen of spades";
        baraja[51]="King of spades";
        
        baraja[52]="Ace of diamonds";
        baraja[53]="2 of diamonds";
        baraja[54]="3 of diamonds";
        baraja[55]="4 of diamonds";
        baraja[56]="5 of diamonds";
        baraja[57]="6 of diamonds";
        baraja[58]="7 of diamonds";
        baraja[59]="8 of diamonds";
        baraja[60]="9 of diamonds";
        baraja[61]="10 of diamonds";
        baraja[62]="Jack of diamonds";
        baraja[63]="Queen of diamonds";
        baraja[64]="King of diamonds";
        baraja[65]="Ace of diamonds";
        baraja[66]="2 of diamonds";
        baraja[67]="3 of diamonds";
        baraja[68]="4 of diamonds";
        baraja[69]="5 of diamonds";
        baraja[70]="6 of diamonds";
        baraja[71]="7 of diamonds";
        baraja[72]="8 of diamonds";
        baraja[73]="9 of diamonds";
        baraja[74]="10 of diamonds";
        baraja[75]="Jack of diamonds";
        baraja[76]="Queen of diamonds";
        baraja[77]="King of diamonds";
        
        baraja[78]="Ace of hearts";
        baraja[79]="2 of hearts";
        baraja[80]="3 of hearts";
        baraja[81]="4 of hearts";
        baraja[82]="5 of hearts";
        baraja[83]="6 of hearts";
        baraja[84]="7 of hearts";
        baraja[85]="8 of hearts";
        baraja[86]="9 of hearts";
        baraja[87]="10 of hearts";
        baraja[88]="Jack of hearts";
        baraja[89]="Queen of hearts";
        baraja[90]="King of hearts";
        baraja[91]="Ace of hearts";
        baraja[92]="2 of hearts";
        baraja[93]="3 of hearts";
        baraja[94]="4 of hearts";
        baraja[95]="5 of hearts";
        baraja[96]="6 of hearts";
        baraja[97]="7 of hearts";
        baraja[98]="8 of hearts";
        baraja[99]="9 of hearts";
        baraja[100]="10 of hearts";
        baraja[101]="Jack of hearts";
        baraja[102]="Queen of hearts";
        baraja[103]="King of hearts";
        
        for(int indice=0;indice<baraja.length;indice++){
            int numeroAleatorio=random.nextInt(104)+0; 
            if(numerosGenerados.add(numeroAleatorio)){
                carta=baraja[numeroAleatorio];
                baraja[numeroAleatorio]=baraja[indice];
                baraja[indice]=carta;
            }
        }
    }
}
