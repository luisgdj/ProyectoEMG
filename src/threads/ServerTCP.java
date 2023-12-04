package threads;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ServerTCP implements Runnable {
	
    private Socket socket;
    private int byteRead;

    public ServerTCP(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
    	
    	InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            while ((byteRead = inputStream.read()) != -1) {
                char caracter = (char) byteRead;
                if(byteRead == -1 || byteRead == 'x') {
                	System.out.println("Character reception finished!");
                	//break;
                }else {
                	System.out.print(caracter);
                }
            }
            inputStream.close();
        	socket.close();
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void releaseResourcesClient(InputStream inputStream, Socket socket) {
        try {
            inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
    }
}

