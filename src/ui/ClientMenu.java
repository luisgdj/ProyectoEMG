package ui;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.bluetooth.RemoteDevice;

import BITalino.BITalino;
import BITalino.BITalinoException;
import BITalino.BitalinoDemo;
import threads.ClientTCP;

public abstract class ClientMenu {

	public static void menu(String email) {

		File file = null;
		LinkedList<String> symptoms = null;
		while (true) {
			//ALS: Amyotrophic Lateral Sclerosis (ELA)
			System.out.println("\nPatient ALS diagnostic menu: (patient: " + email + ")" 
					+ "\n 1. Register new data"
					+ "\n 2. Send data to server"
					+ "\n 3. Check doctor diagnostic"
					+ "\n 4. Change password"
					+ "\n 0. Log out");
			int option = Utilities.readInteger("Choose an option: ");

			switch (option) {
				case 1: {
					file = new File("misc\\FileForServer.txt");
					
					//introducir sintomas
					symptoms = new LinkedList<>();
					System.out.println(" -Symptoms (press x to exit): ");
					while(true) {
						String s = Utilities.readString("   ");
						if(s.equals("x")) {
							break;
						} else {
							symptoms.add(s);
						}
					}
					//meter datos en fichero
					
					String macAddress = Utilities.readString(" -Bitalino MAC address: ");
					//macAddress = "20:17:11:20:51:27"
					//guardar datos en un fichero (variable file)
					recordBitalinoData(file, macAddress);
					
					break;
				}
				case 2: {
					//enviar datos (file) al servidor
					if(file != null) {
						sendDataToServer();
					}else {
						System.out.println("ERROR: No data has been recorded yet.");
					}
					break;
				}
				case 3: {
					//recibir datos del servidor
					break;
				}
				case 4: {
					//cambiar contraseña
					break;
				}
				case 0: {
					System.out.println("Program terminated.");
					return;
				}
				default: {
					System.out.println(" ERROR: Invalid option.");
				}
			}
		}
	}

	//OPTION 2:
	private static void sendDataToServer() {
		//Sent file to server:
		String serverIP = "10.60.85.53";
		int port = 9000;
		try {
			Socket socket = new Socket(serverIP, port);
		    File file = new File("misc\\FileForServer.txt");
		    ClientTCP server = new ClientTCP(socket, file);
		    
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//OPTION 1:
	private static void recordBitalinoData(File file, String macAddress) {
		
		BITalino bitalino = null;
        try {
        	bitalino = new BITalino();
    		Vector<RemoteDevice> devices = bitalino.findDevices();
            System.out.println(devices);
            
        	//Sampling rate, should be 10, 100 or 1000, we choose 100 to be precisely (100 samples/s)
            int SamplingRate = 100;
            bitalino.open(macAddress, SamplingRate);
            //we create a connection with bitalino in channel A1, that corresponds to index 0 (related to EMG)           
            int[] channelsToAcquire = {0};
            bitalino.start(channelsToAcquire);

            //Read in total 10000000 times
            for (int i=0; i<10000000; i++) {
                //Each time read a block of 100 samples (same as sample rate)
                int block_size=100;
                frame = bitalino.read(block_size); //frame is an array of the samples measured
                bitalino.writeFrameToFile(file, frame);
                System.out.println("size block: " + frame.length);
                //Print the samples
                for (int j = 0; j < frame.length; j++) {
                    System.out.println((i * block_size + j) + " seq: " + frame[j].seq + " " + frame[j].analog[0] + " ");
                }
            }
            //stop acquisition
            bitalino.stop();
            
        } catch (BITalinoException ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                //The connection of bitalino is stoped when bluetooth is disconnected
                if (bitalino != null) {
                    bitalino.close();
                }
            } catch (BITalinoException ex) {
                Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	}
}