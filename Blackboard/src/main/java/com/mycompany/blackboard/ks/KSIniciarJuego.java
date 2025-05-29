package com.mycompany.blackboard.ks;

import com.mycompany.blackboard.KnowledgeSourceBase;

public class KSIniciarJuego extends KnowledgeSourceBase {

    private com.mycompany.timbirichenetwork.eventos.EventoIniciarJuego eventoIniciar;
    private static final java.util.concurrent.atomic.AtomicBoolean iniciado
            = new java.util.concurrent.atomic.AtomicBoolean(false);

    public void procesar(com.mycompany.timbirichenetwork.eventos.EventoIniciarJuego evento) {
        this.eventoIniciar = evento;
        ejecutar();
    }

    @Override
    protected boolean puedeEjecutar() {
        return eventoIniciar != null
                && eventoIniciar.getJugadores() != null
                && !eventoIniciar.getJugadores().isEmpty()
                && !iniciado.get();
    }

    @Override
    protected void procesar() {
        var jugadores = eventoIniciar.getJugadores();

        // Configurar avatares si es necesario
        for (var jugador : jugadores) {
            if (jugador.getAvatar() == null && jugador.getRutaAvatar() != null) {
                jugador.setAvatar(new javax.swing.ImageIcon(jugador.getRutaAvatar()));
            }
        }

        // Crear modelo de juego
        mvcJuego.ModeloJuego modeloJuego = new mvcJuego.ModeloJuego(
                jugadores,
                eventoIniciar.getTamañoTablero()
        );

        // Publicar modelo
        publicarEstado(modeloJuego);

        // Iniciar controlador de juego
        if (iniciado.compareAndSet(false, true)) {
            javax.swing.SwingUtilities.invokeLater(() -> {
                new mvcJuego.ControladorJuego();
                System.out.println("Juego iniciado con " + jugadores.size() + " jugadores");
            });
        }
    }
}
