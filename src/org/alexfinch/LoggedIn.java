package org.alexfinch;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.function.LongBinaryOperator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class LoggedIn extends JPanel{

	public LoggedIn(UserInfo userInfo) {
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		ImageIcon App1Icon = null;
		ImageIcon LogoutIcon = null;
		try {
			LogoutIcon = new ImageIcon(ImageIO.read(classLoader.getResourceAsStream("LogoutIcon.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel Apps = new JPanel();
		Apps.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JButton App1 = new JButton("1");
		App1.setIcon(App1Icon);
		JButton App2 = new JButton("2");
		JButton App3 = new JButton("3");
		JButton App4 = new JButton("4");
		JButton App5 = new JButton("5");
		JButton App6 = new JButton("6");
		JButton App7 = new JButton("7");
		JButton App8 = new JButton("8");
		JButton App9 = new JButton("9");
		JButton Logout = new JButton("");
		Logout.setIcon(LogoutIcon);
		
		
		c.gridx = 0;
		c.gridy = 0;
		Apps.add(App1,c);
		c.gridx = 1;
		c.gridy = 0;
		Apps.add(App2,c);
		c.gridx = 2;
		c.gridy = 0;
		Apps.add(App3,c);
		c.gridx = 0;
		c.gridy = 1;
		Apps.add(App4,c);
		c.gridx = 1;
		c.gridy = 1;
		Apps.add(App5,c);
		c.gridx = 2;
		c.gridy = 1;
		Apps.add(App6,c);
		c.gridx = 0;
		c.gridy = 2;
		Apps.add(App7,c);
		c.gridx = 1;
		c.gridy = 2;
		Apps.add(App8,c);
		c.gridx = 2;
		c.gridy = 2;
		Apps.add(App9,c);
		c.gridx = 1;
		c.gridy = 3;
		Apps.add(Logout,c);
		
		
		Logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Logout Code
				Client.out.writeObject(LoginResponse.Logout);
				
				
			}
		});
		
		add(Apps);
		
	}
	
}
