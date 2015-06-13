package Graphic;

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

//import server.Application;
//import server.Session;







import javax.swing.SwingUtilities;

import controllers.Application;
import domain.Session;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class Options extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Options frame = new Options(null, null);
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
	public Options(final Application instance, final Session session) {
		setTitle("TreckApp");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new ImagePanel(new ImageIcon("Options.jpg").getImage());
		panel.setBackground(new Color(25, 25, 112));
		panel.setEnabled(false);
		panel.setLayout(null);
		setContentPane(panel);
		
		final JButton btnProfile = new JButton();
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile frame = new Profile(instance, 1 , session);
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
				BuscadorUsuario frame = new BuscadorUsuario(instance,session);
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
				BuscadorViaje frame = new BuscadorViaje(instance,session);
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
		lblChoice.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblChoice.setForeground(Color.WHITE);
		lblChoice.setBounds(300, 58, 238, 29);
		panel.add(lblChoice);
		
		final JButton btnSignOut = new JButton();
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				session.logOut();
				Connect frame = new Connect();
				frame.setVisible(true);
				frame.pack();
			    frame.setSize(900, 602);
				close();
			}
		});
		btnSignOut.setBounds(361, 367, 131, 29);
		panel.add(btnSignOut);
		
		final JButton btnNewGroup = new JButton();
		btnNewGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Grupo frame = new Grupo(0,null,null,null, instance, session);
				frame.setVisible(true);
				frame.pack();
				frame.setSize(900, 602);
				close();
			}
		});
		btnNewGroup.setBounds(227, 242, 131, 29);
		panel.add(btnNewGroup);
		
		final JButton btnCalif = new JButton();
		btnCalif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calif frame = new Calif(instance, session);
				frame.setVisible(true);
				frame.pack();
				frame.setSize(900, 602);
				close();
			}
		});
		btnCalif.setBounds(300, 312, 250, 29);
		panel.add(btnCalif);
		
		/**/
		
		int z = 0;
		if(z == 0){
			btnCalif.setVisible(false);
		}else{
			btnCalif.setVisible(true);
		}
		/**/
		
		
		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//language = 1;
				btnCalif.setText("Calificar integrantes con los que viaje");
				btnNewGroup.setText("Crear Grupo");
				btnSignOut.setText("Cerrar Sesi\u00F3n");
				btnTripSearch.setText("Busqueda de Viaje");
				lblChoice.setText("Elija la Opci\u00F3n Deseada");
				btnProfile.setText("Perfil");
				btnUserSearch.setText("Busqueda de Usuario");
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
				//language = 2;
				btnCalif.setText("Rate your fellow travellers");
				btnNewGroup.setText("Create Group");
				btnSignOut.setText("Sign Out");
				btnTripSearch.setText("Trip Search");
				lblChoice.setText("Choose an Option");
				btnProfile.setText("Profile");
				btnUserSearch.setText("User Search");
			}
		});

		ImageIcon imageE = new ImageIcon("EnglishFlag.jpg"); 
		panel.add(img2);
		img2.setIcon(imageE); 
		img2.setSize(22,18); 
		img2.setLocation(760,11); 
		img2.setVisible(true); 
		
		btnCalif.setText("Calificar integrantes con los que viaje");
		btnNewGroup.setText("Crear Grupo");
		btnSignOut.setText("Cerrar Sesi\u00F3n");
		btnTripSearch.setText("Busqueda de Viaje");
		lblChoice.setText("Elija la Opci\u00F3n Deseada");
		btnProfile.setText("Perfil");
		btnUserSearch.setText("Busqueda de Usuario");
	}
	
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

		}
}
