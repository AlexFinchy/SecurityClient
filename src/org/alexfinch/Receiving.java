
package org.alexfinch;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class Receiving extends Thread{
	
	@Override
	public void run() {

		
		
		Object object;
		try {
				System.out.println("Connected");
				while((object = Client.in.readObject()) != null) {
					System.out.println(object.toString());
					if(object instanceof LoginResponse) { //Check if object is a LoginResponse
						LoginResponse loginResponse = (LoginResponse) object;
						if(loginResponse == LoginResponse.Logout) {
							Client.out.writeObject("G");
							JFrame frame = Client.clientGUI;
							frame.remove(Client.CurrentPanel);
							frame.add(BorderLayout.CENTER, Client.LockedGUI);
							Client.CurrentPanel = Client.LockedGUI;
							frame.repaint();
							frame.revalidate();
							Client.LoggedIn = false;
						}
						
						else if(loginResponse == LoginResponse.UserFound) {
							UserInfo userinfo = (UserInfo) Client.in.readObject();
							System.out.println("User Recieved");
							//Login Dialog
							JFrame frame = Client.clientGUI;
							frame.remove(Client.CurrentPanel);
							LoggedIn loggedIn = new LoggedIn(userinfo);
							frame.add(BorderLayout.CENTER,loggedIn);
							Client.CurrentPanel = loggedIn;
							frame.revalidate();
							frame.repaint();
							Client.LoggedIn = true;
							Client.LoginAttemptCompleted = true;
						} else if(loginResponse == LoginResponse.ProtocolAccepted) {
							
						} else if(loginResponse == LoginResponse.UserNotFound) {
							Client.LoginAttemptCompleted = true;
						}
							
							

					} else if(object instanceof AlarmResponse) {
						
						AlarmResponse alarmResponse = (AlarmResponse) object;
						if(alarmResponse == AlarmResponse.AlarmManually | alarmResponse == AlarmResponse.AlarmTriggered) {
							Client.AlarmTriggered = true;
						} else if(alarmResponse == AlarmResponse.AlarmCancel) {
							Client.AlarmTriggered = false;
						}
						
					} else {
						
					}
					
					
				}

		} catch (ClassNotFoundException e)  {
			
		} catch(IOException e) {
			Reconnector reconnector = new Reconnector();
			reconnector.start();
		} catch(NullPointerException e) {
			//This means it  is null at the top but the reconnector will start anyway
		}
		
		
		
	}

}
