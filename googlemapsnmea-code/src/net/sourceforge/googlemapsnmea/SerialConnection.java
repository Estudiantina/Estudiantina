/**
 * Serial connection source file
 * Provides GPS communication capabilities to Google Maps+NMEA
 * @author Will Hayworth
 */
package net.sourceforge.googlemapsnmea;

import gnu.io.*;
import java.io.*;

public class SerialConnection implements Connection{
	private InputStream inputstream;
	private byte[] serbuff;
	private int lastlength;
	public void connect(String portname) throws IOException{
		try{
			if(CommPortIdentifier.getPortIdentifier(portname).isCurrentlyOwned()){
				System.out.println("The port you've requested is already being used by somebody else.  Please close that application and relaunch, or select a different port.");
				return;
			}
			SerialPort myport = (SerialPort) CommPortIdentifier.getPortIdentifier(portname).open("net.sourceforge.googlemapsnmea.SerialConnection", 2500);
			myport.setSerialPortParams(4800, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			//Get I/O streams
			inputstream = myport.getInputStream();
		}catch(NoSuchPortException nspe){
			System.err.println("Could not find the port you specified.");
			nspe.printStackTrace();
		}catch(PortInUseException piue){
			System.err.println("The port you specified is already in use.");
			piue.printStackTrace();
		}catch(UnsupportedCommOperationException ucoe){
			System.err.println("Could not set the connection parameters.");
			ucoe.printStackTrace();
		}
	}
	public String readLine() throws IOException{
		serbuff = new byte[2048];
		lastlength = inputstream.read(serbuff);
		return new String(serbuff, 0, lastlength);
	}
	public boolean ready(){
		return lastlength > -1;
	}
	public static void main(String[] args) throws Exception{
		SerialConnection conn = new SerialConnection();
		conn.connect("COM1");
	}
}
