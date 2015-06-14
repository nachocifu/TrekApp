package Graphic;

import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JSpinner;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.JList;
import javax.swing.JScrollPane;

import controllers.Application;
import domain.Session;

public class Calif extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6292082986974427278L;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calif frame = new Calif(null,null);
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
	public Calif(final Application instance, final Session session) {
		setTitle("TreckApp");
		setBounds(0, 0, 900, 601);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new ImagePanel(new ImageIcon("Calif.jpg").getImage());
		panel.setBackground(new Color(25, 25, 112));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setLayout(null);
		setContentPane(panel);
		
		final JLabel lblCalifMembers = new JLabel();
		lblCalifMembers.setForeground(Color.BLACK);
		lblCalifMembers.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblCalifMembers.setBounds(249, 131, 353, 39);
		panel.add(lblCalifMembers);
		
		LinkedList<String> hola = new LinkedList<String>();
		
		final Choice requests = new Choice();
		requests.setBackground(Color.WHITE);
		requests.setBounds(370, 226, 255, 20);
		panel.add(requests);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(145, 181, 190, 120);
		panel.add(scrollPane);
		
		final DefaultListModel profiles = new DefaultListModel();
		
		LinkedList<String> profileCol = new LinkedList<String>();
		//profileCol = instance.getGroupController().
		for(String each : profileCol){
			profiles.addElement(each);
		}
		
		final JList list = new JList(profiles);
		scrollPane.setViewportView(list);
		
		final JButton btnBack = new JButton();
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Options frame = new Options(instance,session);
				frame.setVisible(true);
			    frame.pack();
			    frame.setSize(900, 620);
			    close();
			}
		});
		btnBack.setBounds(726, 518, 93, 23);
		panel.add(btnBack);
		
		final JButton btnAccept = new JButton();
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getModel().getSize() == 1){
					btnAccept.setEnabled(false);
					requests.setEnabled(false);
				}
				if(list.getSelectedValue() != null){
					System.out.println(list.getSelectedValue() + " " + requests.getSelectedItem());
					profiles.remove(list.getSelectedIndex());//si no se selecciona un objeto tira null pointer exception
				}
			}
		});
		btnAccept.setBounds(652, 226, 89, 23);
		panel.add(btnAccept);
		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//language = 1;
				lblCalifMembers.setText("Califica a los integrantes de tu viaje");
				btnAccept.setText("Aceptar");
				btnBack.setText("Volver");
//				hola.add("Excellent");
//				hola.add("Muy Bueno");
//				hola.add("Regular");
//				hola.add("Malo");
//				hola.add("No recomiendo viajar con esta persona");
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
				lblCalifMembers.setText("Rate your fellow travellers");
				btnAccept.setText("Accept");
				btnBack.setText("Back");
//				hola.add("Excellent");
//				hola.add("Very Good");
//				hola.add("Normal");
//				hola.add("Bad");
//				hola.add("Very Bad Trip Companion");
			}
		});

		ImageIcon imageE = new ImageIcon("EnglishFlag.jpg"); 
		panel.add(img2);
		img2.setIcon(imageE); 
		img2.setSize(22,18); 
		img2.setLocation(760,11); 
		img2.setVisible(true); 
		
		lblCalifMembers.setText("Califica a los integrantes de tu viaje");
		btnAccept.setText("Aceptar");
		btnBack.setText("Volver");
		hola.add("Excelente");
		hola.add("Muy Bueno");
		hola.add("Regular");
		hola.add("Malo");
		hola.add("No recomiendo viajar con esta persona");
		
		for(int i25 = 0; i25 < hola.size(); i25++){
			requests.add(hola.get(i25));
		}
	}
	
	public void close(){
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
}
