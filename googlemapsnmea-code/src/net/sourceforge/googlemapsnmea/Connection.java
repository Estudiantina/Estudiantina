package net.sourceforge.googlemapsnmea;

import java.io.*;

public interface Connection{
	public void connect(String portname) throws IOException;
	public String readLine() throws IOException;
	public boolean ready();
}
