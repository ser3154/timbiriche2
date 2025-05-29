package Main;


import com.mycompany.blackboard.Blackboard;
import com.mycompany.blackboard.BlackboardBridge;
import com.mycompany.blackboard.ControlBlackboard;
import com.mycompany.timbirichenetwork.Cliente;
import com.mycompany.timbirichenetwork.Protocolo;
import mvcRegistro.ControladorRegistro;

public class MainRegistro {

    public static void main(String[] args) {
        System.out.println("=== Iniciando Timbiriche con patrón Blackboard ===");
        
        try {
            // 1. Inicializar Blackboard (esto también inicializa el Control)
            Blackboard blackboard = Blackboard.getInstancia();
            ControlBlackboard control = blackboard.getControl();
            
            System.out.println("✓ Blackboard inicializado");
            
            // 2. Configurar el Bridge con el blackboard
            BlackboardBridge.setBlackboard(blackboard);
            System.out.println("✓ BlackboardBridge configurado");
            
            // 3. Crear y conectar cliente TCP
            Cliente cliente = crearCliente();
            
            // 4. Registrar shutdown hook para limpieza
            registrarShutdownHook();
            
            // 5. Iniciar flujo de registro
            System.out.println("✓ Iniciando interfaz de registro...");
            new ControladorRegistro(cliente);
            
            // 6. Log de estadísticas iniciales
            System.out.println("\n" + BlackboardBridge.getEstadisticas());
            
        } catch (Exception e) {
            System.err.println("Error durante la inicialización: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * Crea y configura el cliente de red con manejo de errores
     */
    private static Cliente crearCliente() {
        try {
            Cliente cliente = new Cliente(Protocolo.HOST_LOCAL, Protocolo.PUERTO_DEFECTO);
            System.out.println("✓ Cliente conectado a " + Protocolo.HOST_LOCAL + ":" + Protocolo.PUERTO_DEFECTO);
            return cliente;
        } catch (Exception e) {
            System.err.println("⚠ No se pudo conectar al servidor. Funcionando en modo offline.");
            System.err.println("  Motivo: " + e.getMessage());
            // Retornar un cliente que no haga nada en caso de error
            return new ClienteOffline();
        }
    }
    
    /**
     * Registra hook para limpieza al cerrar la aplicación
     */
    private static void registrarShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\n=== Cerrando aplicación ===");
            BlackboardBridge.shutdown();
            
            Blackboard blackboard = Blackboard.getInstancia();
            if (blackboard != null) {
                blackboard.limpiar();
            }
            
            System.out.println("✓ Limpieza completada");
        }));
    }
    
    /**
     * Cliente offline para casos donde no hay servidor disponible
     */
    private static class ClienteOffline extends Cliente {
        public ClienteOffline() {
            super(null, 0); // Constructor que no conecta
        }
        
        @Override
        public void enviarEvento(com.mycompany.timbirichenetwork.Evento evento) {
            // En modo offline, solo procesar localmente
            System.out.println("OFFLINE: Procesando evento localmente - " + 
                             evento.getClass().getSimpleName());
            BlackboardBridge.recibirEventoDesdeRed(evento);
        }
    }
}