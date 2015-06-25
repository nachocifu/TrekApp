package graphic;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.ListSelectionModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.Application;
import controllers.CurrentProfileController;
import controllers.ProfileController;
import controllers.TripController;
import domain.ControllerNotLoadedException;
import controllers.Session;
import domain.SessionNotActiveException;


public class OldTrips extends JFrame {

	private static JPanel panel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnBack;
	private CurrentProfileController currentProfile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OldTrips frame = new OldTrips(null, null, 1 , null, true);
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
	 * @param choice
	 * @param language
	 */
	public OldTrips(final Application instance, final Session session, final Integer choice, final ProfileController invitedProfile, final boolean language) {
		
		if(instance != null){
			try {
				currentProfile = instance.getCurrentProfileController();
			} catch (SessionNotActiveException e1) {
				e1.printStackTrace();
			}
		}
		
		Locale currentLocale;
		if(language){
			currentLocale = new Locale("en","US"); 
		}else{
			currentLocale = new Locale("es","AR");
		}
		final ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale); 
		
		setTitle("TrekApp"); //$NON-NLS-1$
		setBounds(0, 0, 902, 602);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new ImagePanel(new ImageIcon("OldTrips.jpg").getImage()); //$NON-NLS-1$
		panel.setBackground(new Color(25, 25, 112));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setLayout(null);
		setContentPane(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 217, 335);
		panel.add(scrollPane);
		
		final JList<String> list_1 = new JList<String>();
		scrollPane.setViewportView(list_1);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setForeground(Color.WHITE);
		list_1.setFont(new Font("Tahoma", Font.PLAIN, 12)); //$NON-NLS-1$
		list_1.setBorder(UIManager.getBorder("InternalFrame.border")); //$NON-NLS-1$
		list_1.setBackground(new Color(0, 0, 128));
		
		final JLabel lblOldTrips = new JLabel();
		lblOldTrips.setForeground(Color.BLACK);
		lblOldTrips.setFont(new Font("Tahoma", Font.BOLD, 20)); //$NON-NLS-1$
		lblOldTrips.setBounds(29, 54, 183, 34);
		panel.add(lblOldTrips);
		
