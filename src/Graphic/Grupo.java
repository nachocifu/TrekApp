package Graphic;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import controllers.Application;
import controllers.GroupController;
import controllers.ProfileController;
import domain.ControllerNotLoadedException;
import domain.Session;
import domain.SessionNotActiveException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Choice;


public class Grupo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8667069963378135326L;
	private static JPanel panel;
	private JTextField tFName;
	private JTextField tFCap;
	private JTextField tFAdmin;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grupo frame = new Grupo(0,null, null,null,null);
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
	// i = 0 creando, i = 1 viendo el propio, i = 2 viendo el de otro
	public Grupo(final Integer i,/* va un tripController aca*/ final Viajeback viaje, final ArrayList<String> aux, final Application instance, final Session session) {
		setTitle("TreckApp");
		setBounds(0, 0, 902, 602);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new ImagePanel(new ImageIcon("Group.jpg").getImage());
		panel.setBackground(new Color(25, 25, 112));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setContentPane(panel);
		panel.setLayout(null);
		
		final JLabel lblGroupName = new JLabel();
		lblGroupName.setForeground(Color.BLACK);
		lblGroupName.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblGroupName.setHorizontalAlignment(SwingConstants.LEFT);
		lblGroupName.setBounds(200, 58, 173, 34);
		panel.add(lblGroupName);
		
		tFName = new JTextField();
		tFName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tFName.setDisabledTextColor(new Color(0, 0, 0));
		tFName.setBounds(408, 64, 185, 25);
		panel.add(tFName);
		tFName.setColumns(10);
		
		final JLabel lblCapacity = new JLabel();
		lblCapacity.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblCapacity.setForeground(Color.BLACK);
		lblCapacity.setBounds(200, 103, 159, 34);
		panel.add(lblCapacity);
		
		tFCap = new JTextField();
		tFCap.setForeground(Color.BLACK);
		tFCap.setDisabledTextColor(new Color(0, 0, 0));
		tFCap.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tFCap.setHorizontalAlignment(SwingConstants.CENTER);
		tFCap.setBounds(408, 105, 43, 30);
		panel.add(tFCap);
		tFCap.setColumns(10);
		
		final JButton btnTrip = new JButton();
		if(i == 0){
			btnTrip.setBounds(430, 386, 145, 23);
		}else{
			btnTrip.setBounds(240, 386, 145, 23);
		}
		
		panel.add(btnTrip);
		
		final JLabel lblMembers = new JLabel();
		lblMembers.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMembers.setForeground(Color.BLACK);
		lblMembers.setHorizontalAlignment(SwingConstants.LEFT);
		lblMembers.setBounds(200, 208, 203, 34);
		panel.add(lblMembers);
		
		/*lista de integrantes del trip*/
		
		final DefaultListModel members = new DefaultListModel();
		HashSet<ProfileController> auxMembers = new HashSet<>();
		if(instance != null){
			try {
				auxMembers = instance.getGroupController().getMembers();
				for(ProfileController each : auxMembers){
					members.addElement(each.getUsername() + " " + each.getSurname());
				}
			} catch (SessionNotActiveException e1) {
				e1.printStackTrace();
			} catch (ControllerNotLoadedException e1) {
				e1.printStackTrace();
			}
			
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(408, 206, 202, 169);
		panel.add(scrollPane);
		
		JList<String> list = new JList<String>(members);
		scrollPane.setViewportView(list);
		list.setForeground(Color.BLACK);
		list.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(null);
		list.setValueIsAdjusting(true);
		list.setEnabled(false);
		
		/**/
		final Choice requests = new Choice();
		requests.setVisible(false);
		requests.setBounds(233, 457, 200, 30);
		
		HashMap<ProfileController, Integer> requestsTrip = new HashMap<ProfileController, Integer>();
		if(instance != null){
			try {
				requestsTrip = instance.getMyGroupController().getMemberRequests();
				for (ProfileController key : requestsTrip.keySet()) {
					requests.add(key.getUsername() + " " + key.getSurname());
				}
			} catch (SessionNotActiveException e1) {
				e1.printStackTrace();
			} catch (ControllerNotLoadedException e1) {
				e1.printStackTrace();
			}
		}		
		panel.add(requests);
		
		final JButton btnReject = new JButton();
		final JButton btnAccept = new JButton();
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				members.addElement(requests.getSelectedItem());
				requests.remove(requests.getSelectedItem());
				if((requests.getItemCount()) < 1){
					btnReject.setEnabled(false);
					btnAccept.setEnabled(false);
				}
			}
		});
		btnAccept.setBounds(476, 457, 123, 20);
		btnAccept.setVisible(false);
		panel.add(btnAccept);
		btnReject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requests.remove(requests.getSelectedItem());
				if((requests.getItemCount()) < 1){
					btnReject.setEnabled(false);
					btnAccept.setEnabled(false);
				}
			}
		});
		btnReject.setVisible(false);
		btnReject.setBounds(637, 457, 123, 20);
		panel.add(btnReject);
		
		final JLabel lblNewRequest = new JLabel();
		lblNewRequest.setVisible(false);
		lblNewRequest.setForeground(Color.WHITE);
		lblNewRequest.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewRequest.setBounds(26, 448, 185, 34);
		panel.add(lblNewRequest);
		
		final JButton btnDelete = new JButton();
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					instance.getMyGroupController().deleteGroup();
				} catch (SessionNotActiveException e1) {
					e1.printStackTrace();
				} catch (ControllerNotLoadedException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(397, 386, 145, 23);
		panel.add(btnDelete);
		
		
		final JButton btnRequestcheck = new JButton();
		btnRequestcheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(i==1){
					lblNewRequest.setVisible(true);
					btnAccept.setVisible(true);
					btnReject.setVisible(true);
					requests.setVisible(true);
				}
			}
		});
		btnRequestcheck.setBounds(552, 386, 150, 23);
		panel.add(btnRequestcheck);
		
		final JLabel lblAdmin = new JLabel();
		lblAdmin.setForeground(Color.BLACK);
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblAdmin.setBounds(200, 148, 200, 34);
		panel.add(lblAdmin);
		
		tFAdmin = new JTextField();
		tFAdmin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tFAdmin.setBounds(408, 152, 185, 25);
		panel.add(tFAdmin);
		tFAdmin.setColumns(10);
		
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
		btnBack.setBounds(26, 530, 93, 23);
		panel.add(btnBack);

		final JButton btnCreatetrip = new JButton();
		btnCreatetrip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer flag = 0;
				//flag=1 corresponde a que no hay error
				//flag=2 no introdujo el nombre del grupo
				//flag=3 no introdujo la capacidad maxima del viaje planeado
				//flag=4 no se creo un viaje
				if(tFName.getText().isEmpty()){
					flag = 2;
				} else if(tFCap.getText().isEmpty()){
					flag = 3;
				}else if(viaje != null){
					flag = 4;
				}else{
					flag = 0;
				}
				switch(flag){
				case 1:
					int confirm = JOptionPane.showConfirmDialog(null, "�Desea crear el viaje?", "Confirmar", JOptionPane.YES_NO_OPTION);
					if(confirm == JOptionPane.YES_OPTION){
						
						
						//new group y trip
			
						
						Options frame = new Options(instance, session);
						frame.setVisible(true);
						frame.pack();
						frame.setSize(900, 602);
						close();
					}
					break;
				case 2:
					JOptionPane.showMessageDialog(new Grupo(i,viaje,aux,instance, session), "No introdujo el nombre del grupo", "ERROR", JOptionPane.ERROR_MESSAGE);
					break;
				case 3:
					JOptionPane.showMessageDialog(new Grupo(i,viaje,aux,instance, session), "No introdujo la capacidad maxima del viaje planeado", "ERROR", JOptionPane.ERROR_MESSAGE);
					break;
				case 4:
					JOptionPane.showMessageDialog(new Grupo(i,viaje,aux,instance, session), "No creo ningun viaje", "ERROR", JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
		});
		btnCreatetrip.setBounds(690, 530, 111, 23);
		panel.add(btnCreatetrip);
		
		if( i == 1){
			tFName.setEnabled(false);
			tFCap.setEnabled(false);
			tFAdmin.setEnabled(false);
			try {
				tFName.setText(instance.getGroupController().getGroupName());
				tFCap.setText(instance.getGroupController().groupSize().toString());
				tFAdmin.setText(instance.getGroupController().getAdmin().getUsername() + " " + instance.getGroupController().getAdmin().getUsername());
			} catch (SessionNotActiveException e2) {
				e2.printStackTrace();
			} catch (ControllerNotLoadedException e2) {
				e2.printStackTrace();
			}
			btnTrip.setText("Modificar Viaje");
			btnTrip.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Viaje frame = new Viaje(1, instance.getTripController() ,null, instance, session);
					frame.setVisible(true);
					frame.pack();
				    frame.setSize(900, 602);
					close();
				}
			});
			btnRequestcheck.setText("Ver Solicitudes");
			btnDelete.setVisible(true);
		}else if(i == 2){
			
			tFName.setEnabled(false);
			tFCap.setEnabled(false);
			tFAdmin.setEnabled(false);
			if( instance != null){
				try {
					tFName.setText(instance.getGroupController().getGroupName());
					tFCap.setText(instance.getGroupController().groupSize().toString());
					tFAdmin.setText(instance.getGroupController().getAdmin().getUsername() + " " + instance.getGroupController().getAdmin().getUsername());
				} catch (SessionNotActiveException e2) {
					e2.printStackTrace();
				} catch (ControllerNotLoadedException e2) {
					e2.printStackTrace();
				}
			}
			btnTrip.setText("Ver Viaje");
			btnTrip.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Viaje frame = new Viaje(2,instance.getTripController(),null, instance, session);
					frame.setVisible(true);
				    frame.pack();
				    frame.setSize(900, 602);
					close();
				}
			});
			btnDelete.setVisible(false);
			btnRequestcheck.setText("Enviar Solicitud");
			btnRequestcheck.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						instance.getGroupController().sendMemberRequest(instance.getCurrentProfileController());
					} catch (SessionNotActiveException e1) {
						e1.printStackTrace();
					} catch (ControllerNotLoadedException e1) {
						e1.printStackTrace();
					}
				}
			});	
			
		}else if(i == 0){
			if(aux != null){
				tFName.setText(aux.get(0));
				tFCap.setText(aux.get(1));
				tFAdmin.setText(aux.get(2));
			}
			btnCreatetrip.setVisible(true);
			btnTrip.setText("Crear Viaje");
			btnTrip.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ArrayList<String> aux = new ArrayList<String>();
					aux.add(tFName.getText());
					aux.add(tFCap.getText());
					aux.add(tFAdmin.getText());
					Viaje frame = new Viaje(0,null,aux, instance, session);
					frame.setVisible(true);
					frame.pack();
				    frame.setSize(900, 602);
					close();
				}
			});
			btnDelete.setVisible(false);
			btnRequestcheck.setVisible(false);
		}
		
		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//language = 1;
				btnDelete.setText("Eliminar Grupo");
				btnCreatetrip.setText("Crear Grupo");
				btnBack.setText("Volver");
				lblAdmin.setText("Organizador del Viaje :");
				btnAccept.setText("Aceptar");
				lblNewRequest.setText("Solicitudes Nuevas");
				btnReject.setText("Rechazar");
				lblMembers.setText("Integrantes del Viaje :");
				lblGroupName.setText("Nombre del Grupo :");
				lblCapacity.setText("Cupos Restantes :");
				if(i == 0){
					btnTrip.setText("Crear Viaje");
				}else if(i == 1){
					btnTrip.setText("Modificar Viaje");
					btnRequestcheck.setText("Ver Solicitudes");
				}else if(i == 2){
					btnRequestcheck.setText("Enviar Solicitud");
					btnTrip.setText("Ver Viaje");
				}
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
				lblGroupName.setText("Group Name :");
				lblCapacity.setText("Capacity Left :");
				btnDelete.setText("Delete Group");
				btnCreatetrip.setText("Create Group");
				btnBack.setText("Back");
				lblAdmin.setText("User Organizer :");
				btnAccept.setText("Accept");
				lblNewRequest.setText("New Requests");
				btnReject.setText("Reject");
				lblMembers.setText("Trip Members :");
				
				if(i == 0){
					btnTrip.setText("Create Trip");
					btnRequestcheck.setText("Check Trip Requests");
				}else if(i == 1){
					btnTrip.setText("Modify Trip");
					btnRequestcheck.setText("Check Requests");
				}else if(i == 2){
					btnRequestcheck.setText("Send Trip Requests");
					btnTrip.setText("Check Trip");
				}
			}
		});
		ImageIcon imageE = new ImageIcon("EnglishFlag.jpg"); 
		panel.add(img2);
		img2.setIcon(imageE); 
		img2.setSize(22,18); 
		img2.setLocation(760,11); 
		img2.setVisible(true); 
		
		btnDelete.setText("Eliminar Grupo");
		btnCreatetrip.setText("Crear Grupo");
		btnBack.setText("Volver");
		lblAdmin.setText("Organizador del Viaje :");
		btnAccept.setText("Aceptar");
		lblNewRequest.setText("Solicitudes Nuevas");
		btnReject.setText("Rechazar");
		lblMembers.setText("Integrantes del Viaje :");
		lblGroupName.setText("Nombre del Grupo :");
		lblCapacity.setText("Cupos Restantes :");
		
	}
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

	}
}
