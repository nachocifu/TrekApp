package Graphic;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;

//import server.Application;
//import server.Session;












import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;


import javax.swing.JPasswordField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Choice;
import java.io.File;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import controllers.Application;
import domain.ControllerNotLoadedException;
import domain.InvalidPasswordException;
import domain.Session;
import domain.SessionNotActiveException;
import domain.UserNameAlreadyExistsException;

public class Profile extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7008575209671670763L;
	private static JPanel panel;
	private JTextField tFName;
	private JTextField tFSurName;
	private JTextField tFUserName;
	private Integer flag;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField tFAge;
	private JTextField tFRate;
	private JLabel lblProfile;
	private JTextField tFEmail;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Profile frame = new Profile(null, 1, null);
					frame.setVisible(true);
				    frame.pack();
				    frame.setSize(900, 620);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/*choice corresponde al porque entra al frame profile. Si choice es 1 entra debido a que ya existe el usuario y el usuario selecciono la opcion de ver su perfil,
		 si choice es 0 corresponde a la creacion de un usuario nuevo y si choice es 2 un usuario que no es el propio entro a ver el perfil*/
	
	public Profile(final Application instance, final Integer choice, final Session session) {
		
		panel = new ImagePanel(new ImageIcon("Profile.jpg").getImage());
		panel.setBackground(Color.BLACK);
		setTitle("TreckApp");
		setBounds(0, 0, 826, 616);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setLayout(null);
		setContentPane(panel);

		
		lblProfile = new JLabel();
		lblProfile.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblProfile.setForeground(Color.BLACK);
		lblProfile.setBounds(361, 20, 134, 49);
		panel.add(lblProfile);
		
		final JLabel lblName = new JLabel();
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setForeground(Color.BLACK);
		lblName.setBounds(139, 95, 108, 20);
		panel.add(lblName);
		
		final JLabel lblLastName = new JLabel();
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLastName.setForeground(Color.BLACK);
		lblLastName.setBounds(438, 95, 101, 20);
		panel.add(lblLastName);
		
		final JLabel lblUser = new JLabel();
		lblUser.setForeground(Color.BLACK);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUser.setBounds(70, 141, 108, 20);
		panel.add(lblUser);
		
		final JLabel lblChangePass = new JLabel();
		lblChangePass.setForeground(Color.BLACK);
		lblChangePass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChangePass.setBounds(334, 197, 238, 25);
		panel.add(lblChangePass);
		
		final JLabel lblNewPass = new JLabel();
		lblNewPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewPass.setForeground(Color.BLACK);
		lblNewPass.setBounds(46, 244, 179, 20);
		panel.add(lblNewPass);
		
		final JLabel lblConfirmPass = new JLabel();
		lblConfirmPass.setForeground(Color.BLACK);
		lblConfirmPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblConfirmPass.setBounds(371, 244, 206, 20);
		panel.add(lblConfirmPass);
		
		tFName = new JTextField();
		tFName.setDisabledTextColor(Color.BLACK);
		tFName.setHorizontalAlignment(SwingConstants.CENTER);
		tFName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tFName.setEnabled(true);
		tFName.setColumns(10);
		tFName.setBounds(254, 95, 123, 20);
		panel.add(tFName);

		
		tFSurName = new JTextField();
		tFSurName.setDisabledTextColor(Color.BLACK);
		tFSurName.setHorizontalAlignment(SwingConstants.CENTER);
		tFSurName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tFSurName.setEnabled(true);
		tFSurName.setColumns(10);
		tFSurName.setBounds(559, 96, 123, 20);
		panel.add(tFSurName);
		
		tFUserName = new JTextField();
		tFUserName.setDisabledTextColor(Color.BLACK);
		tFUserName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tFUserName.setHorizontalAlignment(SwingConstants.CENTER);
		tFUserName.setEnabled(true);
		tFUserName.setColumns(10);
		tFUserName.setBounds(148, 142, 123, 20);
		panel.add(tFUserName);

		final JButton btnApply = new JButton();
		btnApply.setBounds(700, 544, 97, 23);
		panel.add(btnApply);
		
		final JRadioButton radioButton = new JRadioButton("");
		//radioButton.setBackground(new Color(25, 25, 112));
		radioButton.setBounds(284, 197, 21, 23);
		panel.add(radioButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(214, 244, 123, 20);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(587, 244, 123, 20);
		panel.add(passwordField_1);
		
		final JLabel lblExists = new JLabel();
		
		lblExists.setForeground(Color.BLACK);
		lblExists.setBorder(null);
		lblExists.setBounds(80, 156, 158, 27);
		panel.add(lblExists);
		lblExists.setVisible(false);
		
		JLabel label_6 = new JLabel("*");
		label_6.setVisible(false);
		label_6.setForeground(Color.BLACK);
		label_6.setBounds(62, 142, 15, 22);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("*");
		label_7.setVisible(false);
		label_7.setForeground(Color.BLACK);
		label_7.setBounds(131, 95, 15, 22);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("*");
		label_8.setVisible(false);
		label_8.setForeground(Color.BLACK);
		label_8.setBounds(430, 95, 15, 22);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("*");
		label_9.setVisible(false);
		label_9.setForeground(Color.BLACK);
		label_9.setBounds(266, 199, 15, 22);
		panel.add(label_9);
		
		final JLabel label_11 = new JLabel();
		label_11.setVisible(false);
		label_11.setForeground(Color.BLACK);
		label_11.setBounds(51, 20, 134, 20);
		panel.add(label_11);
		
		final JButton btn = new JButton();
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Options frame = new Options(null, null);
				frame.setVisible(true);
				frame.pack();
				frame.setSize(900, 602);
				close();
			}
		});
		btn.setBounds(80, 544, 89, 23);
		panel.add(btn);
		
		final JLabel lblAge = new JLabel();
		lblAge.setForeground(Color.BLACK);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAge.setBounds(318, 141, 193, 20);
		panel.add(lblAge);
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.BLACK);
		label_2.setBounds(310, 141, 15, 28);
		panel.add(label_2);
		label_2.setVisible(false);
		
		final JLabel lblGender = new JLabel();
		lblGender.setForeground(Color.BLACK);
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGender.setBounds(648, 142, 54, 22);
		panel.add(lblGender);
		
		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.BLACK);
		label_4.setBounds(640, 141, 15, 28);
		panel.add(label_4);
		label_4.setVisible(false);
		
		Choice choice2 = new Choice();
		choice2.setBounds(712, 143, 85, 20);
		panel.add(choice2);
		
		tFAge = new JTextField();
		tFAge.setBounds(505, 141, 97, 20);
		panel.add(tFAge);
		tFAge.setColumns(10);
		
		final JLabel lblCalif = new JLabel();
		lblCalif.setForeground(Color.BLACK);
		lblCalif.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCalif.setBounds(559, 302, 123, 20);
		panel.add(lblCalif);
		
		tFRate = new JTextField();
		tFRate.setEditable(false);
		tFRate.setHorizontalAlignment(SwingConstants.CENTER);
		tFRate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tFRate.setEnabled(false);
		tFRate.setDisabledTextColor(Color.BLACK);
		tFRate.setColumns(10);
		tFRate.setBounds(690, 302, 54, 20);
		panel.add(tFRate);
		
		
		
		
