package net.sourceforge.googlemapsnmea;

import java.io.*;

public class NMEAParser {
	private Connection connection;
	private float latitude;
	private float longitude;
	public NMEAParser(Connection connection){
		this.connection = connection;
	}
	public void update(){
		try{
			while(true){
				this.parseNMEA(connection.readLine());	
			}
		}catch(IOException iex){
			System.err.println("Problem reading in the NMEA data from the GPS.");
			iex.printStackTrace();
		}
	}
	private void parseNMEA(String input){
		String[] temp = input.split(",");
		//Discard NMEA messages that are unrelated
		if(!temp[0].equals("$GPGLL")) return;
		//If the conditional below returns "true", we don't have a fix yet
		if(temp[1].equals("") || temp[3].equals("")) return;
		//If we've gotten here, we have a fix
		this.latitude = Float.parseFloat(temp[1]);
		this.longitude = Float.parseFloat(temp[3]);
		return;
	}
}
