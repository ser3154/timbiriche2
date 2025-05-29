package com.mycompany.timbirichenetwork.eventos;

import com.mycompany.timbirichenetwork.Evento;
import com.mycompany.timbirichenetwork.modelo.Jugador;

import java.util.List;

public class EventoIniciarJuego extends Evento {

    private final List<Jugador> jugadores;
    private final int tamañoTablero;

    public EventoIniciarJuego(List<Jugador> jugadores, int tamañoTablero) {
        this.jugadores = jugadores;
        this.tamañoTablero = tamañoTablero;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public int getTamañoTablero() {
        return tamañoTablero;
    }
}
