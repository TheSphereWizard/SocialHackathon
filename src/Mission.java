import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Mission implements ActionListener{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mission window = new Mission();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mission() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1, 46, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{1, 14, 20, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Dashboard");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblMyGroups = new JLabel("My groups");
		GridBagConstraints gbc_lblMyGroups = new GridBagConstraints();
		gbc_lblMyGroups.insets = new Insets(0, 0, 5, 0);
		gbc_lblMyGroups.gridx = 4;
		gbc_lblMyGroups.gridy = 1;
		frame.getContentPane().add(lblMyGroups, gbc_lblMyGroups);
		
		JButton btnIm = new JButton("IM");
		GridBagConstraints gbc_btnIm = new GridBagConstraints();
		gbc_btnIm.insets = new Insets(0, 0, 5, 5);
		gbc_btnIm.gridx = 1;
		gbc_btnIm.gridy = 2;
		frame.getContentPane().add(btnIm, gbc_btnIm);
		
		JButton btnMessage = new JButton("Message");
		GridBagConstraints gbc_btnMessage = new GridBagConstraints();
		gbc_btnMessage.insets = new Insets(0, 0, 5, 5);
		gbc_btnMessage.gridx = 1;
		gbc_btnMessage.gridy = 3;
		frame.getContentPane().add(btnMessage, gbc_btnMessage);
		
		JButton btnGroups = new JButton("Groups");
		GridBagConstraints gbc_btnGroups = new GridBagConstraints();
		gbc_btnGroups.insets = new Insets(0, 0, 5, 5);
		gbc_btnGroups.gridx = 1;
		gbc_btnGroups.gridy = 4;
		frame.getContentPane().add(btnGroups, gbc_btnGroups);
		
		JButton btnContacts = new JButton("Contacts");
		GridBagConstraints gbc_btnContacts = new GridBagConstraints();
		gbc_btnContacts.insets = new Insets(0, 0, 5, 5);
		gbc_btnContacts.gridx = 1;
		gbc_btnContacts.gridy = 5;
		frame.getContentPane().add(btnContacts, gbc_btnContacts);
		
		JButton btnProfile = new JButton("Profile");
		GridBagConstraints gbc_btnProfile = new GridBagConstraints();
		gbc_btnProfile.insets = new Insets(0, 0, 5, 5);
		gbc_btnProfile.gridx = 1;
		gbc_btnProfile.gridy = 6;
		frame.getContentPane().add(btnProfile, gbc_btnProfile);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Groups") {
			
		}
	}

}
