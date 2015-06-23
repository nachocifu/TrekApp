package graphic;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JPasswordField;

import controllers.Application;
import controllers.Session;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.ResourceBundle;


public class Connect extends JFrame {

	private JTextField textField;
	private JPasswordField passwordField;
	private static JPanel panel;
	private Application instance = Application.getInstance() ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connect frame = new Connect(true);
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
	 * Create the frame.
	 * @param language
	 */
	public Connect(final boolean language) {
		
		Locale currentLocale;
		if(language){
			currentLocale = new Locale("en","US"); 
		}else{
			currentLocale = new Locale("es","AR");
		}
		final ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale); 
		
		
		panel = new ImagePanel(new ImageIcon("Connect.jpg").getImage()); //$NON-NLS-1$
		setTitle("TreckApp"); //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel.setEnabled(true);
		panel.setLayout(null);
		setResizable(false);
		setContentPane(panel);
		
		final JLabel lblUser = new JLabel();
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 18)); //$NON-NLS-1$
		lblUser.setBounds(260, 176, 132, 37);
		panel.add(lblUser);
		
		final JLabel lblPass = new JLabel();
		lblPass.setForeground(Color.WHITE);
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 18)); //$NON-NLS-1$
		lblPass.setBounds(260, 232, 170, 37);
		panel.add(lblPass);
		
		textField = new JTextField();
		textField.setBounds(440, 187, 132, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(440, 243, 132, 20);
		panel.add(passwordField);
		
		final JButton btnConnect = new JButton();
		btnConnect.addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent e) {
				String password = String.valueOf(passwordField.getPassword());
				Session session = Session.getInstance();
				if(session.logIn(textField.getText(), password) != false ){
					Options frame = new Options(instance, session,language);
					frame.setVisible(true);
					frame.pack();
					frame.setSize(900, 602);
					close();
				}else{
					JOptionPane.showMessageDialog(null, messages.getString("Connect.4"), messages.getString("Connect.5"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
				}
					
			}
		});
		btnConnect.setBounds(333, 305, 156, 23);
		panel.add(btnConnect);
		
		final JButton btnNewUser = new JButton();
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Profile frame = new Profile(instance , 0 , null, language);
				frame.setVisible(true);
				frame.setSize(900, 620);
				frame.pack();
				close();
			}
		});
		btnNewUser.setBounds(333, 350, 156, 23);
		panel.add(btnNewUser);
		
		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connect frame = new Connect(false);
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
				Connect frame = new Connect(true);
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

		btnNewUser.setText(messages.getString("Connect.6")); //$NON-NLS-1$
		btnConnect.setText(messages.getString("Connect.7")); //$NON-NLS-1$
		lblPass.setText(messages.getString("Connect.8")); //$NON-NLS-1$
		lblUser.setText(messages.getString("Connect.9")); //$NON-NLS-1$

	}
	
	/**
	 * Closes a frame after an event
	 */
	public void close(){
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
}