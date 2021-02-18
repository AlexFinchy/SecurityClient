package org.alexfinch;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class LockedGUI extends JPanel{
	
	ArrayList<ImageIcon> Images;
	
	public LockedGUI(ArrayList<ImageIcon> Images) {
			this.Images = Images;
		

			JPanel CenterMiddle = new JPanel(new FlowLayout());
			

			
			setLayout(new GridBagLayout());
			
			
			JPanel Left = new JPanel(new BorderLayout());
			JLabel Welcome = new JLabel("Welcome!");
			Welcome.setFont(new Font(Welcome.getFont().getFontName(), Font.BOLD, 50));
			
			JPanel Right = new JPanel(new BorderLayout());
			JButton Login = new JButton();
			Login.setIcon(Images.get(0));
			Login.setBorder(null);
			Login.setContentAreaFilled(false);
			JPanel FlowPanel = new JPanel(new FlowLayout());
			FlowPanel.add(Login);
			
			Login.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					JFrame frame = (JFrame) SwingUtilities.getRoot(SwingUtilities.getRoot(LockedGUI.this));
					frame.remove(Client.CurrentPanel);
					frame.add(BorderLayout.CENTER,Client.loginPanel);
					Client.CurrentPanel = Client.loginPanel;
					frame.repaint();
					frame.revalidate();
					
				}
			});

			
			Right.add(BorderLayout.CENTER,FlowPanel);
			Welcome.setHorizontalAlignment(JLabel.CENTER);
			Welcome.setVerticalAlignment(JLabel.CENTER);
			Left.add(BorderLayout.CENTER,Welcome);
			Left.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 200));
			
			CenterMiddle.add(Left);
			CenterMiddle.add(Right);
			
			
			
			add(CenterMiddle,new GridBagConstraints());
		
		
	}
	

}
