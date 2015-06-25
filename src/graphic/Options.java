package graphic;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controllers.Application;
import controllers.Session;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class Options extends JFrame {

	private static JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Options frame = new Options(null, null,true);
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
	public Options(final Application instance, final Session session, final boolean language) {
		
		Locale currentLocale;
		if(language){
			currentLocale = new Locale("en","US"); 
		}else{
			currentLocale = new Locale("es","AR");
		}
		final ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale); 
		
		setTitle("TrekApp"); //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new ImagePanel(new ImageIcon("Options.jpg").getImage()); //$NON-NLS-1$
		panel.setBackground(new Color(25, 25, 112));
		panel.setEnabled(false);
		panel.setLayout(null);
		setContentPane(panel);
		
		final JButton btnProfile = new JButton();
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile frame = new Profile(instance, 1 , session, null,language);
				frame.setVisible(true);
				frame.pack();
				frame.setSize(900, 620);
				close();
			}
		});
		btnProfile.setBounds(227, 144, 131, 29);
		panel.add(btnProfile);
		
		final JButton btnUserSearch = new JButton();
		btnUserSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserSearch frame = new UserSearch(instance,session, language);
				frame.setVisible(true);
				frame.pack();
				frame.setSize(900, 620);
				close();
			}
		});
		btnUserSearch.setBounds(491, 144, 162, 29);
		panel.add(btnUserSearch);
		
		final JButton btnTripSearch = new JButton();
		btnTripSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TripSearch frame = new TripSearch(instance,session, language);
				frame.setVisible(true);
			    frame.pack();
			    frame.setSize(900, 602);
				close();
			}
		});
		btnTripSearch.setBounds(491, 242, 166, 29);
		panel.add(btnTripSearch);
		
		final JLabel lblChoice = new JLabel();
		lblChoice.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoice.setFont(new Font("Tahoma", Font.PLAIN, 22)); //$NON-NLS-1$
		lblChoice.setForeground(Color.WHITE);
		lblChoice.setBounds(300, 58, 238, 29);
		panel.add(lblChoice);
		
		final JButton btnSignOut = new JButton();
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(instance != null){
					session.logOut();
					TrekApp frame = new TrekApp(language);
					frame.setVisible(true);
					frame.pack();
				    frame.setSize(900, 602);
					close();
				}
			}
		});
		btnSignOut.setBounds(361, 367, 131, 29);
		panel.add(btnSignOut);
		
		final JButton btnNewGroup = new JButton();
		btnNewGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Group frame = new Group(0,null,null,null, instance, session,null, language);
				frame.setVisible(true);
				frame.pack();
				frame.setSize(900, 602);
				close();
			}
		});
		btnNewGroup.setBounds(227, 242, 131, 29);
		panel.add(btnNewGroup);		
		
		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Options frame = new Options(instance, session,false);
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
				Options frame = new Options(instance, session,true);
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
		
		
		btnNewGroup.setText(messages.getString("Options.6")); //$NON-NLS-1$
		btnSignOut.setText(messages.getString("Options.7")); //$NON-NLS-1$
		btnTripSearch.setText(messages.getString("Options.8")); //$NON-NLS-1$
		lblChoice.setText(messages.getString("Options.9")); //$NON-NLS-1$
		btnProfile.setText(messages.getString("Options.10")); //$NON-NLS-1$
		btnUserSearch.setText(messages.getString("Options.11")); //$NON-NLS-1$
	}
	
	/**
	 * Closes a frame after an event
	 */
	public void close(){
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
}
