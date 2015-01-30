package net.sourceforge.googlemapsnmea;

public interface Client {
	public void sendUpdate(float[] location) throws Exception;
	public void close();
}
