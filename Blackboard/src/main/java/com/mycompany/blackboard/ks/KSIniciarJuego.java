package com.mycompany.blackboard.ks;

import com.mycompany.blackboard.Blackboard;
import com.mycompany.timbirichenetwork.eventos.EventoIniciarJuego;
import com.mycompany.timbirichenetwork.modelo.Jugador;
import mvcJuego.ModeloJuego;
import javax.swing.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class KSIniciarJuego {

    private static final AtomicBoolean iniciado = new AtomicBoolean(false);

    public void procesar(EventoIniciarJuego evento) {
        List<Jugador> jugadores = evento.getJugadores();
        if (jugadores.size() < 2) {
            System.out.println("[KS] No hay suficientes jugadores para iniciar");
            return;
        }

        for (Jugador j : jugadores) {
            if (j.getAvatar() == null && j.getRutaAvatar() != null) {
                j.setAvatar(new ImageIcon(j.getRutaAvatar()));
            }
        }

        System.out.println("[KS] EventoIniciarJuego recibido. Iniciando partida.");
        ModeloJuego modelo = new ModeloJuego(jugadores, evento.getTamañoTablero());
        Blackboard blackboard = Blackboard.getInstancia();
        modelo.inicializarJuego(blackboard, null); // cliente no necesario
        blackboard.publicar(modelo);
        System.out.println("[KS] ModeloJuego publicado en Blackboard.");

        // Crear controlador de juego (solo una vez)
        if (iniciado.compareAndSet(
                false, true)) {
            SwingUtilities.invokeLater(() -> new mvcJuego.ControladorJuego());
        }
    }
}
