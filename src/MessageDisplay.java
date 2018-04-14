import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;

public class MessageDisplay extends JPanel implements ActionListener { 
	ArrayList<JLabel> panels = new ArrayList<JLabel>();
	JPanel openedThing;
	JLabel subject;
	JEditorPane document;
	JButton sendButton, checkButton;
	WelcomeScreen wS;
	
	MessageDisplay(WelcomeScreen w) {
		this.setSize(new Dimension(1367, 768));
		this.setLayout(null);
		wS = w;
		
		sendButton = new JButton("write message") {
			public void paintComponen(Graphics g) {
				g.setColor(new Color(0x20, 0xFF, 0x60));
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(Color.black);
				g.drawString(this.getText(), 0, this.getHeight() / 2 + 4);
			}
		};
		sendButton.setActionCommand("send");
		sendButton.addActionListener(this);
		this.add(sendButton);
		sendButton.setBounds(1100, 700, 100, 50);
		
		checkButton = new JButton("check for email") {
			public void paintComponent(Graphics g) {
				g.setColor(new Color(0x20, 0xFF, 0x60));
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(Color.black);
				g.drawString(this.getText(), 0, this.getHeight() / 2 + 4);
			}
		};
		checkButton.setActionCommand("check");
		checkButton.addActionListener(this);
		this.add(checkButton);
		checkButton.setBounds(1250, 700, 100, 50);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		panels.clear();
		String msg = "";
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(new File(path)));
			char []buffer = {};
			fileReader.read(buffer);
			String allText = new String(buffer);
			int i=0;
			while(allText.indexOf("$") != -1) {
				String subject = allText.substring(0, allText.indexOf("$"));
				allText=allText.substring(allText.indexOf("$") + 1);
				String body = allText.substring(0, allText.indexOf("$"));
				allText=allText.substring(allText.indexOf("$") + 1);
				panels.add(new JLabel(subject + "\n\n" + body));
				i++;
				panels.get(panels.size()-1).setBounds( 50,100*i,800,100);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	String path = "users/mainUser/messages.txt";
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("send")) {
			wS.setScreen(wS.messageWriter);
		}
		else if(e.getActionCommand().equals("check")) {
			ArrayList<String[]> messages = Email.check();
			File file = new File(path);
			try {
			FileWriter w = new FileWriter(file);
			for(String[] s : messages) {
				w.append(s[0]+"$"+s[1]+"$");
			}
			} catch(Exception sgfde) {
			
			}
			//repaint();
		}
	}
}
