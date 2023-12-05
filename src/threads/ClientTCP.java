package threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientTCP implements Runnable {

	private Socket socket;
	private File file;
	 
	public ClientTCP(Socket socket, File file) throws SocketException {
		//serverIP = "10.60.85.53"
		//port = 900
		this.socket = socket;
	    this.file = file;
	}
	 
	@Override
	public void run() {
			
		try {
		    OutputStream outputStream = socket.getOutputStream();
		    BufferedReader br = new BufferedReader(new FileReader(file));
	
		        int character;
		        while ((character = br.read()) != -1) {
		        	System.out.println((char) character);
		            outputStream.write(character);
		            outputStream.flush();
		            try {
		                Thread.sleep(1);
		            } catch (InterruptedException ex) {
		                Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
		        outputStream.flush();
		        releaseResources(outputStream, br, socket);
		        System.exit(0);
		        
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }

	 private static void releaseResources(OutputStream outputStream, BufferedReader br, Socket socket) {
	     try {
	    	 try {
	    		 br.close();
	    	 } catch (IOException ex) {
	             Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
	         }
	         try {
	             outputStream.close();
	         } catch (IOException ex) {
	             Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
	         }
	         socket.close();

	     } catch (IOException ex) {
	         Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
	     }
	 }

	 
}
