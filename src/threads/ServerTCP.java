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
                System.out.print(caracter);
                // What if I write in a second file instead???
            }
            inputStream.close();
        	socket.close();
        	
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	
        }
    }
}

