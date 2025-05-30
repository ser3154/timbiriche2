package com.mycompany.blackboard;

import com.mycompany.timbirichenetwork.Evento;
import com.mycompany.timbirichenetwork.eventos.*;
import com.mycompany.blackboard.ks.*;

public class BlackboardBridge {

    private static Blackboard blackboard;

    public static void setBlackboard(Blackboard bb) {
        blackboard = bb;
    }

    public static void recibirEventoDesdeRed(Evento evento) {
        if (evento == null || blackboard == null) return;

        blackboard.publicarEvento(evento);

        if (evento instanceof EventoJugadorListo) {
            new KSEscucharJugadorListo().procesar((EventoJugadorListo) evento);
        } else if (evento instanceof EventoIniciarJuego) {
            new KSIniciarJuego().procesar((EventoIniciarJuego) evento);
        } else if (evento instanceof EventoIniciarPartida) {
            new KSIniciarPartida().procesar((EventoIniciarPartida) evento);
        }
    }
}
