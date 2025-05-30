package com.mycompany.blackboard;

import blackboard.IV;
import com.mycompany.timbirichenetwork.Evento;

import java.util.*;

public class Blackboard {

    private final Map<Class<?>, List<IV<?>>> observadores = new HashMap<>();
    private final Map<Class<?>, Object> estadoCompartido = new HashMap<>();
    private static Blackboard instancia;

    public Blackboard() {
    }

    public static Blackboard getInstancia() {
        if (instancia == null) {
            instancia = new Blackboard();
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
        if (nuevoEstado == null) {
            return;
        }
        Class<?> tipo = nuevoEstado.getClass();
        estadoCompartido.put(tipo, nuevoEstado);
        System.out.println("[Blackboard] Publicando estado de tipo: " + tipo.getSimpleName());
        List<IV<?>> lista = observadores.getOrDefault(tipo, Collections.emptyList());
        for (IV<?> obs : lista) {
            try {
                ((IV<T>) obs).actualizar(nuevoEstado);
            } catch (ClassCastException ignored) {
            }
        }
    }

    public synchronized <T> Optional<T> obtenerEstado(Class<T> tipo) {
        Object valor = estadoCompartido.get(tipo);
        return Optional.ofNullable(tipo.cast(valor));
    }

    public synchronized void publicarEvento(Evento evento) {
        if (evento != null) {
            System.out.println("[Blackboard] Publicando evento: " + evento.getClass().getSimpleName());
            publicar(evento);
        }
    }

    public void forzarPublicacion(Class<?> claseModelo) {
        obtenerEstado(claseModelo).ifPresent(this::publicar);
    }

    public synchronized void limpiar() {
        observadores.clear();
        estadoCompartido.clear();
    }
}
