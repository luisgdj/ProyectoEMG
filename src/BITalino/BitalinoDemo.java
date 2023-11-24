package BITalino;


import java.util.Vector;

import javax.bluetooth.RemoteDevice;


import java.util.logging.Level;
import java.util.logging.Logger;

public class BitalinoDemo {

    public static Frame[] frame;

    public static void main(String[] args) {

        BITalino bitalino = null;
        try {
            bitalino = new BITalino();
            //Code to find Devices
            //Only works on some OS
            Vector<RemoteDevice> devices = bitalino.findDevices();
            System.out.println(devices);

            //MAC adress of our BITalino:
            String macAddress = "20:17:11:20:51:27";
            
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

                System.out.println("size block: " + frame.length);

                //Print the samples
                for (int j = 0; j < frame.length; j++) {
                    System.out.println((i * block_size + j) + " seq: " + frame[j].seq + " "
                            + frame[j].analog[0] + " "
                    );
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
