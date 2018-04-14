import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.swing.*;


public class CreateAccountScreen extends JPanel implements ActionListener {
	JTextField username;
	JPasswordField password, confirmPassword;
	WelcomeScreen afterReturnPressed;
	JButton returnButton, makeAccountButton;
	JLabel passwordsNotMatch;
	BufferedImage background;
	
	CreateAccountScreen(WelcomeScreen w) {
		afterReturnPressed = w;
		this.setSize(new Dimension(1600, 900));
		this.setLayout(null);
		background = new BufferedImage(1600, 900, BufferedImage.TYPE_INT_RGB);
		WelcomeScreen.makeGradient(background, (float)290.0, (float)60.0, (float)0.5, (float)0.2, (float)0.6, (float)0.4);
		username = new JTextField();
		password = new JPasswordField();
		this.add(username);
		this.add(password);
		JLabel usernameString = new JLabel("Username:");
		usernameString.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		usernameString.setForeground(Color.BLACK);
		this.add(usernameString);
		usernameString.setBounds(10, 200, 180, 50);
		username.setBounds(200, 200, 200, 50);
		JLabel passwordString = new JLabel("Password:");
		passwordString.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		passwordString.setForeground(Color.BLACK);
		this.add(passwordString);
		passwordString.setBounds(10, 300, 180, 50);
		password.setBounds(200, 300, 200, 50);
		confirmPassword = new JPasswordField();
		this.add(confirmPassword);
		confirmPassword.setBounds(200, 400, 200, 50);
		JLabel confirmPasswordString = new JLabel("Confirm Password:");
		confirmPasswordString.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		confirmPasswordString.setForeground(Color.BLACK);
		this.add(confirmPasswordString);
		confirmPasswordString.setBounds(10, 400, 180, 50);
		
		
		JLabel loginText = new JLabel() {
			public void paintComponent(Graphics g) {
				g.drawImage(WelcomeScreen.makeOutline(WelcomeScreen.makeGradient(new BufferedImage(1400, 200, BufferedImage.TYPE_INT_RGB), 
						(float)50.0, (float)50.0, (float)0.5, (float)0.2, (float)0.8, (float)0.2), "Create Account"), 0, 0, this);
			}
		};
		
		passwordsNotMatch = new JLabel("Passwords don't match.");
		passwordsNotMatch.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		passwordsNotMatch.setForeground(Color.RED);
		
		this.add(loginText);
		loginText.setBounds(100, 0, 1400, 200);
		
		returnButton = new JButton("return") {
			public void paintComponent(Graphics g) {
				g.setColor(new Color(0x20, 0xFF, 0x60));
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(Color.black);
				g.drawString(this.getText(), 25, this.getHeight() / 2 + 4);
			}
		};
		returnButton.setActionCommand("return");
		returnButton.addActionListener(this);
		this.add(returnButton);
		returnButton.setBounds(700, 700, 100, 50);
		
		makeAccountButton = new JButton("Make Account") {
			public void paintComponent(Graphics g) {
				g.setColor(new Color(0x20, 0xFF, 0x60));
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(Color.black);
				g.drawString(this.getText(), 10, this.getHeight() / 2 + 4);
			}
		};
		makeAccountButton.setActionCommand("Make Account");
		makeAccountButton.addActionListener(this);
		this.add(makeAccountButton);
		makeAccountButton.setBounds(200, 500, 100, 50);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("return")) {
			afterReturnPressed.setScreen(afterReturnPressed.mainScreen);
		}
		else if(e.getActionCommand().equals("Make Account")) {
			if(new String(new String(password.getPassword())).equals(new String(confirmPassword.getPassword()))) {
				try {
					this.remove(passwordsNotMatch);
				} catch(Exception x) {
					
				}
				try {
					File file = new File("users/" + LoginScreen.getMD5(username.getText()));
					BufferedWriter writer = new BufferedWriter(new FileWriter(file));
					String g = LoginScreen.getMD5(new String(password.getPassword()));
					writer.write(LoginScreen.getMD5(new String(password.getPassword())), 0, g.length());
					writer.close();
				}
				catch(IOException x) {
					x.printStackTrace();
				}
			}
			else {
				this.add(passwordsNotMatch);
				passwordsNotMatch.setBounds(800, 100, 500, 100);
			}
		}
	}

}
