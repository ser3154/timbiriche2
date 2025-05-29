package mvcJuego;

import com.mycompany.blackboard.Blackboard;

import java.util.Optional;

public class ControladorJuego {

    private final VistaJuego vista;
    private final Blackboard blackboard;

    public ControladorJuego() {
        this.blackboard = Blackboard.getInstancia();
        this.vista = new VistaJuego();

        blackboard.registrar(ModeloJuego.class, vista);

        Optional<ModeloJuego> modelo = blackboard.obtenerEstado(ModeloJuego.class);
        modelo.ifPresent(vista::actualizar);
    }
}