//		list.setModel(new AbstractListModel() {
//			String[] values = new String[] {"hola\t", "hola"};
//			public int getSize() {
//				return values.length;
//			}
//			public Object getElementAt(int index) {
//				return values[index];
//			}
//		});

		
		final JButton btnPastTrip = new JButton();
		btnPastTrip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OldTrips frame = new OldTrips(instance, session);
				frame.setVisible(true);
				frame.pack();
				frame.setSize(900, 602);
				close();
			}
		});
		btnPastTrip.setBounds(297, 374, 193, 23);
		panel.add(btnPastTrip);
		
		
		if(session != null){
			try {
				tFName.setText(instance.getCurrentProfileController().getUsername());
				tFUserName.setText(instance.getCurrentProfileController().getUserName());
				tFSurName.setText(instance.getCurrentProfileController().getSurname());
				tFAge.setText(instance.getCurrentProfileController().getBirthday().toString());
				tFEmail.setText(instance.getCurrentProfileController().getMail());
				tFRate.setText(instance.getCurrentProfileController().getRating().toString());
			} catch (SessionNotActiveException | ControllerNotLoadedException e1) {
			
			}
			
		}
		
		if(choice == 0){
			tFName.setEnabled(true);
			tFSurName.setEnabled(true);
			tFUserName.setEnabled(true);
			label_6.setVisible(true);
			label_7.setVisible(true);
			label_8.setVisible(true);
			label_9.setVisible(true);
			label_11.setVisible(true);
			radioButton.setSelected(true);
			radioButton.setEnabled(false);
		}
		else{
			//btnCancelar.setVisible(false);
		}
		
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// flag=0 corresponde a que no hay error
				//flag=1 error en la igualdad de constrase�as
				//flag=2 error en la introduccion de una direccion
				//flag=3 no introdujo campos obligatorios el usuario que se esta registrando
				//flag=4 corresponde a la existencia de un usuario con el mismo UserName en la creacion de un usuario nuevo
				
				flag = 0;
				if(choice == 1){
					if(radioButton.isSelected()){
						if(passwordField.getText().equals(passwordField_1.getText()) && !passwordField.getText().equals("") ){
							flag = 0;
							if(instance != null){
								try {
									instance.getCurrentProfileController().changePass(passwordField.getText(), passwordField_1.getText());
								} catch (InvalidPasswordException e) {
									
								}
							}
						}
						else{
							flag = 1;
						}
					}
					if(flag == 0){
						Options frame = new Options(instance, session);
						frame.setVisible(true);
						frame.setSize(484, 315);
						close();
					}
					else{
						if(flag == 1){
							JOptionPane.showMessageDialog(new Profile(instance,1,session), "No coinciden las Contrase\u00f1as", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						else JOptionPane.showMessageDialog(new Profile(instance,1,session), "No introdujo una direcci\u00f3n correcta", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				if(choice == 0){
					
					if(tFName.getText().isEmpty() && tFSurName.getText().isEmpty() && tFUserName.getText().isEmpty()){
						flag = 3;
					}
					if(flag == 0){
						if(passwordField.getText().equals(passwordField_1.getText()) && !passwordField.getText().isEmpty() ){
							flag = 0;
						}else{
							flag = 1;
						}
					}
					
					
					int day = Integer.parseInt(tFEmail.getText().substring(0, 2));
					int month = Integer.parseInt(tFEmail.getText().substring(3, 5));
					int year = Integer.parseInt(tFEmail.getText().substring(6, 8));
					Date date = new Date(year,month,day);
					boolean sex;
					if(choice2.getSelectedItem().equals("Male")){
						sex = true;
					}else{
						sex= false;
					}
					try {
						instance.registerUser(tFName.getText(), tFSurName.getText(), tFUserName.getText(), date , sex , passwordField.getText(), null, tFAge.getText());
					} catch (ServerException e) {
						e.printStackTrace();
					} catch (UserNameAlreadyExistsException e) {
						lblExists.setVisible(true);
					}
						
				}
			}
		});
		
		final JLabel lblLanguage = new JLabel();
		lblLanguage.setForeground(Color.BLACK);
		lblLanguage.setBackground(Color.BLACK);
		lblLanguage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLanguage.setBounds(46, 302, 110, 28);
		panel.add(lblLanguage);
		
		Choice choice_1 = new Choice();
		choice_1.setBounds(162, 302, 123, 22);
		panel.add(choice_1);
		
		final JButton btnPresenttrips = new JButton();
		btnPresenttrips.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TripGroups frame = new TripGroups(instance, session);
				frame.setVisible(true);
			    frame.pack();
			    frame.setSize(900, 602);
			    close();
			}
		});
		btnPresenttrips.setBounds(297, 424, 193, 23);
		panel.add(btnPresenttrips);
		
		final JButton btnContacts = new JButton();
		btnContacts.setBounds(297, 474, 193, 23);
		panel.add(btnContacts);
		
		JLabel lblEmail = new JLabel();
		lblEmail.setText("Email :");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setBounds(320, 302, 72, 20);
		panel.add(lblEmail);
			
		tFEmail = new JTextField();
		tFEmail.setBounds(386, 304, 145, 20);
		panel.add(tFEmail);
		tFEmail.setColumns(10);	
		
		if(choice == 0){
			btnContacts.setVisible(false);
			btnPastTrip.setVisible(false);
			btnPresenttrips.setVisible(false);
		}else if(choice == 1){
			tFName.setEditable(false);
			tFSurName.setEditable(false);
			tFUserName.setEditable(false);
			tFAge.setEditable(false);
			choice2.setEnabled(false);
			btnContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contacts frame = new Contacts(instance, session);
				frame.setVisible(true);
			    frame.pack();
			    frame.setSize(900, 602);
			    close();
				}
			});
		}else if(choice == 2){
			tFName.setEditable(false);
			tFSurName.setEditable(false);
			tFUserName.setEditable(false);
			tFAge.setEditable(false);
			choice2.setEnabled(false);
			btnPresenttrips.setVisible(false);
			btnContacts.setVisible(false);
			lblChangePass.setVisible(false);
			lblConfirmPass.setVisible(false);
			lblNewPass.setVisible(false);
			passwordField.setVisible(false);
			passwordField_1.setVisible(false);
			radioButton.setVisible(false);
			btnContacts.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
		}
		
		if(choice == 1){
			btnContacts.setText("Contactos");
		}else if(choice == 2){
			btnContacts.setText("Enviar Solicitud de Amistad");			
		}
		
		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//language = 1;
				lblProfile.setText("Perfil");
				lblName.setText("Nombre : ");
				lblLastName.setText("Apellido :");
				lblUser.setText("Usuario :");
				lblChangePass.setText("Contrase\u00F1a Nueva");
				lblNewPass.setText("Contrase\u00F1a Nueva :");
				lblConfirmPass.setText("Confirmar Contrase\u00F1a :");
				btnApply.setText("Aplicar");
				lblExists.setText("* Ya existe este usuario");
				label_11.setText("* Campos Obligatorios");
				btn.setText("Cancelar");
				lblAge.setText("Fecha de Nacimiento :");
				lblGender.setText("Sexo:");
				lblCalif.setText("Calificacion :");
				btnPastTrip.setText("Viajes Pasados");
				btnPresenttrips.setText("Viajes Planeados");
				lblLanguage.setText("Idioma :");
