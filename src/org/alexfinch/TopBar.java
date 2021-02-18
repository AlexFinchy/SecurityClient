package org.alexfinch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ResourceBundle.Control;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TopBar extends JPanel {


	
	ArrayList<ImageIcon> Images;
	
	JPanel Center;
	
	JPanel EastElements;
	
	JLabel AlarmText = new JLabel();
	
	Timer AlarmFlashing;
	
	Boolean AlarmStarted = false;
	
	public TopBar(ArrayList<ImageIcon> Images) {
			
		
			this.Images = Images;
		
			ImageIcon Connectedicon = this.Images.get(0);
			ImageIcon Disconnectedicon = this.Images.get(1);
			ImageIcon Mute = this.Images.get(2);
			ImageIcon Volume = this.Images.get(3);
		
			
			JLabel ControlPanel = new JLabel("Control Panel");
			ControlPanel.setFont(new Font(ControlPanel.getFont().getFontName(), Font.PLAIN, 20));
			ControlPanel.setForeground(Color.black);
			setLayout(new BorderLayout());
			JButton MuteButton = new JButton();
			
			JLabel Connection = new JLabel();
			
			if(Client.Connected) {
				Connection.setIcon(Connectedicon);
			} else {
				Connection.setIcon(Disconnectedicon);
				
			}
			
			Timer ConnnectionCheck = new Timer();
			ConnnectionCheck.schedule(new TimerTask() {
				
				@Override
				public void run() {
					if(!Client.Connected) {
						Connection.setIcon(Disconnectedicon);
						Connection.setText(String.valueOf(Client.ReconnectCountdown));
					} else {
						Connection.setIcon(Connectedicon);
						Connection.setText("");
					}
					
				}
			}, 500,500);
			
			add(BorderLayout.WEST,ControlPanel);
			EastElements = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			EastElements.add(Connection);
			EastElements.add(MuteButton);
			add(BorderLayout.EAST,EastElements);
			EastElements.setBackground(new Color(12, 138, 155));
			MuteButton.setBorder(null);
			MuteButton.setContentAreaFilled(false);
			MuteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(Client.Muted) {
						Client.Muted = false;
						MuteButton.setIcon(Volume);
					} else {
						Client.Muted = true;
						MuteButton.setIcon(Mute);
					}
					
				}
			});
			setBackground(new Color(12, 138, 155));
		
			
			Timer MuteCheck = new Timer();
			
			
			MuteButton.setIcon(Volume);
			//Something about the alarm
			Center = new JPanel(new FlowLayout(FlowLayout.CENTER));
			AlarmText.setText("");
			AlarmText.setHorizontalAlignment(SwingConstants.CENTER);
			AlarmText.setVerticalAlignment(SwingConstants.CENTER);
			AlarmText.setForeground(Color.BLACK);
			AlarmText.setFont(new Font(AlarmText.getFont().getName(), Font.BOLD, 25));
			Center.add(AlarmText);
			Center.setBackground(new Color(12, 138, 155));
			add(BorderLayout.CENTER,Center);
			
			
	}
	
	public TopBar Refresh() {
		return new TopBar(this.Images);
	}
	
	
	public void StartAlarm() {
		AlarmStarted = true;
		AlarmFlashing = new Timer();
		AlarmFlashing.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if(getBackground() == Color.red) {
					setBackground(new Color(12, 138, 155));
					Center.setBackground(new Color(12, 138, 155));
					EastElements.setBackground(new Color(12, 138, 155));
				} else {
					AlarmText.setText("SECURITY ALERT");
					setBackground(Color.red);
					EastElements.setBackground(Color.red);
					Center.setBackground(Color.red);
				}
				
				
			}
		}, 500,500);
		
		
		
		
	}
	
	public void StopAlarm() {
		if(AlarmStarted) {
			AlarmStarted = false;
			AlarmFlashing.cancel();
			AlarmFlashing = null;
			AlarmText.setText("");
			setBackground(new Color(12, 138, 155));
			Center.setBackground(new Color(12, 138, 155));
			EastElements.setBackground(new Color(12, 138, 155));
		}


		
	}
	
}
