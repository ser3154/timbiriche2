package mvcTamanoTablero;

import com.mycompany.blackboard.Blackboard;
import com.mycompany.timbirichenetwork.Cliente;

public class ModeloTamanoTablero {
    private int tamañoSeleccionado;

    public int getTamañoSeleccionado() {
        return tamañoSeleccionado;
    }

    public void setTamañoSeleccionado(int tamañoSeleccionado) {
        this.tamañoSeleccionado = tamañoSeleccionado;
    }

    // Nuevo método según diagrama de secuencia
    public void configurarPartida(int tamaño, Blackboard blackboard, Cliente cliente) {
        this.tamañoSeleccionado = tamaño;
        // Publicar configuración en blackboard
        blackboard.publicar(this);
        // Notificar al servidor sobre la configuración (según diagrama)
        // El servidor será notificado más adelante en el flujo
    }
}