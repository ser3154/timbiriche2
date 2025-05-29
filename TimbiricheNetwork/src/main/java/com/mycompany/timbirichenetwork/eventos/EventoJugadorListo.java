package com.mycompany.timbirichenetwork.eventos;

import com.mycompany.timbirichenetwork.Evento;
import com.mycompany.timbirichenetwork.modelo.Jugador;

public class EventoJugadorListo extends Evento {

    private final Jugador jugador;

    public EventoJugadorListo(Jugador jugador) {
        this.jugador = jugador;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
