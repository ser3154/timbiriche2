/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.timbirichenetwork.eventos;

import com.mycompany.timbirichenetwork.Evento;

/**
 *
 * @author joseq
 */
public class EventoIniciarPartida extends Evento {

    private final boolean aceptadoPorTodos;

    public EventoIniciarPartida(boolean aceptadoPorTodos) {
        this.aceptadoPorTodos = aceptadoPorTodos;
    }

    public boolean isAceptadoPorTodos() {
        return aceptadoPorTodos;
    }

    @Override
    public String toString() {
        return "EventoIniciarPartida{aceptadoPorTodos=" + aceptadoPorTodos + '}';
    }
}
