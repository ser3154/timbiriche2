package com.mycompany.blackboard.ks;

import com.mycompany.timbirichenetwork.modelo.Jugador;

import javax.swing.JButton;
import java.util.List;

public class KSActivarBotonIniciar {

    private final JButton boton;

    public KSActivarBotonIniciar(JButton boton) {
        this.boton = boton;
    }

    public void evaluar(List<Jugador> jugadores) {
        boolean habilitar = new KSEvaluarJugadoresListos().validar(jugadores);
        boton.setEnabled(habilitar);
    }
}
