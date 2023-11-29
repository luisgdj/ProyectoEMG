package threads;

import java.net.SocketException;

//EXAMPLE MAIN (implementar en nuestra interfaz de usuario)
public class UdpThread {

    public static void main(String[] args) {
        /* Crear instancias de las clases que implementan Runnable:
    	int serverPort = 9000;
        ServerUDP server = new ServerUDP(serverPort);
        server.startServer();
        */
        
    	Object obj = null;
    	String data = obj.toString();
        Runnable clientUDP;
		try {
			clientUDP = new ClientUDP(9000, data);
			Runnable serverUDP = new ServerUDP(9000);
			
			// Crear hilos para ejecutar las instancias de Runnable
	        Thread clientThread = new Thread(clientUDP);
	        Thread serverThread = new Thread(serverUDP);

	        // Iniciar los hilos
	        clientThread.start();
	        serverThread.start();
	        
		} catch (SocketException e) {
			e.printStackTrace();
		}
    }
}




