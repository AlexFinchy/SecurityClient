package org.alexfinch;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClientWriter {

	ObjectOutputStream out;
	
	public ClientWriter(ObjectOutputStream out) {
		this.out = out;
		
	}
	
	public boolean writeObject(Object object) {
		
		try {
			out.writeObject(object);
			return true;
		} catch (IOException e) {
			Reconnector reconnector = new Reconnector();
			reconnector.start();
			return false;
		}
		
	}
	
}
