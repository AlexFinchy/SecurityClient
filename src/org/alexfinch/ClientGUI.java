package org.alexfinch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class ClientGUI extends JFrame{
	
	public Receiving receiving;

	
	public ClientGUI() throws IOException {

		Client.clientGUI = this;
		//setUndecorated(true);
		setResizable(false);
		setSize(800,480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(BorderLayout.NORTH,Client.topBar);
		add(BorderLayout.CENTER,Client.LockedGUI);
		Client.CurrentPanel = Client.LockedGUI;
		repaint();
		revalidate();
		new AlarmMonitor();
		receiving = new Receiving();
		receiving.start();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	

}
