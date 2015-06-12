package Graphic;

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

import javax.swing.JTextField;
import javax.swing.JButton;

import controllers.Application;
import domain.Session;

public class BuscadorUsuario extends JFrame {

	private static JPanel panel;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscadorUsuario frame = new BuscadorUsuario();
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
	 */
	public BuscadorUsuario(final Application instance, final Session session) {
		setTitle("TreckApp");
		setBounds(0, 0, 900, 601);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new ImagePanel(new ImageIcon("UserSearch.jpg").getImage());
		panel.setBackground(new Color(25, 25, 112));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setLayout(null);
		setContentPane(panel);
		
		final JLabel lblUserSearch = new JLabel();
		lblUserSearch.setForeground(new Color(240, 248, 255));
		lblUserSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUserSearch.setBounds(308, 85, 193, 47);
		panel.add(lblUserSearch);
		
		final JLabel lblName = new JLabel();
		lblName.setForeground(new Color(240, 248, 255));
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblName.setBounds(237, 162, 181, 25);
		panel.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(428, 165, 118, 25);
		panel.add(textField);
		textField.setColumns(10);
		
		final JButton btnCheckUser = new JButton();
		btnCheckUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCheckUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(textField.getText()).isEmpty() && Profile.get.textField.getText()){
					//abrir el profile cargado
					Profile frame = new Profile(instance, 2, session);
					frame.setVisible(true);
				    frame.pack();
				    frame.setSize(900, 620);
					close();
				}else{
					JOptionPane.showMessageDialog(new BuscadorUsuario(instance,session), "No existe el usurio que esta buscando", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCheckUser.setBounds(342, 243, 135, 23);
		panel.add(btnCheckUser);
		
		btnBack = new JButton();
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Options frame = new Options(instance, session);
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
				//language = 1;
				lblName.setText("Nombre de Usuario :");
				btnCheckUser.setText("Buscar Usuario");
				lblUserSearch.setText("Busqueda de Usuario");
				btnBack.setText("Volver");
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
				lblName.setText("User Name :");
				btnCheckUser.setText("Search");
				lblUserSearch.setText("Search a User");
				btnBack.setText("Back");
			}
		});

		ImageIcon imageE = new ImageIcon("EnglishFlag.jpg"); 
		panel.add(img2);
		img2.setIcon(imageE); 
		img2.setSize(22,18); 
		img2.setLocation(760,11); 
		img2.setVisible(true); 
		
		lblName.setText("Nombre de Usuario :");
		btnCheckUser.setText("Buscar Usuario");
		lblUserSearch.setText("Busqueda de Usuario");
		btnBack.setText("Volver");
	}
	
	public void close(){
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}

}