//				if(choice == 1){
				btnContacts.setText("Contactos");
//			}else if(choice == 2){
				btnContacts.setText("Enviar Solicitud de Amistad");
//				
//			}
				lblCalif.setBounds(559, 302, 123, 20);
				lblAge.setBounds(318, 141, 193, 20);
				lblConfirmPass.setBounds(371, 244, 206, 20);
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
				lblProfile.setText("Profile");
				lblName.setText("Name : ");
				lblLastName.setText("Last Name :");
				lblUser.setText("User :");
				lblChangePass.setText("Change Password");
				lblNewPass.setText("New Password :");
				lblConfirmPass.setText("Confirm Password :");
				btnApply.setText("Apply");
				lblExists.setText("* The user already exists");
				label_11.setText("* Mandatory fields");
				btn.setText("Cancel");
				lblAge.setText("Birth Date :");
				lblGender.setText("Sex:");
				lblCalif.setText("Rating :");
				btnPastTrip.setText("Old Trips");
				btnPresenttrips.setText("Planned Trips");
				lblLanguage.setText("Language :");
				
//				if(choice == 1){
				btnContacts.setText("Contactos");
//			}else if(choice == 2){
				btnContacts.setText("Enviar Solicitud de Amistad");
//				
//			}
				lblCalif.setBounds(600, 302, 123, 20);
				lblAge.setBounds(380, 141, 193, 20);
				lblConfirmPass.setBounds(395, 244, 206, 20);
			}
		});
		ImageIcon imageE = new ImageIcon("EnglishFlag.jpg"); 
		panel.add(img2);
		img2.setIcon(imageE); 
		img2.setSize(22,18); 
		img2.setLocation(760,11); 
		img2.setVisible(true); 
		
		
		choice2.add("Femenino");
		choice2.add("Masculino");
		lblLanguage.setText("Idioma :");
		btnContacts.setText("Enviar Solicitud de Amistad");
		btnPresenttrips.setText("Viajes Planeados");
		lblProfile.setText("Perfil");
		lblName.setText("Nombre : ");
		lblLastName.setText("Apellido :");
		lblUser.setText("Usuario :");
		lblChangePass.setText("Contrase\u00F1a Nueva");
		lblNewPass.setText("Contrase\u00F1a Nueva :");
		lblConfirmPass.setText("Confirmar Contrase\u00F1a :");
		btnApply.setText("Aplicar");
		lblExists.setText("* Ya existe este usuario");
		label_11.setText("* Campos Obligatorios");
		btn.setText("Cancelar");
		lblAge.setText("Fecha de Nacimiento :");
		lblGender.setText("Sexo:");
		lblCalif.setText("Calificacion :");
		btnPastTrip.setText("Viajes Pasados");
	}
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

		}
}
