import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.awt.image.*;
import java.io.*;
@SuppressWarnings("serial")
public class LoginScreen extends JPanel implements ActionListener {
	
	JTextField username;
	JPasswordField password;
	WelcomeScreen afterReturnPressed;
	JButton returnButton, loginButton;
	JLabel wrongUsernameOrPassword;
	BufferedImage background;
	
	LoginScreen(WelcomeScreen w) {
		afterReturnPressed = w;
		this.setSize(new Dimension(1600, 900));
		this.setLayout(null);
		background = new BufferedImage(1600, 900, BufferedImage.TYPE_INT_RGB);
		WelcomeScreen.makeGradient(background, (float)290.0, (float)60.0, (float)0.5, (float)0.2, (float)0.6, (float)0.4);
		username = new JTextField();
		password = new JPasswordField();
		this.add(username);
		this.add(password);
		username.setBounds(100, 200, 200, 50);
		password.setBounds(100, 300, 200, 50);
		
		JLabel loginText = new JLabel() {
			public void paintComponent(Graphics g) {
				g.drawImage(WelcomeScreen.makeOutline(WelcomeScreen.makeGradient(new BufferedImage(1400, 200, BufferedImage.TYPE_INT_RGB), 
						(float)50.0, (float)50.0, (float)0.5, (float)0.2, (float)0.8, (float)0.2), "Login"), 0, 0, this);
			}
		};
		
		wrongUsernameOrPassword = new JLabel("Wrong Username or Password. Try logging in again.");
		wrongUsernameOrPassword.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		wrongUsernameOrPassword.setForeground(Color.RED);
		
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
		returnButton.setBounds(400, 400, 100, 50);
		
		loginButton = new JButton("sign in") {
			public void paintComponent(Graphics g) {
				g.setColor(new Color(0x20, 0xFF, 0x60));
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(Color.black);
				g.drawString(this.getText(), 25, this.getHeight() / 2 + 4);
			}
		};
		loginButton.setActionCommand("login");
		loginButton.addActionListener(this);
		this.add(loginButton);
		loginButton.setBounds(320, 300, 100, 50);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, this);
	}

	public static String getMD5(String input) {
        byte[] source;
        try {
            //Get byte according by specified coding.
            source = input.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            source = input.getBytes();
        }
        String result = null;
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            //The result should be one 128 integer
            byte temp[] = md.digest();
            char str[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = temp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            result = new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("return")) {
			afterReturnPressed.setScreen(afterReturnPressed.mainScreen);
		}
		else if(e.getActionCommand().equals("login")) {
			String g = getMD5(new String(username.getText()));
			try {
				File file = new File("users/" + g);
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String md5CheckPassword = reader.readLine();
				if(md5CheckPassword.equals(getMD5(new String(password.getPassword())))) {
					try {
						this.remove(wrongUsernameOrPassword);
					} catch(Exception x) {
						
					}
					afterReturnPressed.setScreen(afterReturnPressed.messageWriter);
					System.out.println("it works.");
				}
				else {
					this.add(wrongUsernameOrPassword);
					wrongUsernameOrPassword.setBounds(600, 100, 800, 100);
				}
			}
			catch(IOException x) {
				System.out.println("OOPS.");
				x.printStackTrace();
				this.add(wrongUsernameOrPassword);
				wrongUsernameOrPassword.setBounds(600, 100, 800, 100);
			}
		}
	}

}
