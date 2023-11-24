package threads;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClientUDP implements Runnable {

	 private DatagramSocket socket;
	 private byte[] buffer;
	 private DatagramPacket createdPacket;
	 
	 public ClientUDP(int port, String data) throws SocketException {
		 this.socket = new DatagramSocket(port);
	     this.buffer = data.getBytes();
	 }
	
	@Override
	public void run() {
		
		try {
			InetAddress destination = InetAddress.getByName("localhost");
			createdPacket = new DatagramPacket(buffer, buffer.length, destination, 9000);
        	socket.send(createdPacket);
        	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null && !socket.isClosed()) {
                socket.close();
            }
		}   
	}
}
