package com.mycompany.blackboard.ks;

import com.mycompany.timbirichenetwork.modelo.Jugador;

import java.util.List;

public class KSEvaluarJugadoresListos {

    public boolean validar(List<Jugador> jugadores) {
        long listos = jugadores.stream().filter(Jugador::isListo).count();
        return listos >= 2 && listos <= 4;
    }
}
