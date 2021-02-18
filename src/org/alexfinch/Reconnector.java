package org.alexfinch;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Reconnector extends Thread {

	@Override
	public void run() {
		System.out.println("Attempted to Reconnect");
		Client.Connected = false;
		JFrame frame = Client.clientGUI;
		try {
			frame.remove(Client.CurrentPanel);
			frame.add(BorderLayout.CENTER,Client.LockedGUI);
			Client.CurrentPanel = Client.LockedGUI;
			frame.repaint();
			frame.revalidate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				Client.ReconnectCountdown = Client.ReconnectCountdown -1;
				System.out.println(Client.ReconnectCountdown);
				if(Client.ReconnectCountdown <= 1) {
					try {
						Client.socket = new Socket("192.168.0.11", 1353);
						
						Client.outputstream = new ObjectOutputStream(Client.socket.getOutputStream());
						Client.outputstream.flush();
						Client.out = new ClientWriter(Client.outputstream);
						Client.in = new ObjectInputStream(Client.socket.getInputStream());
						//Do a quicko handshake to prove you are an offical device
						Client.out.writeObject(new UniqueKey());
						Client.Connected = true;
						Client.clientGUI.receiving = new Receiving();
						Client.clientGUI.receiving.start();
						Reconnector.this.interrupt();
						timer.cancel();
					} catch (UnknownHostException e) {
						Client.ReconnectCountdown = 10;
					} catch (IOException e) {
						Client.ReconnectCountdown = 10;
					}
				}
			}
		}, 1000,1000);
	}
}
