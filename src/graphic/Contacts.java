package Graphic;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllers.Application;
import controllers.ProfileController;
import domain.ControllerNotLoadedException;
import domain.Session;
import domain.SessionNotActiveException;

public class Contacts extends JFrame {

	private static JPanel panel;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contacts frame = new Contacts(null, null);
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
	public Contacts(final Application instance, final Session session) {
		panel = new ImagePanel(new ImageIcon("Contacts.jpg").getImage());
		setTitle("TreckApp");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 901, 602);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setLayout(null);
		setContentPane(panel);
		
		
		
		final DefaultListModel friends = new DefaultListModel();
		HashSet<ProfileController> auxFriends = new HashSet<>();
		try {
			auxFriends = instance.getCurrentProfileController().getFriends();
			for(ProfileController each : auxFriends){
				friends.addElement(each.getUsername());
			}
		} catch (SessionNotActiveException e1) {
			e1.printStackTrace();
		} catch (ControllerNotLoadedException e1) {
			e1.printStackTrace();
		}
		
		final DefaultListModel block = new DefaultListModel();
		HashSet<ProfileController> auxBlock = new HashSet<>();
		try {
			auxBlock = instance.getCurrentProfileController().getBlockUsers();
			for(ProfileController each : auxBlock){
				block.addElement(each.getUsername());
			}
		} catch (SessionNotActiveException e1) {
			e1.printStackTrace();
		} catch (ControllerNotLoadedException e1) {
			e1.printStackTrace();
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(148, 121, 202, 200);
		panel.add(scrollPane);
		
		JList list = new JList(friends);
		scrollPane.setViewportView(list);
		list.setForeground(Color.BLACK);
		list.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(null);
		list.setValueIsAdjusting(true);
		list.setEnabled(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(null);
		scrollPane_1.setBounds(496, 121, 202, 200);
		panel.add(scrollPane_1);
		
		JList list_1 = new JList(block);
		scrollPane_1.setViewportView(list_1);
		list_1.setValueIsAdjusting(true);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setForeground(Color.BLACK);
		list_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list_1.setEnabled(false);
		list_1.setBorder(null);
		
		final JLabel lblFriends = new JLabel();
		lblFriends.setForeground(Color.BLACK);
		lblFriends.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFriends.setBounds(200, 90, 101, 36);
		panel.add(lblFriends);
		
		final JLabel lblBlock = new JLabel();
		lblBlock.setForeground(Color.BLACK);
		lblBlock.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBlock.setBounds(495, 90, 203, 36);
		panel.add(lblBlock);
		
		final JLabel lblContacts = new JLabel();
		lblContacts.setForeground(Color.BLACK);
		lblContacts.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblContacts.setBounds(348, 28, 145, 36);
		panel.add(lblContacts);
		
		//instance.getCurrentProfileController().requestsfriends
		
		
		final Choice requests = new Choice();
		requests.setBounds(377, 405, 200, 30);
//		for(int i25 = 0; i25 < hola.size(); i25++){
//			requests.add(hola.get(i25));
//		}
		panel.add(requests);
		/**/
		
		
		final JButton btnReject = new JButton();
		final JButton btnAccept = new JButton();
		final JButton btnBlock = new JButton();
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!friends.contains(requests.getSelectedItem())){
					friends.addElement(requests.getSelectedItem());
					requests.remove(requests.getSelectedItem());
				}else{
					requests.remove(requests.getSelectedItem());
				}
					
				if((requests.getItemCount()) < 1){
					btnReject.setEnabled(false);
					btnAccept.setEnabled(false);
					btnBlock.setEnabled(false);
				}
			}
		});
		btnAccept.setBounds(281, 436, 123, 20);
		panel.add(btnAccept);
		
		
		btnReject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requests.remove(requests.getSelectedItem());
				if((requests.getItemCount()) < 1){
					btnReject.setEnabled(false);
					btnAccept.setEnabled(false);
					btnBlock.setEnabled(false);
				}
			}
		});
		btnReject.setBounds(549, 436, 123, 20);
		panel.add(btnReject);
		
		final JLabel lblNewRequest = new JLabel();
		lblNewRequest.setForeground(Color.WHITE);
		lblNewRequest.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewRequest.setBounds(22, 390, 326, 34);
		panel.add(lblNewRequest);
		
		btnBack = new JButton();
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile frame = new Profile(instance, 1, session);
				frame.setVisible(true);
			    frame.pack();
			    frame.setSize(900, 620);
			    close();
			}
		});
		btnBack.setBounds(726, 518, 93, 23);
		panel.add(btnBack);
		
		btnBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!block.contains(requests.getSelectedItem())){
					block.addElement(requests.getSelectedItem());
					requests.remove(requests.getSelectedItem());
				}else{
					requests.remove(requests.getSelectedItem());
				}
				if((requests.getItemCount()) < 1){
					btnReject.setEnabled(false);
					btnAccept.setEnabled(false);
					btnBlock.setEnabled(false);
				}		
			}
		});
		btnBlock.setBounds(416, 436, 123, 20);
		panel.add(btnBlock);
		
		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//language = 1;
				btnBack.setText("Volver");
				lblFriends.setText("Amigos:");
				lblBlock.setText("Contactos Bloqueados : ");
				lblNewRequest.setText("Solicitudes de Amistad Nuevas");
				btnAccept.setText("Aceptar");
				btnReject.setText("Rechazar");
				btnBlock.setText("Bloquear");
				lblContacts.setText("Contactos");
				lblNewRequest.setBounds(30, 391, 326, 34);
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
				btnBack.setText("Back");
				lblFriends.setText("Friends :");
				lblBlock.setText("Block Contacts : ");
				lblNewRequest.setText("New Friends Requests");
				btnAccept.setText("Accept");
				btnReject.setText("Reject");
				btnBlock.setText("Block");
				lblContacts.setText("Contacts");
				lblNewRequest.setBounds(120, 391, 326, 34);

			}
		});
		ImageIcon imageE = new ImageIcon("EnglishFlag.jpg"); 
		panel.add(img2);
		img2.setIcon(imageE); 
		img2.setSize(22,18); 
		img2.setLocation(760,11); 
		img2.setVisible(true); 
		
		btnBack.setText("Volver");
		lblFriends.setText("Amigos:");
		lblBlock.setText("Contactos Bloqueados : ");
		lblNewRequest.setText("Solicitudes de Amistad Nuevas");
		btnAccept.setText("Aceptar");
		btnReject.setText("Rechazar");
		btnBlock.setText("Block");
		lblContacts.setText("Contactos");

	}
	
	public void close(){
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
}
