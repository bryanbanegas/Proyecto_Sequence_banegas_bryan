/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import static Clases.Funciones.baraja;
import static Clases.Funciones.barajaCantidad;
import static Clases.Funciones.cartas;
import static Main.Menu_Principal.jugadores;
import static Main.Menu_Principal.confi;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author adalb JuegoSequence
 */
//qt
public class JuegoSequence extends JPanel {
    public static Cartas[][] tablero=new Cartas[10][10], fichas=new Cartas[10][10];
    public static JLabel[] jugador=new JLabel[100];
    public static Cartas[] mano=new Cartas[100];
    public static String turnoActual;
    private Funciones funcion=new Funciones();
    private int x, Xmano, Ymano, Xtablero, Ytablero;
    public static int cartasMano,ganeRojo,ganeAmarillo,ganeAzul,ganeVerde,secondsRemaining,minutesRemaining;
    public static JLabel tiempo,ultimaCarta,label,barajCantidadLabel,fotoBaraja;
    private Timer timer;
    
    public JuegoSequence() {
        tiempo=new JLabel("Tiempo restante: 5 segundos");
        tiempo.setBounds(1300, 0, 500, 50);
        add(tiempo);
        startTimer();
        label=new JLabel("Ultima Carta usada:");
        label.setBounds(1300, 100, 500, 50);
        add(label);
        ultimaCarta=new JLabel();
        ultimaCarta.setBounds(1300, 140, 200, 304);
        ImageIcon imagen1=new ImageIcon(getClass().getResource("/ImagesTablero/baraja.jpg"));
        Image Scalecard1=imagen1.getImage().getScaledInstance(200, 304, Image.SCALE_SMOOTH);
        imagen1=new ImageIcon(Scalecard1);
        ultimaCarta.setIcon(imagen1);
        add(ultimaCarta);
        funcion.Cartas();
        funcion.Baraja();
        turnoActual=jugadores[0].getJugador();
        setLayout(null);        
        ganeRojo=0;
        ganeAzul=0;
        ganeVerde=0;
        ganeAmarillo=0;
        Xmano=0;
        Ymano=0;
        cartasMano=-1;
        for(int indice=0;indice<confi.getCantidadJugadores();indice++){
            final int jugadorActual=indice;
            if(confi.getCantidadJugadores()==8){
                if(indice==2){
                    Xmano+=100;
                    Ymano=0;
                }else if(indice==6){
                    Xmano+=100;
                    Ymano=0;
                }
            }else if(confi.getCantidadJugadores()==6){
                if(indice==2){
                    Xmano+=100;
                    Ymano=0;
                }
            }
            jugador[indice]=new JLabel(jugadores[indice].getEquipo()+": "+jugadores[indice].getJugador());
            jugador[indice].setBounds(Xmano, Ymano, 100, 50);
            jugador[indice].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    funcion.intercambiarFicha(jugadorActual);
                }
            });
            add(jugador[indice]);
            Ymano+=50;
            for(int contar=0;contar<confi.getCantidadCartas();contar++){
                final int cartasManoActual=cartasMano+1;
                cartasMano++;
                JOptionPane.showMessageDialog(null, jugadores[jugadorActual].getJugador());
                mano[cartasMano]=new Cartas(baraja[cartasManoActual], jugadores[jugadorActual].getJugador(), "",true);
                mano[cartasMano].setBounds(Xmano, Ymano, 30,46);
                ImageIcon imagen=new ImageIcon(getClass().getResource("/ImagesTablero/"+baraja[cartasManoActual]+".jpg"));
                Image Scalecard=imagen.getImage().getScaledInstance(30, 46, Image.SCALE_SMOOTH);
                imagen=new ImageIcon(Scalecard);
                mano[cartasMano].setIcon(imagen);
                add(mano[cartasMano]);
                mano[cartasManoActual].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        funcion.JugarEnMano(jugadorActual,cartasManoActual);
                    }
                });
                Ymano+=50;
            }
            if(indice==confi.getParaIndex()) {
                Xtablero=Xmano+100;
                x=Xtablero;
                Xmano+=920;
                Ymano=0;
            }
        }
        Ytablero=0;
        for(int rows=0;rows<10;rows++){
            final int filas=rows;
            for(int columns=0;columns<10;columns++){
                final int columnas=columns;
                fichas[rows][columns]=new Cartas("ficha","","",true);
                fichas[rows][columns].setBounds(Xtablero,Ytablero,50,76);
                fichas[rows][columns].setContentAreaFilled(false);
                fichas[rows][columns].setBorder(null);
                add(fichas[rows][columns]);
                fichas[filas][columnas].addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        funcion.JugarEnFicha(filas,columnas);
                        funcion.haGanado();
                    }
                });
                fichas[rows][columns].setVisible(false);
                tablero[rows][columns]=new Cartas(cartas[rows][columns],"","",true);
                tablero[rows][columns].setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagesTablero/"+cartas[rows][columns]+".jpg")));
                tablero[rows][columns].setBounds(Xtablero,Ytablero,50,76);
                Border  border=BorderFactory.createLineBorder(Color.BLACK, 2);
                tablero[rows][columns].setBorder(border);
                add(tablero[rows][columns]);
                tablero[filas][columnas].addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if(filas==0&&columnas==0){
                            JOptionPane.showMessageDialog(null, "No puedes poner fichas en esta casilla pero contara como si ya lo has hecho.");
                        }else if(filas==0&&columnas==9){
                            JOptionPane.showMessageDialog(null, "No puedes poner fichas en esta casilla pero contara como si ya lo has hecho.");
                        }else if(filas==9&&columnas==0){
                            JOptionPane.showMessageDialog(null, "No puedes poner fichas en esta casilla pero contara como si ya lo has hecho.");
                        }else if(filas==9&&columnas==9){
                            JOptionPane.showMessageDialog(null, "No puedes poner fichas en esta casilla pero contara como si ya lo has hecho.");
                        }else{
                            funcion.JugarEnTablero(filas,columnas);
                            funcion.haGanado();
                        }
                    }
                });
                Xtablero+=80;
            }
            Ytablero+=80;
            Xtablero=x;
        }
        int cantidad=confi.getCantidadJugadores()*confi.getCantidadCartas();
        barajaCantidad=104-cantidad;
        barajCantidadLabel=new JLabel(barajaCantidad+"/104");
        barajCantidadLabel.setBounds(1200, 100, 100, 50);
        add(barajCantidadLabel);
        fotoBaraja=new JLabel();
        fotoBaraja.setBounds(1150, 150, 100, 151);
        fotoBaraja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagesTablero/baraja.jpg")));
        add(fotoBaraja);
        funcion.ocultarCartas();
    }

    private void startTimer() {
        minutesRemaining=2;
        secondsRemaining=0;
        timer=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsRemaining--;
                if(secondsRemaining>=0) {
                    tiempo.setText("Tiempo restante: "+minutesRemaining+":"+secondsRemaining);
                }else{
                    minutesRemaining--;
                    if(minutesRemaining==-1){
                        minutesRemaining=2;
                        secondsRemaining=0;
                        JOptionPane.showMessageDialog(null, "Se acabo su turno.");
                        funcion.siguienteTurno(); 
                        tiempo.setText("Tiempo restante: "+minutesRemaining+":"+secondsRemaining);
                    }else{
                        secondsRemaining=59;
                        tiempo.setText("Tiempo restante: "+minutesRemaining+":"+secondsRemaining);
                    }
                }
            }
        });
        timer.start();
    }

    public static void Jugar(){
        JFrame frame=new JFrame("Juego Sequence");
        JuegoSequence juegosequence=new JuegoSequence();
        frame.getContentPane().add(juegosequence);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public static void main(String[] args){
        Jugar();
    }
}
