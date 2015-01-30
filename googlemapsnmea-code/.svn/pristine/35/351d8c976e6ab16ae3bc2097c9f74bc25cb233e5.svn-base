package net.sourceforge.googlemapsnmea;

import java.applet.*;
import netscape.javascript.*;

public class AppletImplementation extends Applet implements Client{
	JSObject myjs;
	public void init(){
		//System.out.println("In start");
		//JSObject.getWindow(this).eval("alert('Hello from Java!');");
		//Get the map as a JSObject
		myjs = (JSObject) JSObject.getWindow(this);
		myjs.eval("alert('Whee!');");
		/*myjs.eval("this.document.write('<script src=\"http://maps.google.com/maps?file=api&amp;v=2&amp;key=\" type=\"text/javascript\"></script>"+
				"<body onunload=\"GUnload()\">"+
				"<script>var map = new GMap2(document.getElementById(\"map\"));</script>"+
				"<script>map.setCenter(new GLatLng(40.7357, -74.1726), 13);</script>"+
				"<div id=\"map\" style=\"width: 500px; height: 300px\"></div></body>')");
		*///myjs.eval("");
		//myjs.eval("map.setCenter(new GLatLng(37.4419, -122.1419), 13)");
		//myjs.eval("");
		//myjs.eval("map.setCenter(new GLatLng(40.735722, -74.172699), true);");
		/*String[] args = new String[2];
		args[0] = "new GLatLng(40.735722, -74.172699)";
		args[1] = "true";
		myjs.call("setCenter", args);*/
	}
	public void close(){
		
	}
	public void sendUpdate(float[] location) throws Exception{
		if(location.length > 2) throw new Exception("Not enough arguments.");
		myjs.eval("map.setCenter(new GLatLng("+location[0]+", "+location[1]+"), true);");
	}
}
