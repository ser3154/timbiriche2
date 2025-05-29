package com.mycompany.blackboard.ks;

import com.mycompany.blackboard.KnowledgeSourceBase;
import mvcLobby.ModeloLobbyJuego;

public class KSActivarBotonIniciar extends KnowledgeSourceBase {
    
    private final javax.swing.JButton boton;
    private java.util.List<com.mycompany.timbirichenetwork.modelo.Jugador> jugadores;
    
    public KSActivarBotonIniciar(javax.swing.JButton boton) {
        this.boton = boton;
        
        // Registrar este KS para activarse cuando cambie el modelo de lobby
        control.registrarKS(mvcLobby.ModeloLobbyJuego.class, this);
    }
    
    public void evaluar(java.util.List<com.mycompany.timbirichenetwork.modelo.Jugador> jugadores) {
        this.jugadores = jugadores;
        ejecutar();
    }
    
    @Override
    protected boolean puedeEjecutar() {
        return boton != null && jugadores != null;
    }
    
    @Override
    protected void procesar() {
        // Si no se proporcionaron jugadores directamente, obtener del modelo
        if (jugadores == null) {
            ModeloLobbyJuego modelo = obtenerEstadoSeguro(mvcLobby.ModeloLobbyJuego.class);
            if (modelo != null) {
                jugadores = modelo.getJugadores();
            }
        }
        
        if (jugadores != null) {
            KSEvaluarJugadoresListos evaluador = new KSEvaluarJugadoresListos();
            boolean habilitar = evaluador.validar(jugadores);
            
            // Actualizar botón en EDT
            javax.swing.SwingUtilities.invokeLater(() -> {
                boton.setEnabled(habilitar);
                System.out.println("Botón iniciar " + (habilitar ? "habilitado" : "deshabilitado"));
            });
        }
    }
}
