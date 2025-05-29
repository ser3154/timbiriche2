/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.timbirichenetwork.modelo;

import java.io.Serializable;

/**
 *
 * @author joseq
 */
public class Cuadro implements Serializable {

    private int fila;
    private int columna;
    private Jugador jugador;

    public Cuadro(int fila, int columna, Jugador jugador) {
        this.fila = fila;
        this.columna = columna;
        this.jugador = jugador;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
