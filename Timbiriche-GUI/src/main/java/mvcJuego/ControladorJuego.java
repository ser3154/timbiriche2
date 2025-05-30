package mvcJuego;

import com.mycompany.blackboard.Blackboard;
import java.util.Optional;

public class ControladorJuego {
    private final VistaJuego vista;
    private final Blackboard blackboard;
    private ModeloJuego modelo;

    public ControladorJuego() {
        this.blackboard = Blackboard.getInstancia();
        this.vista = new VistaJuego();
        
        // Registrar vista como observador del blackboard
        blackboard.registrar(ModeloJuego.class, vista);
        
        // Obtener modelo del blackboard
        Optional<ModeloJuego> modeloOpt = blackboard.obtenerEstado(ModeloJuego.class);
        if (modeloOpt.isPresent()) {
            this.modelo = modeloOpt.get();
            vista.actualizar(modelo);
        }
    }
}
