import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;

public class WelcomeScreen extends JFrame implements ActionListener {
	JLabel welcomeMessage;
	JPanel mainScreen;
	JButton loginButton, makeAccountButton;
	JButton createAccountButton;
	BufferedImage backgroundColor;
	LoginScreen loginScreen;
	CreateAccountScreen createAccountScreen;
	MessageWriter messageWriter;
	MessageDisplay messageDisplay;

	public static int getIntRGB(Color c) {
		return (c.getRed() <<16) | (c.getGreen() << 8) | (c.getBlue());
	}
	
	public static BufferedImage makeGradient(BufferedImage img, float hStart, float hAngle, float sStart, float sRange, float bStart, float bRange) {
		Color temp;
		for(int y = 0; y < img.getHeight(); y++) {
			for(int x = 0; x < img.getWidth(); x++) {
				temp = Color.getHSBColor((float)((hStart + hAngle *(1.0/((float) img.getWidth() + img.getHeight()))*(x + y))/360.0), (float)(sStart + sRange * ((1.0 / ((float) img.getWidth() + img.getHeight())) * (x + y))), (float)(bStart + bRange * ((1.0 / ((float) img.getWidth() + img.getHeight())) * (x + y) )));
				int color = getIntRGB(temp);
				img.setRGB(x, y, color);
			}
		}
		return img;
	}
	
	public static BufferedImage makeOutline(BufferedImage originalImage, String text) {
		final BufferedImage textImage = new BufferedImage(
				originalImage.getWidth(),
				originalImage.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = textImage.createGraphics();
		FontRenderContext frc = g.getFontRenderContext();
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 60);
		GlyphVector gv = font.createGlyphVector(frc, text);
		Rectangle2D box = gv.getVisualBounds();
		int xOff = 25+(int)-box.getX();
		int yOff = 80+(int)-box.getY();
		Shape shape = gv.getOutline(xOff,yOff);
		g.setClip(shape);
		g.drawImage(originalImage,0,0,null);
		g.setClip(null);
		g.setStroke(new BasicStroke(2f));
		g.setColor(Color.BLACK);
		g.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.draw(shape);

		g.dispose();
		
		return textImage;
	}
	
	WelcomeScreen() {
		messageWriter = new MessageWriter(this);
		messageDisplay = new MessageDisplay(this);
		loginScreen = new LoginScreen(this);
		createAccountScreen = new CreateAccountScreen(this);
		backgroundColor = new BufferedImage(1600, 900, BufferedImage.TYPE_INT_RGB);
		makeGradient(backgroundColor, (float)160.0, (float)120.0, (float)0.5, (float)0.2, (float)0.8, (float)0.2);
		this.setSize(new Dimension(1600, 900));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setName("Comms for Class");
		
		mainScreen = new JPanel(){
				public void paintComponent(Graphics g) {
					g.drawImage(backgroundColor, 0, 0, this);
				}
		};
		mainScreen.setSize(1600, 900);
		mainScreen.setLayout(null);
		this.setContentPane(mainScreen);
		
		BufferedImage originalImage = new BufferedImage(1000, 250, BufferedImage.TYPE_INT_RGB);
		makeGradient(originalImage, (float) 120, (float) 25, (float) 0.5, (float) 0.2, (float) 0.9, (float) 0.1);
        
        welcomeMessage = new JLabel() {
        	public void paintComponent(Graphics g) {
        		g.drawImage(makeOutline(originalImage, "Welcome to Comms for Class!"), 0, 0, this);
        	}
        };
		mainScreen.add(welcomeMessage);
		welcomeMessage.setBounds(100, 0, 1400, 200);
		this.setVisible(true);
		
		loginButton = new JButton("Sign in") {
			public void paintComponent(Graphics g) {
				g.setColor(new Color(0x20, 0xFF, 0x60));
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(Color.black);
				g.drawString(this.getText(), 25, this.getHeight() / 2 + 4);
			}
		};
		loginButton.setActionCommand("sign in");
		loginButton.addActionListener(this);
		mainScreen.add(loginButton);
		loginButton.setBounds(400, 400, 100, 50);
		
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
		mainScreen.add(makeAccountButton);
		makeAccountButton.setBounds(800, 400, 100, 50);
	}
	
	public void setScreen(JPanel j) {
		setContentPane(j);
		repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("sign in")) {
			setScreen(loginScreen);
		}
		else if(e.getActionCommand().equals("Make Account")) {
			setScreen(createAccountScreen);
		}
	}
	
	public static void main(String []args) {
		WelcomeScreen w = new WelcomeScreen();
	}
}
