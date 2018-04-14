import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MessageWriter extends JPanel implements ActionListener {

	JTextField subject;
	JTextPane document;
	JButton sendButton, returnButton;
	
	MessageWriter() {
		this.setSize(new Dimension(1367, 768));
		this.setLayout(null);
		
		subject = new JTextField("Subject");
		this.add(subject);
		subject.setBounds(150, 50, 1000, 50);
		document = new JTextPane();
		this.add(document);
		document.setBounds(50, 120, 1200, 500);
		
		sendButton = new JButton("send") {
			public void paintComponent(Graphics g) {
				g.setColor(new Color(0x20, 0xFF, 0x60));
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(Color.black);
				g.drawString(this.getText(), 25, this.getHeight() / 2 + 4);
			}
		};
		sendButton.setActionCommand("send");
		sendButton.addActionListener(this);
		this.add(sendButton);
		sendButton.setBounds(1100, 700, 100, 50);
		
		returnButton = new JButton("return") {
			public void paintComponent(Graphics g) {
				g.setColor(new Color(0x20, 0xFF, 0x60));
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(Color.black);
				g.drawString(this.getText(), 33, this.getHeight() / 2 + 4);
			}
		};
		returnButton.setActionCommand("return");
		returnButton.addActionListener(this);
		this.add(returnButton);
		returnButton.setBounds(1250, 700, 100, 50);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("send")) {
			Email.sendFromGMail(subject.getText(), document.getText());
		}
	}

}
