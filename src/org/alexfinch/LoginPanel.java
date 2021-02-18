package org.alexfinch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class LoginPanel extends JPanel {
	String EnteredPin = new String();
	String CodeText = new String();
	int MaxCodeLength = 10;
		public LoginPanel(ArrayList<ImageIcon> Images) {
			setLayout(new BorderLayout());
			
			JTextField CodeView = new JTextField();
			CodeView.setEditable(false);
			CodeView.setBorder(null);
			CodeView.setColumns(6);
			setAutoscrolls(false);
			Font font = CodeView.getFont();
			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			attributes.put(TextAttribute.SIZE, 70);
			CodeView.setFont(font.deriveFont(attributes));
			JPanel CodeViewLogin = new JPanel(new BorderLayout());
			CodeView.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
			CodeViewLogin.add(BorderLayout.NORTH,CodeView);
			
			JPanel LoginSubmit = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
			JButton Submit = new JButton("Login");
			
			

			JButton Back = new JButton("Back");
			
			Back.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame frame = (JFrame) SwingUtilities.getRoot(SwingUtilities.getRoot(LoginPanel.this));
					frame.remove(Client.CurrentPanel);
					frame.add(BorderLayout.CENTER,Client.LockedGUI);
					Client.CurrentPanel = Client.LockedGUI;
					frame.repaint();
					frame.revalidate();
					
				}
			});
			
			LoginSubmit.add(Back);
			LoginSubmit.add(Submit);
			CodeViewLogin.add(BorderLayout.SOUTH,LoginSubmit);
			
			JPanel Keypad = new JPanel();
			Keypad.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			c.ipadx = 30;
			c.ipady = 30;
			c.insets = new Insets(5, 5, 5, 5);
			
			JButton Number1 = new JButton("1");
			JButton Number2 = new JButton("2");
			JButton Number3 = new JButton("3");
			JButton Number4 = new JButton("4");
			JButton Number5 = new JButton("5");
			JButton Number6 = new JButton("6");
			JButton Number7 = new JButton("7");
			JButton Number8 = new JButton("8");
			JButton Number9 = new JButton("9");
			JButton Number0 = new JButton("0");
			
			JButton Clear = new JButton("X");
			
			Clear.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					EnteredPin = new String();
					CodeText = new String();
					CodeView.setText(CodeText);
					
				}
			});
			
			Number1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(CodeView.getText().length() < 7) {
						EnteredPin = EnteredPin + Number1.getText();
						CodeText = CodeText + "*";
						CodeView.setText(CodeText);
					}

				}
			});
			
			Number2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(CodeView.getText().length() < 7) {
						EnteredPin = EnteredPin + Number2.getText();
						CodeText = CodeText + "*";
						CodeView.setText(CodeText);
					}

				}
			});
			
			Number3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(CodeView.getText().length() < 7) {
						EnteredPin = EnteredPin + Number3.getText();
						CodeText = CodeText + "*";
						CodeView.setText(CodeText);
					}

				}
			});
			
			Number4.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(CodeView.getText().length() < 7) {
						EnteredPin = EnteredPin + Number4.getText();
						CodeText = CodeText + "*";
						CodeView.setText(CodeText);
					}

				}
			});
			
			Number5.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(CodeView.getText().length() < 7) {
						EnteredPin = EnteredPin + Number5.getText();
						CodeText = CodeText + "*";
						CodeView.setText(CodeText);
					}

				}
			});
			
			Number6.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(CodeView.getText().length() < 7) {
						EnteredPin = EnteredPin + Number6.getText();
						CodeText = CodeText + "*";
						CodeView.setText(CodeText);
					}

				}
			});
			
			Number7.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(CodeView.getText().length() < 7) {
						EnteredPin = EnteredPin + Number7.getText();
						CodeText = CodeText + "*";
						CodeView.setText(CodeText);
					}

				}
			});
			
			Number8.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(CodeView.getText().length() < 7) {
						EnteredPin = EnteredPin + Number8.getText();
						CodeText = CodeText + "*";
						CodeView.setText(CodeText);
					}

				}
			});
			
			Number9.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(CodeView.getText().length() < 7) {
						EnteredPin = EnteredPin + Number9.getText();
						CodeText = CodeText + "*";
						CodeView.setText(CodeText);
					}

				}
			});
			
			Number0.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(CodeView.getText().length() < 7) {
						EnteredPin = EnteredPin + Number9.getText();
						CodeText = CodeText + "*";
						CodeView.setText(CodeText);	
					}

					
				}
			});
			
			c.gridx = 0;
			c.gridy = 0;
			Keypad.add(Number1,c);
			c.gridx = 1;
			c.gridy = 0;
			Keypad.add(Number2,c);
			c.gridx = 2;
			c.gridy = 0;
			Keypad.add(Number3,c);
			c.gridx = 0;
			c.gridy = 1;
			Keypad.add(Number4,c);
			c.gridx = 1;
			c.gridy = 1;
			Keypad.add(Number5,c);
			c.gridx = 2;
			c.gridy = 1;
			Keypad.add(Number6,c);
			c.gridx = 0;
			c.gridy = 2;
			Keypad.add(Number7,c);
			c.gridx = 1;
			c.gridy = 2;
			Keypad.add(Number8,c);
			c.gridx = 2;
			c.gridy = 2;
			Keypad.add(Number9,c);
			c.gridx = 1;
			c.gridy = 3;
			Keypad.add(Number0,c);
			c.gridx = 2;
			c.gridy = 3;
			Keypad.add(Clear,c);
			
			
			JLabel EnterCode = new JLabel("Enter Passcode:");
			EnterCode.setFont(new Font(EnterCode.getFont().getFontName(), Font.BOLD, 40));
			EnterCode.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
			Keypad.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			
			JPanel KeypadPanel = new JPanel();
			KeypadPanel.add(Keypad);
			KeypadPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 90));
			
			add(BorderLayout.CENTER,CodeViewLogin);
			add(BorderLayout.NORTH,EnterCode);
			add(BorderLayout.EAST,KeypadPanel);
			
			Submit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					//Try and Login/Use the given code.
					if(Client.Connected) {
						Pin pin = new Pin(EnteredPin);
						System.out.println("Writing pin");
						Client.out.writeObject(pin);
						System.out.println("Done writing pin");
							//Invalid Pin
							while(!Client.LoginAttemptCompleted);
							if(!Client.LoggedIn) {
								InvalidLogin(Keypad);
							}
							EnteredPin = new String();
							CodeText = new String();
							CodeView.setText(CodeText);
							
							Client.LoginAttemptCompleted = false;
					}

				}
			});
		}

		

		public void InvalidLogin(JPanel Keypad) {
			
			Keypad.setBackground(Color.RED);
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					Keypad.setBackground(null);
					
				}
			}, 500);
			
		}
		
}
