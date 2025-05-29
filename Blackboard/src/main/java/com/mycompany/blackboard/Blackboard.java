package com.mycompany.blackboard;

import blackboard.IV;
import com.mycompany.timbirichenetwork.Evento;

import java.util.*;

/**
 * Blackboard mejorado que integra el Control según los diagramas
 */
public class Blackboard {

    private final Map<Class<?>, List<IV<?>>> observadores = new HashMap<>();
    private final Map<Class<?>, Object> estadoCompartido = new HashMap<>();
    private static Blackboard instancia;
    private ControlBlackboard control; // Referencia al control

    private Blackboard() {
        // Inicializar el control cuando se crea el blackboard
        this.control = ControlBlackboard.getInstancia();
    }

    public static Blackboard getInstancia() {
        if (instancia == null) {
            synchronized (Blackboard.class) {
                if (instancia == null) {
                    instancia = new Blackboard();
                }
            }
        }
        return instancia;
    }

    public synchronized <T> void registrar(Class<T> tipo, IV<T> observador) {
        observadores.computeIfAbsent(tipo, k -> new ArrayList<>()).add(observador);
        Object actual = estadoCompartido.get(tipo);
        if (actual != null) {
            observador.actualizar(tipo.cast(actual));
        }
    }

    public synchronized <T> void publicar(T nuevoEstado) {
        if (nuevoEstado == null) return;
        
        Class<?> tipo = nuevoEstado.getClass();
        Object estadoAnterior = estadoCompartido.put(tipo, nuevoEstado);
        
        // Notificar a observadores (Views)
        List<IV<?>> lista = observadores.getOrDefault(tipo, Collections.emptyList());
        for (IV<?> obs : lista) {
            try {
                ((IV<T>) obs).actualizar(nuevoEstado);
            } catch (ClassCastException ignored) {
                System.err.println("Error de cast en observador para tipo: " + tipo.getSimpleName());
            }
        }
        
        // Activar Knowledge Sources a través del Control
        // Solo si realmente cambió el estado
        if (!Objects.equals(estadoAnterior, nuevoEstado)) {
            control.evaluarActivacion(nuevoEstado);
        }
    }

    public synchronized <T> Optional<T> obtenerEstado(Class<T> tipo) {
        Object valor = estadoCompartido.get(tipo);
        try {
            return valor != null ? Optional.of(tipo.cast(valor)) : Optional.empty();
        } catch (ClassCastException e) {
            System.err.println("Error de cast al obtener estado para tipo: " + tipo.getSimpleName());
            return Optional.empty();
        }
    }

    public synchronized void publicarEvento(Evento evento) {
        if (evento != null) {
            publicar(evento);
        }
    }

    public void forzarPublicacion(Class<?> claseModelo) {
        obtenerEstado(claseModelo).ifPresent(this::publicar);
    }

    /**
     * Obtiene referencia al control para registro de KS
     */
    public ControlBlackboard getControl() {
        return control;
    }

    public synchronized void limpiar() {
        observadores.clear();
        estadoCompartido.clear();
        if (control != null) {
            control.shutdown();
        }
    }
    
    /**
     * Método para verificar si un tipo de estado existe
     */
    public synchronized boolean existeEstado(Class<?> tipo) {
        return estadoCompartido.containsKey(tipo);
    }
    
    /**
     * Obtiene todos los tipos de estado actualmente almacenados
     */
    public synchronized Set<Class<?>> getTiposEstado() {
        return new HashSet<>(estadoCompartido.keySet());
    }
}