package org.alexfinch;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Client {
	
	public static Socket socket;
	
	public static ObjectOutputStream outputstream;
	
	public static ClientWriter out;
	
	public static ObjectInputStream in;
	
	volatile static Boolean LoggedIn = false;
	
	volatile static Boolean Connected = false;
	
	volatile static Boolean LoginAttemptCompleted = false;
	
	volatile static Boolean Muted = false;
	
	static Boolean AlarmTriggered = false;
	
	static ClientGUI clientGUI;
	
	static JPanel CurrentPanel;
	
	static JPanel loginPanel;
	
	static JPanel LockedGUI;
	
	static int ReconnectCountdown = 10;
	
	static TopBar topBar;
	
	static final DeviceType deviceType = DeviceType.Tablet;
	
	public static String UUID;
	
	public static void main(String[] args) {
		
		
		try {
			File IDFile = new File("UniqueID.txt");
			
			if(IDFile.exists()) {
				Scanner scanner = new Scanner(IDFile);
				if(scanner.hasNextLine()) {
					UUID = scanner.nextLine();
					scanner.close();
				} else {
					scanner.close();
					UUID = java.util.UUID.randomUUID().toString();
					FileWriter fileWriter = new FileWriter(IDFile);
					fileWriter.write(UUID);
					fileWriter.close();
				}
				scanner.close();
			} else {
				IDFile.createNewFile();
				
				UUID = java.util.UUID.randomUUID().toString();
				FileWriter fileWriter = new FileWriter(IDFile);
				fileWriter.write(UUID);
				fileWriter.close();
			}
			
			System.out.println(UUID);
			
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			ArrayList<ImageIcon> TopbarImageIcons = new ArrayList<>();
			ArrayList<ImageIcon> LoginPanelImageIcons = new ArrayList<>();
			ArrayList<ImageIcon> lockedGUIImageIcons = new ArrayList<>();
			ArrayList<ImageIcon> LoggedInImageIcons = new ArrayList<>();
			
			ImageIcon volumeicon = new ImageIcon(ImageIO.read(classLoader.getResourceAsStream("VolumeIcon.png")));
			ImageIcon Muteicon = new ImageIcon(ImageIO.read(classLoader.getResourceAsStream("MuteIcon.png")));
			ImageIcon Connectedicon = new ImageIcon(ImageIO.read(classLoader.getResourceAsStream("ConnectedIcon.png")));
			ImageIcon DisconnectedIconicon = new ImageIcon(ImageIO.read(classLoader.getResourceAsStream("DisconnectedIcon.png")));
			ImageIcon LockedIcon = new ImageIcon(classLoader.getResource("LockIcon.png"));
			
			TopbarImageIcons.add(Connectedicon);
			TopbarImageIcons.add(DisconnectedIconicon);
			TopbarImageIcons.add(Muteicon);
			TopbarImageIcons.add(volumeicon);

			
			lockedGUIImageIcons.add(LockedIcon);
		
			topBar = new TopBar(TopbarImageIcons);
			
			LockedGUI = new LockedGUI(lockedGUIImageIcons);
			
			loginPanel  = new LoginPanel(LoginPanelImageIcons);
			
			socket = new Socket("localhost", 1353);
			
			outputstream = new ObjectOutputStream(socket.getOutputStream());
			outputstream.flush();
			out = new ClientWriter(outputstream);
			in = new ObjectInputStream(socket.getInputStream());
			//Do a quicko handshake to prove you are an offical device
			out.writeObject(deviceType);
			out.writeObject(new UniqueKey());
			
			Connected = true;
			
		} catch (IOException e) {
			Reconnector reconnector = new Reconnector();
			reconnector.start();
			Connected = false;
		}
		
		
		
		try {
			new ClientGUI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	

}
