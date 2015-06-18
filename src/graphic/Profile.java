package graphic;

import graphic.Connect;
import graphic.Contacts;
import graphic.ImagePanel;
import graphic.OldTrips;
import graphic.Options;
import graphic.TripGroups;
import graphic.UserSearch;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;


import javax.swing.JPasswordField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Choice;
import java.rmi.ServerException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import controllers.Application;
import domain.ControllerNotLoadedException;
import domain.InvalidPasswordException;
import domain.InvalidPermissionException;
import controllers.Session;
import domain.SessionNotActiveException;
import domain.UserNameAlreadyExistsException;



public class Profile extends JFrame {

	private static JPanel panel;
	private JTextField tFName;
	private JTextField tFSurName;
	private JTextField tFUserName;
	private Integer flag;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JFormattedTextField tFAge;
	private JTextField tFRate;
	private JTextField tFEmail;
	private JTextField tFCityBirth;
	private JTextField tFTripNum;
	private ResourceBundle messages;
	private JTextField tFFUserName;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile(null, 1, null,true);
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
	/**
	 * Create the Frame
	 * @param instance
	 * @param choice
	 * @param session
	 * @param language
	 */
	public Profile(final Application instance, final Integer choice, final Session session, final boolean language) {
		
		final JLabel label_12 = new JLabel("*");  //$NON-NLS-1$
		final JLabel lblCityBirth = new JLabel();
		Locale currentLocale;
		if(language){
			currentLocale = new Locale("en","US"); //$NON-NLS-1$ //$NON-NLS-2$
			lblCityBirth.setBounds(100, 302, 206, 28);
			label_12.setBounds(85, 304, 15, 22);
		}else{
			currentLocale = new Locale("es","AR"); //$NON-NLS-1$ //$NON-NLS-2$
			lblCityBirth.setBounds(46, 302, 206, 28);
			label_12.setBounds(29, 304, 15, 22);
		}
		messages = ResourceBundle.getBundle("MessagesBundle", currentLocale); 	 //$NON-NLS-1$
		
		panel = new ImagePanel(new ImageIcon(messages.getString("Profile.0")).getImage()); //$NON-NLS-1$
		panel.setBackground(Color.BLACK);
		setTitle(messages.getString("Profile.1")); //$NON-NLS-1$
		setBounds(0, 0, 901, 616);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setLayout(null);
		setContentPane(panel);

		
		final JLabel lblProfile = new JLabel();
		lblProfile.setFont(new Font("Tahoma", Font.BOLD, 24)); //$NON-NLS-1$
		lblProfile.setForeground(Color.BLACK);
		lblProfile.setBounds(361, 20, 134, 49);
		panel.add(lblProfile);
		
		final JLabel lblName = new JLabel();
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18)); //$NON-NLS-1$
		lblName.setForeground(Color.BLACK);
		lblName.setBounds(139, 95, 108, 20);
		panel.add(lblName);
		
		final JLabel lblLastName = new JLabel();
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 18)); //$NON-NLS-1$
		lblLastName.setForeground(Color.BLACK);
		lblLastName.setBounds(438, 95, 101, 20);
		panel.add(lblLastName);
		
		final JLabel lblUser = new JLabel();
		lblUser.setForeground(Color.BLACK);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 18)); //$NON-NLS-1$
		lblUser.setBounds(70, 141, 108, 20);
		panel.add(lblUser);
		
		final JLabel lblChangePass = new JLabel();
		lblChangePass.setForeground(Color.BLACK);
		lblChangePass.setFont(new Font("Tahoma", Font.PLAIN, 20)); //$NON-NLS-1$
		lblChangePass.setBounds(334, 197, 238, 25);
		panel.add(lblChangePass);
		
		final JLabel lblNewPass = new JLabel();
		lblNewPass.setFont(new Font("Tahoma", Font.PLAIN, 18)); //$NON-NLS-1$
		lblNewPass.setForeground(Color.BLACK);
		lblNewPass.setBounds(46, 244, 179, 20);
		panel.add(lblNewPass);
		
		final JLabel lblConfirmPass = new JLabel();
		lblConfirmPass.setForeground(Color.BLACK);
		lblConfirmPass.setFont(new Font("Tahoma", Font.PLAIN, 18)); //$NON-NLS-1$
		lblConfirmPass.setBounds(371, 244, 206, 20);
		panel.add(lblConfirmPass);
		
		tFName = new JTextField();
		tFName.setDisabledTextColor(Color.BLACK);
		tFName.setHorizontalAlignment(SwingConstants.CENTER);
		tFName.setFont(new Font("Tahoma", Font.PLAIN, 16)); //$NON-NLS-1$
		tFName.setEnabled(true);
		tFName.setColumns(10);
		tFName.setBounds(254, 95, 123, 20);
		panel.add(tFName);
		
		tFSurName = new JTextField();
		tFSurName.setDisabledTextColor(Color.BLACK);
		tFSurName.setHorizontalAlignment(SwingConstants.CENTER);
		tFSurName.setFont(new Font("Tahoma", Font.PLAIN, 16)); //$NON-NLS-1$
		tFSurName.setEnabled(true);
		tFSurName.setColumns(10);
		tFSurName.setBounds(559, 96, 123, 20);
		panel.add(tFSurName);
		
		tFUserName = new JTextField();
		tFUserName.setDisabledTextColor(Color.BLACK);
		tFUserName.setFont(new Font("Tahoma", Font.PLAIN, 16)); //$NON-NLS-1$
		tFUserName.setHorizontalAlignment(SwingConstants.CENTER);
		tFUserName.setEnabled(true);
		tFUserName.setColumns(10);
		tFUserName.setBounds(148, 142, 123, 20);
		panel.add(tFUserName);

		final JButton btnApply = new JButton();
		btnApply.setBounds(663, 544, 134, 23);
		panel.add(btnApply);
		
		final JRadioButton radioButton = new JRadioButton(); //$NON-NLS-1$
		radioButton.setBounds(284, 197, 21, 23);
		panel.add(radioButton);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16)); //$NON-NLS-1$
		passwordField.setBounds(214, 244, 123, 20);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 16)); //$NON-NLS-1$
		passwordField_1.setBounds(587, 244, 123, 20);
		panel.add(passwordField_1);
		
		tFCityBirth = new JTextField();
		tFCityBirth.setFont(new Font("Tahoma", Font.PLAIN, 16)); //$NON-NLS-1$
		tFCityBirth.setBounds(250, 305, 123, 20);
		panel.add(tFCityBirth);
		tFCityBirth.setColumns(10);
		
		final JLabel lblExists = new JLabel();
		
		lblExists.setForeground(Color.BLACK);
		lblExists.setBorder(null);
		lblExists.setBounds(80, 156, 158, 27);
		panel.add(lblExists);
		lblExists.setVisible(false);
		
		final JLabel label_6 = new JLabel("*"); //$NON-NLS-1$
		label_6.setVisible(false);
		label_6.setForeground(Color.BLACK);
		label_6.setBounds(62, 142, 15, 22);
		panel.add(label_6);
		
		final JLabel label_7 = new JLabel("*"); //$NON-NLS-1$
		label_7.setVisible(false);
		label_7.setForeground(Color.BLACK);
		label_7.setBounds(131, 95, 15, 22);
		panel.add(label_7);
		
		final JLabel label_8 = new JLabel("*"); //$NON-NLS-1$
		label_8.setVisible(false);
		label_8.setForeground(Color.BLACK);
		label_8.setBounds(430, 95, 15, 22);
		panel.add(label_8);
		
		final JLabel label_9 = new JLabel("*"); //$NON-NLS-1$
		label_9.setVisible(false);
		label_9.setForeground(Color.BLACK);
		label_9.setBounds(266, 199, 15, 22);
		panel.add(label_9);
		
		final JLabel label_10 = new JLabel("*"); //$NON-NLS-1$
		label_10.setVisible(false);
		label_10.setForeground(Color.BLACK);
		label_10.setBounds(310, 304, 15, 22);
		panel.add(label_10);
		
		label_12.setVisible(false);
		label_12.setForeground(Color.BLACK);
		panel.add(label_12);
		
		final JLabel label_11 = new JLabel();
		label_11.setVisible(false);
		label_11.setForeground(Color.BLACK);
		label_11.setBounds(51, 20, 134, 20);
		panel.add(label_11);
		
		final JButton btn = new JButton();
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(choice == 1){
					Options frame = new Options(instance, session,language);
					frame.setVisible(true);
					frame.pack();
					frame.setSize(900, 602);
					close();
				}else if(choice == 2){
					UserSearch frame = new UserSearch(instance, session, language);
					frame.setVisible(true);
					frame.pack();
					frame.setSize(900, 602);
					close();
				}
			}
		});
		btn.setBounds(80, 544, 134, 23);
		panel.add(btn);
		
		final JLabel lblAge = new JLabel();
		lblAge.setForeground(Color.BLACK);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 18)); //$NON-NLS-1$
		lblAge.setBounds(318, 141, 193, 20);
		panel.add(lblAge);
		
		final JLabel label_2 = new JLabel("*"); //$NON-NLS-1$
		label_2.setForeground(Color.BLACK);
		label_2.setBounds(310, 141, 15, 28);
		panel.add(label_2);
		label_2.setVisible(false);
		
		final JLabel lblGender = new JLabel();
		lblGender.setForeground(Color.BLACK);
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 18)); //$NON-NLS-1$
		lblGender.setBounds(648, 142, 54, 22);
		panel.add(lblGender);
		
		final JLabel label_4 = new JLabel("*"); //$NON-NLS-1$
		label_4.setForeground(Color.BLACK);
		label_4.setBounds(640, 141, 15, 28);
		panel.add(label_4);
		label_4.setVisible(false);
		
		final Choice choice2 = new Choice();
		choice2.setBounds(712, 143, 85, 20);
		panel.add(choice2);
		
		tFAge = new JFormattedTextField();
		try {
			MaskFormatter dateMask = new MaskFormatter("##/##/####");  //$NON-NLS-1$
            dateMask.install(tFAge);
        }catch (ParseException ex) {
        }
		tFAge.setFont(new Font("Tahoma", Font.PLAIN, 16)); //$NON-NLS-1$
		tFAge.setHorizontalAlignment(SwingConstants.CENTER);
		tFAge.setBounds(505, 141, 97, 20);
		panel.add(tFAge);
		tFAge.setColumns(10);
		
		final JLabel lblCalif = new JLabel();
		lblCalif.setForeground(Color.BLACK);
		lblCalif.setFont(new Font("Tahoma", Font.PLAIN, 18)); //$NON-NLS-1$
		lblCalif.setBounds(649, 302, 123, 20);
		panel.add(lblCalif);
		
		tFRate = new JTextField();
		tFRate.setCaretColor(Color.BLACK);
		tFRate.setForeground(Color.BLACK);
		tFRate.setEditable(false);
		tFRate.setHorizontalAlignment(SwingConstants.CENTER);
		tFRate.setFont(new Font("Tahoma", Font.PLAIN, 16)); //$NON-NLS-1$
		tFRate.setEnabled(false);
		tFRate.setDisabledTextColor(Color.BLACK);
		tFRate.setColumns(10);
		tFRate.setBounds(764, 305, 111, 20);
		panel.add(tFRate);	
		
		final JButton btnPastTrip = new JButton();
		btnPastTrip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OldTrips frame = new OldTrips(instance, session, choice, language);
				frame.setVisible(true);
				frame.pack();
				frame.setSize(900, 602);
				close();
			}
		});
		btnPastTrip.setBounds(297, 410, 193, 23);
		panel.add(btnPastTrip);
		
		if(session != null){
			try {
				tFName.setText(instance.getCurrentProfileController().getUsername());
				tFUserName.setText(instance.getCurrentProfileController().getUserName());
				tFSurName.setText(instance.getCurrentProfileController().getSurname());
				tFAge.setText(instance.getCurrentProfileController().getBirthday().toString());
				tFEmail.setText(instance.getCurrentProfileController().getMail());
				tFRate.setText(getReview(instance.getCurrentProfileController().getRating()));
				tFTripNum.setText(	((Integer) instance.getCurrentProfileController().getTrips().size()).toString());
			} catch (SessionNotActiveException | ControllerNotLoadedException e1) {
			
			}
		}
		

		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//flag=0 corresponde a que no hay error
				//flag=1 error en la igualdad de constrase�as
				//flag=2 no introdujo un mail valido
				//flag=3 no introdujo campos obligatorios el usuario que se esta registrando
				//flag=4 no introdujo una fecha de nacimineto correcta
				if(instance != null){
					flag = 0;
					if(choice == 1){
						if(radioButton.isSelected()){
							if(passwordField.getText().equals(passwordField_1.getText()) && !passwordField.getText().equals("") ){ //$NON-NLS-1$
								flag = 0;
								if(instance != null){
									try {
											instance.getCurrentProfileController().changePass(passwordField.getText(), passwordField_1.getText());
									} catch (InvalidPasswordException e) {
										
									}catch (SessionNotActiveException e) {
										e.printStackTrace();
									}
								}
							}
							else{
								flag = 1;
							}
						}
						if(tFEmail.getText().isEmpty() || !validate(tFEmail.getText().trim())){
							flag = 2;
						}
						if(flag == 0){
							Options frame = new Options(instance, session,language);
							frame.setVisible(true);
							frame.setSize(900, 602);
							close();
						}else{
							if(flag == 1){
								JOptionPane.showMessageDialog(null, messages.getString("Profile.29"), messages.getString("Profile.30"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
							}else if(flag == 2){
								JOptionPane.showMessageDialog(null, messages.getString("Profile.31"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
							}
						}
					}
					
					if(choice == 0){
						
						if(tFName.getText().isEmpty() || tFSurName.getText().isEmpty() || tFUserName.getText().isEmpty() || tFAge.getText().isEmpty() || tFEmail.getText().isEmpty()){
							flag = 3;
						}else if(!validate(tFEmail.getText().trim())){
							flag = 2;
						}else if(!passwordField.getText().equals(passwordField_1.getText()) || passwordField.getText().isEmpty() ){
							flag = 1;
						}
						
						boolean sex;
						if(choice2.getSelectedItem().equals(messages.getString("Profile.33"))){ //$NON-NLS-1$
							sex = true;
						}else{
							sex= false;
						}	
						
						int day = Integer.parseInt(tFAge.getText().substring(0, 2));
						int month = Integer.parseInt(tFAge.getText().substring(3, 5));
						int year = Integer.parseInt(tFAge.getText().substring(6, 10));
						Date date = null;
						String inputDate = new String(year + "-" + month + "-" + day);  //$NON-NLS-1$ //$NON-NLS-2$
						try {
						    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  //$NON-NLS-1$
						    formatter.setLenient(false);
						    date = formatter.parse(inputDate);
						} catch (ParseException e) { 
						    flag = 4;
						}
						if(flag == 0){
							try {
								instance.registerUser(tFName.getText(), tFSurName.getText(), tFUserName.getText(), date , sex , passwordField.getText(), tFCityBirth.getText(), tFAge.getText()); //$NON-NLS-1$
							} catch (ServerException e) {
								e.printStackTrace();
							} catch (UserNameAlreadyExistsException e) {
								lblExists.setVisible(true);
							}	
							Connect frame = new Connect(language);
							frame.setVisible(true);
							frame.setSize(900, 602);
							close();
						}else if(flag == 1){
								JOptionPane.showMessageDialog(null, messages.getString("Profile.38"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
						}else if(flag == 2){
								JOptionPane.showMessageDialog(null, messages.getString("Profile.40"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
						}else if(flag == 3){
							JOptionPane.showMessageDialog(null, messages.getString("Profile.42"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
						}else if(flag == 4){
							JOptionPane.showMessageDialog(null, messages.getString("Profile.44"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
						}
					}
				}
			}
		});
		
		lblCityBirth.setForeground(Color.BLACK);
		lblCityBirth.setBackground(Color.BLACK);
		lblCityBirth.setFont(new Font("Tahoma", Font.PLAIN, 18)); //$NON-NLS-1$
		panel.add(lblCityBirth);
		
		final JButton btnPresenttrips = new JButton();
		btnPresenttrips.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TripGroups frame = new TripGroups(instance, session,language);
				frame.setVisible(true);
				frame.pack();
				frame.setSize(900, 602);
				close();
			}
		});
		btnPresenttrips.setBounds(297, 450, 193, 23);
		panel.add(btnPresenttrips);
		
		final JButton btnContacts = new JButton();
		btnContacts.setBounds(297, 490, 193, 23);
		panel.add(btnContacts);
		
		final JLabel lblNumbertrips = new JLabel(); 
		lblNumbertrips.setFont(new Font("Tahoma", Font.PLAIN, 18)); //$NON-NLS-1$
		lblNumbertrips.setBounds(200, 345, 220, 20);
		lblNumbertrips.setForeground(Color.BLACK);
		panel.add(lblNumbertrips);
		
		tFTripNum = new JTextField();
		tFTripNum.setHorizontalAlignment(SwingConstants.CENTER);
		tFTripNum.setDisabledTextColor(Color.BLACK);
		tFTripNum.setFont(new Font("Tahoma", Font.PLAIN, 15)); //$NON-NLS-1$
		tFTripNum.setForeground(Color.BLACK);
		tFTripNum.setEditable(false);
		tFTripNum.setBounds(420, 345, 163, 20);
		panel.add(tFTripNum);
		tFTripNum.setColumns(10);
		
		final JLabel lblEmail = new JLabel();
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18)); //$NON-NLS-1$
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setBounds(428, 303, 72, 20);
		panel.add(lblEmail);
			
		tFEmail = new JTextField();
		tFEmail.setFont(new Font("Tahoma", Font.PLAIN, 16)); //$NON-NLS-1$
		tFEmail.setHorizontalAlignment(SwingConstants.CENTER);
		tFEmail.setBounds(494, 305, 145, 20);
		panel.add(tFEmail);
		tFEmail.setColumns(10);	
		
		tFFUserName = new JTextField();
		
		final Object[] fields = {
				"Escribir nombre de Usuario", tFFUserName,  //$NON-NLS-1$
		};
		
		final Object[] options = {"A�adir Usuario","Cancelar"}; //$NON-NLS-1$ //$NON-NLS-2$
		
		if(choice == 0){
			btnContacts.setVisible(false);
			btnPastTrip.setVisible(false);
			btnPresenttrips.setVisible(false);
			btn.setVisible(false);
			tFName.setEnabled(true);
			tFSurName.setEnabled(true);
			tFUserName.setEnabled(true);
			label_2.setVisible(true);
			label_4.setVisible(true);
			label_6.setVisible(true);
			label_7.setVisible(true);
			label_8.setVisible(true);
			label_9.setVisible(true);
			label_10.setVisible(true);
			label_11.setVisible(true);
			label_12.setVisible(true);
			lblCalif.setVisible(false);
			tFRate.show(false);
			radioButton.setVisible(false);
			lblNumbertrips.setVisible(false);
			tFTripNum.setVisible(false);
			
		}else if(choice == 1){
			tFCityBirth.setEditable(false);
			tFName.setEditable(false);
			tFSurName.setEditable(false);
			tFUserName.setEditable(false);
			tFAge.setEditable(false);
			choice2.setEnabled(false);
			btnContacts.setText(messages.getString("Profile.50")); //$NON-NLS-1$
			btnContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contacts frame = new Contacts(instance, session,language);
				frame.setVisible(true);
			    frame.pack();
			    frame.setSize(900, 602);
			    close();
				}
			});
		}else if(choice == 2){
			btnApply.setVisible(false);
			tFCityBirth.setEditable(false);
			tFName.setEditable(false);
			tFSurName.setEditable(false);
			tFUserName.setEditable(false);
			tFAge.setEditable(false);
			tFEmail.setEditable(false);
			choice2.setEnabled(false);
			btnPresenttrips.setVisible(false);
			lblChangePass.setVisible(false);
			lblConfirmPass.setVisible(false);
			lblNewPass.setVisible(false);
			passwordField.setVisible(false);
			passwordField_1.setVisible(false);
			radioButton.setVisible(false);
			btnContacts.setVisible(true);
			btnContacts.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {


					
					
					
					int confirm = JOptionPane.showOptionDialog(null, fields, messages.getString("Group.11"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,options[1]); //$NON-NLS-1$
					if(confirm == JOptionPane.OK_OPTION){
						while( !tFFUserName.getText().isEmpty() ){
							JOptionPane.showMessageDialog(null, "No existe el usuario que quiere agregar", "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
							confirm = JOptionPane.showOptionDialog(null, fields, messages.getString("Group.11"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,options[1]); //$NON-NLS-1$
						}
						try {
							//if(instance.getCurrentProfileController().)session. && instance != null
								instance.getCurrentProfileController().sendFriendRequest(tFFUserName.getText());
						} catch (SessionNotActiveException e1) {
							e1.printStackTrace();
						} catch (ControllerNotLoadedException e1) {
							e1.printStackTrace();
						} catch (InvalidPermissionException e1) {
							e1.printStackTrace();
						}/*catch (NO EXISTE EL USUARIO e1) {
							JOptionPane.showMessageDialog(null, "No existe el usuario que quiere agregar", "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
						}*/
						JOptionPane.showMessageDialog(null, "Se mando de manera exitosa la solicitud", "Felicitaciones", JOptionPane.INFORMATION_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
					}
					
					
					
					
					
					
					
					
				}
			});
			lblUser.setBounds(70, 200, 108, 20);
			tFUserName.setBounds(148, 200, 123, 20);
			lblAge.setBounds(318, 200, 193, 20);
			tFAge.setBounds(505, 200, 97, 20);
			lblGender.setBounds(648, 200, 54, 22);
			choice2.setBounds(712, 200, 85, 20);
			btnPastTrip.setBounds(297, 400, 193, 23);
		}
		
		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Profile frame = new Profile(instance, choice, session, false);
				frame.setVisible(true);
			    frame.pack();
			    frame.setSize(900, 620);
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
				Profile frame = new Profile(instance, choice, session, true);
				frame.setVisible(true);
			    frame.pack();
			    frame.setSize(900, 620);
			    close();
			}
		});
		ImageIcon imageE = new ImageIcon("EnglishFlag.jpg");  //$NON-NLS-1$
		panel.add(img2);
		img2.setIcon(imageE); 
		img2.setSize(22,18); 
		img2.setLocation(760,11); 
		img2.setVisible(true); 
		
		if(choice == 1){
			btnContacts.setText(messages.getString("Profile.53")); //$NON-NLS-1$
			btnApply.setText(messages.getString("Profile.54")); //$NON-NLS-1$
		}else if(choice == 2){
			btnContacts.setText(messages.getString("Profile.55")); //$NON-NLS-1$
		}else if(choice == 0){
			btnApply.setText(messages.getString("Profile.56")); //$NON-NLS-1$
		}
		
		lblEmail.setText(messages.getString("Profile.47")); //$NON-NLS-1$
		choice2.add(messages.getString("Profile.57")); //$NON-NLS-1$
		choice2.add(messages.getString("Profile.58")); //$NON-NLS-1$
		lblCityBirth.setText(messages.getString("Profile.59")); //$NON-NLS-1$
		btnPresenttrips.setText(messages.getString("Profile.60")); //$NON-NLS-1$
		lblProfile.setText(messages.getString("Profile.61")); //$NON-NLS-1$
		lblName.setText(messages.getString("Profile.62")); //$NON-NLS-1$
		lblLastName.setText(messages.getString("Profile.63")); //$NON-NLS-1$
		lblUser.setText(messages.getString("Profile.64")); //$NON-NLS-1$
		lblChangePass.setText(messages.getString("Profile.65")); //$NON-NLS-1$
		lblNewPass.setText(messages.getString("Profile.66")); //$NON-NLS-1$
		lblConfirmPass.setText(messages.getString("Profile.67")); //$NON-NLS-1$
		lblExists.setText(messages.getString("Profile.68")); //$NON-NLS-1$
		label_11.setText(messages.getString("Profile.69")); //$NON-NLS-1$
		btn.setText(messages.getString("Profile.70")); //$NON-NLS-1$
		lblAge.setText(messages.getString("Profile.71")); //$NON-NLS-1$
		lblGender.setText(messages.getString("Profile.72")); //$NON-NLS-1$
		lblCalif.setText(messages.getString("Profile.73")); //$NON-NLS-1$
		btnPastTrip.setText(messages.getString("Profile.74")); //$NON-NLS-1$
		lblNumbertrips.setText(messages.getString("Profile.15")); //$NON-NLS-1$
	}
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

	}
	
	/**
	 * 
	 * @param rate
	 * @return review
	 */
	public String getReview (Double rate){
		if(rate >= 0.0 && rate < 0.9999){
			return messages.getString("Profile.16"); //$NON-NLS-1$
		}else if(rate >= 1.0000 && rate < 1.9999){
			return messages.getString("Profile.17"); //$NON-NLS-1$
		}else if(rate >= 2.0000 && rate < 2.9999){
			return messages.getString("Profile.18"); //$NON-NLS-1$
		}else if(rate >= 3.0000 && rate < 3.9999){
			return messages.getString("Profile.19"); //$NON-NLS-1$
		}else if(rate >= 4.0000 && rate < 4.9999){
			return messages.getString("Profile.20"); //$NON-NLS-1$
		}
		return messages.getString("Profile.21"); //$NON-NLS-1$
	}
	
	/**
	* Validate hex with regular expression
	*  @param hex
	*  hex for validation
	* @return true valid hex, false invalid hex
	*/
	public static boolean validate(final String hex) {
		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(hex);
		return matcher.matches();
	}
}
