package mvcLobby;

import com.mycompany.blackboard.Blackboard;
import com.mycompany.timbirichenetwork.Cliente;
import com.mycompany.timbirichenetwork.modelo.Jugador;

public class ControladorLobbyJuego {

    private final ModeloLobbyJuego modelo;
    private final VistaLobby vista;
    private final Cliente cliente;
    private final Jugador jugadorLocal;
    private final int tamañoTablero;
    private final Blackboard blackboard;

    public ControladorLobbyJuego(Jugador jugadorHost, int tamañoTablero, Cliente cliente) {
        this.tamañoTablero = tamañoTablero;
        this.cliente = cliente;
        this.jugadorLocal = jugadorHost;
        this.blackboard = Blackboard.getInstancia();

        // Crear vista
        this.vista = new VistaLobby();

        // Obtener o crear modelo a través del modelo estático
        this.modelo = ModeloLobbyJuego.obtenerOInicializar(blackboard);

        // Registrar vista como observador
        blackboard.registrar(ModeloLobbyJuego.class, vista);

        // Agregar jugador al modelo
        modelo.agregarJugador(jugadorHost, blackboard, cliente);

        // Configurar listeners
        vista.getBtnIniciar().addActionListener(e -> solicitarInicioJuego());
        vista.getBtnEditarPerfil().addActionListener(e -> editarPerfil());
    }

    private void solicitarInicioJuego() {
        modelo.solicitarInicioJuego(blackboard, cliente, tamañoTablero);
    }

    private void editarPerfil() {
        new mvcEditarPerfil.ControladorEditarPerfil(jugadorLocal);
        modelo.actualizarJugador(jugadorLocal, blackboard, cliente);
    }
}
