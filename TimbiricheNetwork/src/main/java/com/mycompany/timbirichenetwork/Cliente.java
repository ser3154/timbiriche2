package com.mycompany.timbirichenetwork;

import com.mycompany.blackboard.BlackboardBridge;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

    private Socket socket;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;

    public Cliente(String host, int puerto) {
        try {
            socket = new Socket(host, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            new Thread(this::escucharEventos).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void escucharEventos() {
        try {
            while (true) {
                Object recibido = entrada.readObject();
                if (recibido instanceof Evento evento) {
                    BlackboardBridge.recibirEventoDesdeRed(evento);
                }
            }
        } catch (Exception e) {
            System.out.println("Conexión cerrada.");
        }
    }

    public void enviarEvento(Evento evento) {
        try {
            salida.writeObject(evento);
            salida.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
