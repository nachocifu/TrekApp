package Graphic;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

//import server.Application;
//import server.Session;

import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JPasswordField;

import controllers.Application;
import domain.Session;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Connect extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;
	private static JPanel panel;
	private int language;
	private Application instance = Application.getInstance() ; 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connect frame = new Connect();
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
	 * @param instance 
	 */
	public Connect() {
		
		panel = new ImagePanel(new ImageIcon("Connect.jpg").getImage());
		setTitle("TreckApp");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel.setEnabled(true);
		panel.setLayout(null);
		setResizable(false);
		setContentPane(panel);
		
		final JLabel lblUser = new JLabel();
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUser.setBounds(260, 176, 132, 37);
		panel.add(lblUser);
		
		final JLabel lblPass = new JLabel();
		lblPass.setForeground(Color.WHITE);
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
				if(instance.validate(textField.getText(), password) != false ){
					Session session = Session.getInstance();
					session.logIn(textField.getText(), passwordField.getText());
					Options frame = new Options(instance, session);
					frame.setVisible(true);
					frame.setSize(484, 315);
					close();
				}
				else{
					Connect frame = new Connect();
					frame.setVisible(true);
					frame.setSize(484, 315);
					close();
				}
					
			}
		});
		btnConnect.setBounds(333, 305, 156, 23);
		panel.add(btnConnect);
		
		final JButton btnNewUser = new JButton();
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Profile frame = new Profile(instance , 0 , null);
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
				language = 1;
				btnNewUser.setText("NUEVO USUARIO");
				btnConnect.setText("CONECTAR");
				lblPass.setText("CONTRASE\u00D1A :");
				lblUser.setText("USUARIO :");
			}
		});
		ImageIcon imageS = new ImageIcon("SpanishFlag.jpg"); 
		panel.add(img);
		img.setIcon(imageS); 
		img.setSize(22,18); 
		img.setLocation(796,11); 
		img.setVisible(true); 
		
		JButton img2 = new JButton();
		img2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				language = 2;
				btnNewUser.setText("NEW USER");
				btnConnect.setText("CONNECT");
				lblPass.setText("PASSWORD :");
				lblUser.setText("USER :");
			}
		});

		ImageIcon imageE = new ImageIcon("EnglishFlag.jpg"); 
		panel.add(img2);
		img2.setIcon(imageE); 
		img2.setSize(22,18); 
		img2.setLocation(760,11); 
		img2.setVisible(true); 
		
		btnNewUser.setText("NUEVO USUARIO");
		btnConnect.setText("CONECTAR");
		lblPass.setText("CONTRASE\u00D1A :");
		lblUser.setText("USUARIO :");


	}
	
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

		}
}