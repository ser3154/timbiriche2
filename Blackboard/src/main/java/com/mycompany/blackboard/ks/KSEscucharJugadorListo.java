package com.mycompany.blackboard.ks;

import com.mycompany.blackboard.Blackboard;
import com.mycompany.timbirichenetwork.eventos.EventoJugadorListo;
import com.mycompany.timbirichenetwork.modelo.Jugador;
import mvcLobby.ModeloLobbyJuego;

public class KSEscucharJugadorListo {

    public void procesar(EventoJugadorListo evento) {
        Jugador jugador = evento.getJugador();
        jugador.setListo(true);

        Blackboard bb = Blackboard.getInstancia();
        ModeloLobbyJuego modelo = bb.obtenerEstado(ModeloLobbyJuego.class)
                .orElseGet(() -> ModeloLobbyJuego.inicializarYPublicar(bb));

        System.out.println("[KS] EventoJugadorListo recibido desde red para: " + jugador.getNombre());
        modelo.agregarJugadorDesdeRed(jugador, bb);
        System.out.println("[KS] ModeloLobby actualizado y publicado.");
    }
}
