package threads;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerUDP implements Runnable {
	
    private DatagramSocket socket;
    private static byte[] receiveBuffer;
    private static byte[] data;

    public ServerUDP(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    @Override
    public void run() {
    	
        try {
            while (true) {
                recibirYEnviarDatos();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }

    private void recibirYEnviarDatos() throws IOException {
    	
        receiveBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(receivePacket);
        
        //Obtener dirección IP del host que envio el datagram (cliente)
        InetAddress originHost = receivePacket.getAddress();
        int destinationPort = receivePacket.getPort();
        data = receivePacket.getData();

        //Procesar los datos recibidos, por ejemplo, imprimir el mensaje
        String dataString = new String(data, 0, receivePacket.getLength());
        System.out.println("Mensaje envidado desde " + originHost + ":"
        		+ "\n -Puerto: " + destinationPort
        		+ "\n -Mensaje recibido:" + dataString);

        /*
        //Enviar una respuesta al cliente (puedes personalizar según tus necesidades)
        byte[] sendData = "Respuesta desde el servidor".getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
        socket.send(sendPacket);
        */
    }
}

