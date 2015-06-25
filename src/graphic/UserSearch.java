package graphic;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JTextField;
import javax.swing.JButton;

import graphic.ImagePanel;
import graphic.Options;
import controllers.Application;
import controllers.CurrentProfileController;
import controllers.ProfileController;
import controllers.Session;
import domain.ControllerNotLoadedException;
import domain.SessionNotActiveException;

public class UserSearch extends JFrame {

	private static JPanel panel;
	private JTextField textField;
	private JButton btnBack;
	private CurrentProfileController currentProfile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserSearch frame = new UserSearch(null, null,true);
					frame.setVisible(true);
					frame.pack();
					frame.setSize(900, 602);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the Frame
	 * @param instance
	 * @param session
	 * @param language
	 */
	public UserSearch(final Application instance, final Session session, final boolean language) {
		
		try {
			currentProfile = instance.getCurrentProfileController();
		} catch (SessionNotActiveException e2) {
			e2.printStackTrace();
		}		
		
		Locale currentLocale;
		if(language){
			currentLocale = new Locale("en","US");  //$NON-NLS-1$ //$NON-NLS-2$
		}else{
			currentLocale = new Locale("es","AR"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		final ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);  //$NON-NLS-1$
		
		setTitle("TrekApp"); //$NON-NLS-1$
		setBounds(0, 0, 900, 601);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new ImagePanel(new ImageIcon("UserSearch.jpg").getImage()); //$NON-NLS-1$
		panel.setBackground(new Color(25, 25, 112));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setLayout(null);
		setContentPane(panel);
		
		final JLabel lblUserSearch = new JLabel();
		lblUserSearch.setForeground(new Color(240, 248, 255));
		lblUserSearch.setFont(new Font("Tahoma", Font.PLAIN, 20)); //$NON-NLS-1$
		lblUserSearch.setBounds(308, 85, 193, 47);
		panel.add(lblUserSearch);
		
		final JLabel lblName = new JLabel();
		lblName.setForeground(new Color(240, 248, 255));
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 17)); //$NON-NLS-1$
		lblName.setBounds(237, 162, 181, 25);
		panel.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(428, 165, 118, 25);
		panel.add(textField);
		textField.setColumns(10);
		
		final JButton btnCheckUser = new JButton();
		btnCheckUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(textField.getText()).isEmpty()){
					ProfileController profile = null;
					try {
						profile = instance.getCollectionController().getProfileControllerByUsername(textField.getText());
					} catch (SessionNotActiveException e1) {
						e1.printStackTrace();
					}
					if(profile != null){
						try {
							if(profile.getUsername().equals(currentProfile.getUsername())){
								Profile frame = new Profile(instance, 1, session, profile,language);
								frame.setVisible(true);
							    frame.pack();
							    frame.setSize(900, 620);
								close();
							}else{
								Profile frame = new Profile(instance, 2, session, profile,language);
								frame.setVisible(true);
							    frame.pack();
							    frame.setSize(900, 620);
								close();
							}
						} catch (SessionNotActiveException e1) {
							e1.printStackTrace();
						} catch (ControllerNotLoadedException e1) {
							e1.printStackTrace();
						}
						
					}else{
						JOptionPane.showMessageDialog(null, messages.getString("UserSearch.5"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}
				
			}
		});
		btnCheckUser.setBounds(342, 243, 135, 23);
		panel.add(btnCheckUser);
		
		btnBack = new JButton();
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Options frame = new Options(instance, session,language);
				frame.setVisible(true);
			    frame.pack();
			    frame.setSize(900, 620);
			    close();
			}
		});
		btnBack.setBounds(726, 518, 93, 23);
		panel.add(btnBack);
		
		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserSearch frame = new UserSearch(instance, session,false);
				frame.setVisible(true);
				frame.pack();
				frame.setSize(900, 602);
				close();
			}
		});
		ImageIcon imageS = new ImageIcon("SpanishFlag.jpg");  //$NON-NLS-1$
		panel.add(img);
		img.setIcon(imageS); 
		img.setSize(22,18); 
		img.setLocation(796,11); 
		img.setVisible(true); 
		
		JButton img2 = new JButton();
		img2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserSearch frame = new UserSearch(instance, session,true);
				frame.setVisible(true);
				frame.pack();
				frame.setSize(900, 602);
				close();
			}
		});

		ImageIcon imageE = new ImageIcon("EnglishFlag.jpg");  //$NON-NLS-1$
		panel.add(img2);
		img2.setIcon(imageE); 
		img2.setSize(22,18); 
		img2.setLocation(760,11); 
		img2.setVisible(true); 
		
		lblName.setText(messages.getString("UserSearch.6")); //$NON-NLS-1$
		btnCheckUser.setText(messages.getString("UserSearch.7")); //$NON-NLS-1$
		lblUserSearch.setText(messages.getString("UserSearch.8")); //$NON-NLS-1$
		btnBack.setText(messages.getString("UserSearch.9")); //$NON-NLS-1$
	}
	
	/**
	 * Closes a frame after an event
	 */
	public void close(){
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}

}
