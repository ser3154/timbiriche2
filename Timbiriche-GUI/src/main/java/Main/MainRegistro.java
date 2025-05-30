package Main;


import com.mycompany.blackboard.Blackboard;
import com.mycompany.blackboard.BlackboardBridge;
import com.mycompany.timbirichenetwork.Cliente;
import com.mycompany.timbirichenetwork.Protocolo;
import mvcRegistro.ControladorRegistro;

public class MainRegistro {

    public static void main(String[] args) {
        // Inicializar Blackboard
        Blackboard blackboard = Blackboard.getInstancia();
        BlackboardBridge.setBlackboard(blackboard);

        // Crear cliente TCP y conectar al servidor
        Cliente cliente = new Cliente(Protocolo.HOST_LOCAL, Protocolo.PUERTO_DEFECTO);

        // Iniciar flujo de registro
        new ControladorRegistro(cliente);
    }
}
