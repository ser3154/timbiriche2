/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.timbirichenetwork;


/**
 *
 * @author joseq
 */
public class EventoRed {
    public enum Tipo { JOIN, START, MOVE, TURN }

    private Tipo tipo;
    private String jugadorJson;   // JSON de Jugador (para JOIN/START)
    private int tamañoTablero;    // para START
    private int y1, x1, y2, x2;    // para MOVE
    private int turnoIndex;        // para TURN

    // Constructor vacío para Gson
    public EventoRed() {}

    // Fábricas estáticas para facilidad
    public static EventoRed join(String jugadorJson) {
        EventoRed e = new EventoRed();
        e.tipo = Tipo.JOIN;
        e.jugadorJson = jugadorJson;
        return e;
    }
    public static EventoRed start(String jugadoresJson, int tamaño) {
        EventoRed e = new EventoRed();
        e.tipo = Tipo.START;
        e.jugadorJson = jugadoresJson;
        e.tamañoTablero = tamaño;
        return e;
    }
    public static EventoRed move(int y1, int x1, int y2, int x2) {
        EventoRed e = new EventoRed();
        e.tipo = Tipo.MOVE;
        e.y1 = y1; e.x1 = x1; e.y2 = y2; e.x2 = x2;
        return e;
    }
    public static EventoRed turn(int turnoIndex) {
        EventoRed e = new EventoRed();
        e.tipo = Tipo.TURN;
        e.turnoIndex = turnoIndex;
        return e;
    }

    // getters & setters
    public Tipo getTipo() { return tipo; }
    public String getJugadorJson() { return jugadorJson; }
    public int getTamañoTablero() { return tamañoTablero; }
    public int getY1() { return y1; }
    public int getX1() { return x1; }
    public int getY2() { return y2; }
    public int getX2() { return x2; }
    public int getTurnoIndex() { return turnoIndex; }

    public void setTipo(Tipo tipo) { this.tipo = tipo; }
    public void setJugadorJson(String j) { this.jugadorJson = j; }
    public void setTamañoTablero(int t) { this.tamañoTablero = t; }
    public void setY1(int y1) { this.y1 = y1; }
    public void setX1(int x1) { this.x1 = x1; }
    public void setY2(int y2) { this.y2 = y2; }
    public void setX2(int x2) { this.x2 = x2; }
    public void setTurnoIndex(int ti) { this.turnoIndex = ti; }

}