		final JLabel lblFrom = new JLabel();
		lblFrom.setForeground(Color.WHITE);
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 15)); //$NON-NLS-1$
		lblFrom.setBounds(291, 192, 180, 35);
		panel.add(lblFrom);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17)); //$NON-NLS-1$
		textField.setDisabledTextColor(Color.BLACK);
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setBounds(518, 202, 168, 27);
		panel.add(textField);
		
		final JLabel label_1 = new JLabel();
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15)); //$NON-NLS-1$
		label_1.setBounds(343, 261, 106, 35);
		panel.add(label_1);
		
		final JLabel lblTo = new JLabel();
		lblTo.setForeground(Color.WHITE);
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 15)); //$NON-NLS-1$
		lblTo.setBounds(289, 241, 183, 35);
		panel.add(lblTo);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 17)); //$NON-NLS-1$
		textField_1.setDisabledTextColor(Color.BLACK);
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(517, 249, 168, 27);
		panel.add(textField_1);
		
		final JLabel lblDescription = new JLabel();
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 15)); //$NON-NLS-1$
		lblDescription.setBounds(289, 357, 219, 35);
		panel.add(lblDescription);
		
		final JLabel label_4 = new JLabel();
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 15)); //$NON-NLS-1$
		label_4.setBounds(469, 251, 25, 35);
		panel.add(label_4);
		
		JScrollPane scrollPane_1 = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(518, 329, 286, 137);
		panel.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setDisabledTextColor(Color.BLACK);
		textArea.setEnabled(false);
		scrollPane_1.setViewportView(textArea);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 13)); //$NON-NLS-1$
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		final JLabel lblLeavingOn = new JLabel();
		lblLeavingOn.setForeground(Color.WHITE);
		lblLeavingOn.setFont(new Font("Tahoma", Font.BOLD, 15)); //$NON-NLS-1$
		lblLeavingOn.setBounds(289, 108, 205, 27);
		panel.add(lblLeavingOn);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 17)); //$NON-NLS-1$
		textField_2.setDisabledTextColor(Color.BLACK);
		textField_2.setEnabled(false);
		textField_2.setBounds(518, 108, 168, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		final JLabel lblReturningOn = new JLabel();
		lblReturningOn.setForeground(Color.WHITE);
		lblReturningOn.setFont(new Font("Tahoma", Font.BOLD, 15)); //$NON-NLS-1$
		lblReturningOn.setBounds(289, 155, 205, 27);
		panel.add(lblReturningOn);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 17)); //$NON-NLS-1$
		textField_3.setDisabledTextColor(Color.BLACK);
		textField_3.setEnabled(false);
		textField_3.setColumns(10);
		textField_3.setBounds(518, 155, 168, 26);
		panel.add(textField_3);
		
		DefaultListModel<String> trips = new DefaultListModel<String>();
		Collection<TripController> pastTripsAux = null;
		if(instance != null){
			try {
				pastTripsAux = currentProfile.getTrips();
			} catch (SessionNotActiveException e3) {
				e3.printStackTrace();
			} catch (ControllerNotLoadedException e3) {
				e3.printStackTrace();
			}
		
			final ArrayList<TripController> pastTrips = new ArrayList<>(pastTripsAux);
		   
	    	try {
	    		for (TripController each : pastTrips) {
	    			trips.addElement(each.getStartDate().toString() + " " + each.getEndDate().toString() + " " + each.getOriginCity() + " " + each.getEndCity()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	    		}
			} catch (SessionNotActiveException e1) {
				e1.printStackTrace();
			} catch (ControllerNotLoadedException e1) {
				e1.printStackTrace();
			}
			
		    list_1.setModel(trips);
		    list_1.addMouseListener(new MouseAdapter()
	        {
	            @Override
	            public void mousePressed(MouseEvent e)
	            {
					try {
						if(list_1.getSelectedIndex() >= 0){
							textField_2.setText(pastTrips.get(list_1.getSelectedIndex()).getStartDate().toString());
							textField_3.setText(pastTrips.get(list_1.getSelectedIndex()).getEndDate().toString());	
							textField.setText(pastTrips.get(list_1.getSelectedIndex()).getOriginCity());	
							textField_1.setText(pastTrips.get(list_1.getSelectedIndex()).getEndCity());	
						}
						
					} catch (SessionNotActiveException e1) {
						e1.printStackTrace();
					} catch (ControllerNotLoadedException e1) {
						e1.printStackTrace();
					}
					
	            }
	        });
		}
		
		btnBack = new JButton();
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile frame = new Profile(instance, choice, session, invitedProfile,language);
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
				OldTrips frame = new OldTrips(instance, session, choice, invitedProfile,false);
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
				OldTrips frame = new OldTrips(instance, session, choice, invitedProfile,true);
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
		
		lblOldTrips.setText(messages.getString("OldTrips.22")); //$NON-NLS-1$
		lblFrom.setText(messages.getString("OldTrips.23")); //$NON-NLS-1$
		label_1.setText(messages.getString("OldTrips.24")); //$NON-NLS-1$
		lblTo.setText(messages.getString("OldTrips.25")); //$NON-NLS-1$
		lblDescription.setText(messages.getString("OldTrips.26")); //$NON-NLS-1$
		lblReturningOn.setText(messages.getString("OldTrips.27")); //$NON-NLS-1$
		lblLeavingOn.setText(messages.getString("OldTrips.28")); //$NON-NLS-1$
		btnBack.setText(messages.getString("OldTrips.29")); //$NON-NLS-1$
		label_4.setText(messages.getString("OldTrips.30")); //$NON-NLS-1$
	}
	
	/**
	 * Closes a frame after an event
	 */
	public void close(){
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
}
