package MainServidor;

import com.mycompany.timbirichenetwork.Servidor;

public class MainServidor {

    public static void main(String[] args) {
        int puerto = 5000;
        new Servidor(puerto);
    }
}
