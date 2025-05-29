package com.mycompany.blackboard;

import com.mycompany.timbirichenetwork.Evento;
import com.mycompany.timbirichenetwork.eventos.*;
import com.mycompany.blackboard.ks.*;

public class BlackboardBridge {

    private static Blackboard blackboard;
    private static ControlBlackboard control;
    
    // Registro de Knowledge Sources por tipo de evento
    private static final java.util.Map<Class<?>, java.util.List<java.util.function.Consumer<? super Evento>>> 
        eventHandlers = new java.util.concurrent.ConcurrentHashMap<>();

    public static void setBlackboard(Blackboard bb) {
        blackboard = bb;
        control = bb.getControl();
        inicializarKnowledgeSources();
    }
    
    /**
     * Inicializa y registra los Knowledge Sources según los diagramas
     */
    private static void inicializarKnowledgeSources() {
        if (control == null) return;
        
        // Registrar KS para diferentes tipos de eventos
        registrarHandlerEvento(EventoJugadorListo.class, 
            evento -> new KSEscucharJugadorListo().procesar((EventoJugadorListo) evento));
            
        registrarHandlerEvento(EventoIniciarJuego.class,
            evento -> new KSIniciarJuego().procesar((EventoIniciarJuego) evento));
            
        registrarHandlerEvento(EventoIniciarPartida.class,
            evento -> new KSIniciarPartida().procesar((EventoIniciarPartida) evento));
        
        System.out.println("Knowledge Sources inicializados en BlackboardBridge");
    }
    
    /**
     * Registra un handler para un tipo específico de evento
     */
    @SuppressWarnings("unchecked")
    private static <T extends Evento> void registrarHandlerEvento(
            Class<T> tipoEvento, 
            java.util.function.Consumer<? super Evento> handler) {
        eventHandlers.computeIfAbsent(tipoEvento, k -> new java.util.ArrayList<>()).add(handler);
    }

    /**
     * Punto de entrada principal para eventos desde la red
     * Mejorado para seguir el patrón de los diagramas
     */
    public static void recibirEventoDesdeRed(Evento evento) {
        if (evento == null || blackboard == null) {
            System.err.println("Evento nulo o blackboard no inicializado");
            return;
        }

        try {
            // 1. Publicar evento en el blackboard (actualiza el estado compartido)
            blackboard.publicarEvento(evento);
            
            // 2. Activar Knowledge Sources específicos para este tipo de evento
            Class<?> tipoEvento = evento.getClass();
            var handlers = eventHandlers.get(tipoEvento);
            
            if (handlers != null) {
                for (var handler : handlers) {
                    try {
                        handler.accept(evento);
                    } catch (Exception e) {
                        System.err.println("Error ejecutando handler para " + tipoEvento.getSimpleName() + ": " + e.getMessage());
                    }
                }
            } else {
                System.out.println("No hay handlers registrados para evento: " + tipoEvento.getSimpleName());
            }
            
        } catch (Exception e) {
            System.err.println("Error procesando evento desde red: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Método para enviar evento hacia la red (desde el blackboard)
     */
    public static void enviarEventoHaciaRed(Evento evento) {
        if (evento == null) return;
        
        // Aquí se integraría con el cliente de red
        // Por ahora solo log para debugging
        System.out.println("Enviando evento hacia red: " + evento.getClass().getSimpleName());
        
        // También publicar localmente para consistencia
        if (blackboard != null) {
            blackboard.publicarEvento(evento);
        }
    }
    
    /**
     * Obtiene estadísticas del bridge para debugging
     */
    public static String getEstadisticas() {
        StringBuilder sb = new StringBuilder();
        sb.append("BlackboardBridge Statistics:\n");
        sb.append("- Handlers registrados: ").append(eventHandlers.size()).append("\n");
        
        for (var entry : eventHandlers.entrySet()) {
            sb.append("  - ").append(entry.getKey().getSimpleName())
              .append(": ").append(entry.getValue().size()).append(" handlers\n");
        }
        
        if (blackboard != null) {
            sb.append("- Tipos de estado en blackboard: ").append(blackboard.getTiposEstado().size()).append("\n");
        }
        
        return sb.toString();
    }
    
    /**
     * Limpia recursos del bridge
     */
    public static void shutdown() {
        eventHandlers.clear();
        if (control != null) {
            control.shutdown();
        }
        System.out.println("BlackboardBridge shutdown completado");
    }
}