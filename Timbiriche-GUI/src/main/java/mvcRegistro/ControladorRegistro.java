package mvcRegistro;

import com.mycompany.blackboard.Blackboard;
import com.mycompany.timbirichenetwork.Cliente;
import com.mycompany.timbirichenetwork.modelo.Jugador;
import mvcTamanoTablero.ControladorTamanoTablero;
import javax.swing.*;

public class ControladorRegistro {

    private final ModeloRegistro modelo;
    private final VistaRegistro vista;
    private final Cliente cliente;
    private final Blackboard blackboard;

    public ControladorRegistro(Cliente cliente) {
        this.cliente = cliente;
        this.blackboard = Blackboard.getInstancia();
        this.modelo = new ModeloRegistro();
        this.vista = new VistaRegistro();
        vista.getBtnRegistrar().addActionListener(e -> registrarJugador());
    }

    private void registrarJugador() {
        // Solicitar datos del jugador a la vista
        String nombre = vista.getNombre();
        String colorHex = vista.getColorHex();
        String rutaAvatar = vista.getRutaAvatar();

        // Configurar modelo con los datos
        modelo.setNombre(nombre);
        modelo.setColorHex(colorHex);
        modelo.setRutaAvatar(rutaAvatar);

        if (!modelo.datosValidos()) {
            JOptionPane.showMessageDialog(vista, "Completa todos los campos.");
            return;
        }

        // Crear jugador y publicar en blackboard
        Jugador jugador = new Jugador(modelo.getNombre(), modelo.getColorHex(),
                modelo.getRutaAvatar(), true);
        System.out.println("[Registro] Registrando jugador: " + jugador.getNombre());
        modelo.registrarJugador(cliente);

        vista.dispose();
        // Continuar al siguiente paso
        new ControladorTamanoTablero(jugador, cliente);
    }
}
