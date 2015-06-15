package graphic;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Date;
import java.util.ResourceBundle;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import controllers.Application;
import controllers.GroupController;
import controllers.MyTripController;
import controllers.TripController;
import domain.ControllerNotLoadedException;
import controllers.Session;
import domain.SessionNotActiveException;
import domain.UserNameAlreadyExistsException;


public class Trip extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8548069016778574983L;
	private static JPanel panel;
	private ObservingTextField tFLeaving;
	private ObservingTextField tFArriving;
	private JTextField tFStatus;
	private DatePicker dp1;
	private JTextField tFFrom;
	private JTextField tFTo;
	private JTextField tFCost;

	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Trip frame = new Trip(1,null,null,null, null, null, null,true);
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
	public Trip(final Integer choice, final TripController trip, final MyTripController myTrip, final ArrayList<String> auxText, final Application instance, final Session session, final GroupController groupController, final boolean language){
		
		final JLabel lblState = new JLabel();
		final JLabel lblFrom = new JLabel();
		final JLabel lblTo = new JLabel();
		Locale currentLocale;
		if(language){
			currentLocale = new Locale("en","US");
			lblState.setBounds(450, 87, 180, 35);
			lblFrom.setBounds(170, 212, 180, 35);
			lblTo.setBounds(180, 285, 211, 35);
		}else{
			currentLocale = new Locale("es","AR");
			lblState.setBounds(412, 87, 180, 35);
			lblFrom.setBounds(26, 212, 180, 35);
			lblTo.setBounds(26, 267, 211, 35);
		}
		ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale); 
		
		panel = new ImagePanel(new ImageIcon("Trip.jpg").getImage()); //$NON-NLS-1$
		setContentPane(panel);
		setTitle("TreckApp"); //$NON-NLS-1$
		setBounds(0, 0, 766, 616);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		panel.setEnabled(true);
		panel.setLayout(null);
		
		final JButton btnNewButton = new JButton();
		
		tFLeaving = new ObservingTextField();
		tFLeaving.setEnabled(false);
		tFLeaving.setDisabledTextColor(Color.BLACK);
		tFLeaving.setHorizontalAlignment(SwingConstants.CENTER);
		tFLeaving.setFont(new Font("Tahoma", Font.PLAIN, 15)); //$NON-NLS-1$
		tFLeaving.setBounds(176, 77, 106, 28);
		panel.add(tFLeaving);
		tFLeaving.setColumns(10);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(choice == 1 || choice == 0){					
					String lang = null;
					final Locale locale = getLocale(lang);
					DatePicker dp = new DatePicker(tFLeaving, locale);
					Date selectedDate = dp.parseDate(tFLeaving.getText());
					dp.setSelectedDate(selectedDate);
					dp.start(tFLeaving);
				}else{
					btnNewButton.setEnabled(false);
				}
			}
		});
		
		btnNewButton.setBounds(36, 77, 121, 28);
		panel.add(btnNewButton);
		
		final JLabel lblTrip = new JLabel();
		lblTrip.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrip.setFont(new Font("Tahoma", Font.BOLD, 27)); //$NON-NLS-1$
		lblTrip.setForeground(Color.WHITE);
		lblTrip.setBounds(390, 20, 92, 35);
		panel.add(lblTrip);
		
		tFArriving = new ObservingTextField();
		tFArriving.setDisabledTextColor(Color.BLACK);
		tFArriving.setHorizontalAlignment(SwingConstants.CENTER);
		tFArriving.setFont(new Font("Tahoma", Font.PLAIN, 15)); //$NON-NLS-1$
		tFArriving.setEnabled(false);
		tFArriving.setColumns(10);
		tFArriving.setBounds(176, 123, 106, 28);
		panel.add(tFArriving);
		
		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(choice == 1 || choice == 0){
					String lang = null;
					final Locale locale = getLocale(lang);
					dp1 = new DatePicker(tFArriving, locale);
					Date selectedDate = dp1.parseDate(tFArriving.getText());
					dp1.setSelectedDate(selectedDate);
					dp1.start(tFArriving);
				}else{
					button.setEnabled(false);
				}
			}
		});
		button.setBounds(36, 123, 121, 28);
		panel.add(button);
		
		lblState.setFont(new Font("Tahoma", Font.BOLD, 17)); //$NON-NLS-1$
		lblState.setForeground(Color.WHITE);
		panel.add(lblState);
		
		tFStatus = new JTextField();
		tFStatus.setDisabledTextColor(Color.BLACK);
		tFStatus.setEnabled(false);
		tFStatus.setFont(new Font("Tahoma", Font.PLAIN, 17)); //$NON-NLS-1$
		tFStatus.setBounds(602, 87, 138, 35);
		panel.add(tFStatus);
		tFStatus.setColumns(10);
		
		final JButton btnReady = new JButton();
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer flag = 0;
				Integer day1= null;
				Integer day2= null;
				Integer month1= null;
				Integer month2= null;
				Integer year1= null;
				Integer year2 = null;
				//flag=1 corresponde a que no hay error
				//flag=2 error en la introduccion de las fechas
				//flag=3 no introdujo una ciudad de origen
				//flag=4 no introdujo una ciudad de finalizacion
				//flag=5 no introdujo un costo estimado del viaje
				//flag=6 no introdujo un costo estimado del viaje correcto
				//flag=7 no introdujo una descripcion del viaje
				try{
					day1 = Integer.parseInt(tFLeaving.getText().substring(0, 2));
					day2 = Integer.parseInt(tFArriving.getText().substring(0, 2));
					month1 = Integer.parseInt(tFLeaving.getText().substring(3, 5));
					month2 = Integer.parseInt(tFArriving.getText().substring(3, 5));
					year1 = Integer.parseInt(tFLeaving.getText().substring(6, 8));
					year2 = Integer.parseInt(tFArriving.getText().substring(6, 8));
					
					if(day1 > day2 && month1 == month2 & year1 == year2 || month1 > month2 && year1 == year2 || year1 > year2){	
						flag = 2;
					}else if(tFFrom.getText().isEmpty()){
						flag = 3;
					} else if(tFTo.getText().isEmpty()){
						flag = 4;
					}else if(tFCost.getText().isEmpty()){
						flag = 5;
					}else if(!isNumeric(tFCost.getText())){
						flag = 6;
					}else if(textArea.getText().isEmpty()){
						flag = 7;
					}else{
						flag = 1;
					}
					
				}catch (Exception e1){
					System.err.println("No introdujo fechas"); //$NON-NLS-1$
				}

				if(instance != null){
					switch(flag){
						case 1:
							Date dateL = new Date(year1, month1, day1);
							Date dateA = new Date(year2, month2, day2);
							if(choice == 0){
								MyTripController viaje = null;
								
								try {
									viaje = instance.registerTrip(dateL, dateA, Double.parseDouble(tFCost.getText()), textArea.getText(), tFTo.getText(), tFFrom.getText());
								} catch (ServerException e1) {
									e1.printStackTrace();
								} catch (NumberFormatException e1) {
									e1.printStackTrace();
								} catch (UserNameAlreadyExistsException e1) {
									e1.printStackTrace();
								} catch (SessionNotActiveException e1) {
									e1.printStackTrace();
								}
								Group frame = new Group(0,viaje, null ,auxText, instance, session, groupController,language);
								frame.setVisible(true);
								frame.pack();
								frame.setSize(900, 602);
								close();
							}else if(choice == 1){
								try {
									myTrip.setEndCity(tFTo.getText());
									myTrip.setOriginCity(tFFrom.getText());
									myTrip.setStartDate(dateL);
									myTrip.setEndDate(dateA);
									myTrip.setEstimateCost(Double.parseDouble(tFCost.getText()));
									myTrip.setTripDescription(textArea.getText());
								} catch (SessionNotActiveException e1) {
									e1.printStackTrace();
								} catch (ControllerNotLoadedException e1) {
									e1.printStackTrace();
								}
								Group frame = new Group(1,myTrip,null, null, instance, session, groupController,language);
								frame.setVisible(true);
								frame.pack();
								frame.setSize(900, 602);
								close();
							}else if(choice == 2){
								Group frame = new Group(2 ,null, trip ,null, instance, session, groupController,language);
								frame.setVisible(true);
								frame.pack();
								frame.setSize(900, 602);
								close();
							}
							break;
						case 2:
							JOptionPane.showMessageDialog(null, messages.getString("Trip.8"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
							break;
						case 3:
							JOptionPane.showMessageDialog(null, messages.getString("Trip.10"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
							break;
						case 4:
							JOptionPane.showMessageDialog(null, messages.getString("Trip.12"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
							break;
						case 5:
							JOptionPane.showMessageDialog(null, messages.getString("Trip.14"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
							break;
						case 6: 
							JOptionPane.showMessageDialog(null, messages.getString("Trip.16"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
							break;
						case 7:
							JOptionPane.showMessageDialog(null, messages.getString("Trip.18"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
							break;
						default:
							JOptionPane.showMessageDialog(null, messages.getString("Trip.20"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
							break;
					}
				}
			}
		});
		btnReady.setBounds(651, 544, 89, 23);
		panel.add(btnReady);
		
		final JLabel lblDescription = new JLabel();
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 17)); //$NON-NLS-1$
		lblDescription.setBounds(25, 379, 246, 35);
		panel.add(lblDescription);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(245, 369, 472, 119);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 13)); //$NON-NLS-1$
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		lblFrom.setForeground(Color.WHITE);
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 17)); //$NON-NLS-1$
		panel.add(lblFrom);
		
		tFFrom = new JTextField();
		tFFrom.setBounds(253, 222, 134, 20);
		panel.add(tFFrom);
		tFFrom.setColumns(10);
		
		lblTo.setForeground(Color.WHITE);
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 17)); //$NON-NLS-1$
		panel.add(lblTo);
		
		final JLabel lbltrip = new JLabel();
		lbltrip.setForeground(Color.WHITE);
		lbltrip.setFont(new Font("Tahoma", Font.BOLD, 17)); //$NON-NLS-1$
		lbltrip.setBounds(79, 298, 106, 35);
		panel.add(lbltrip);
		
		tFTo = new JTextField();
		tFTo.setBounds(253, 293, 134, 20);
		panel.add(tFTo);
		tFTo.setColumns(10);

		final JLabel label = new JLabel();
		label.setFont(new Font("Tahoma", Font.BOLD, 17)); //$NON-NLS-1$
		label.setForeground(Color.WHITE);
		label.setBounds(224, 283, 25, 35);
		panel.add(label);
		
		final JLabel lblCost = new JLabel();
		lblCost.setForeground(Color.WHITE);
		lblCost.setFont(new Font("Tahoma", Font.BOLD, 17)); //$NON-NLS-1$
		lblCost.setBounds(452, 253, 180, 35);
		panel.add(lblCost);
		
		tFCost = new JTextField();
		tFCost.setBounds(640, 263, 86, 20);
		panel.add(tFCost);
		tFCost.setColumns(10);
		
		if(choice == 0 || choice == 1){
			tFFrom.setEditable(true);
			tFTo.setEditable(true);
			tFCost.setEditable(true);
			textArea.setEditable(true);
		}else if( choice == 2 ){
			tFFrom.setEditable(false);
			tFTo.setEditable(false);
			tFCost.setEditable(false);
			textArea.setEditable(false);
		}
		
		if((trip != null || myTrip != null) && instance != null){
			try {
				tFFrom.setText(trip.getOriginCity());
				tFTo.setText(trip.getEndCity());
				tFLeaving.setText(trip.getStartDate().toString());
				tFArriving.setText(trip.getStartDate().toString());
				tFCost.setText(" $ " + trip.getEstimateCost().toString()); //$NON-NLS-1$
				tFStatus.setText(trip.getTripStatus().getName());
				textArea.setText(trip.getTripDescription());
			} catch (SessionNotActiveException e1) {
				e1.printStackTrace();
			} catch (ControllerNotLoadedException e1) {
				e1.printStackTrace();
			}
		}
		
		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Trip frame = new Trip(choice,trip,myTrip,auxText, instance, session, groupController,false);
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
				Trip frame = new Trip(choice,trip,myTrip,auxText, instance, session, groupController,true);
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
		
		
		lblCost.setText(messages.getString("Trip.32")); //$NON-NLS-1$
		lblTo.setText(messages.getString("Trip.33")); //$NON-NLS-1$
		lblFrom.setText(messages.getString("Trip.34")); //$NON-NLS-1$
		lblDescription.setText(messages.getString("Trip.35")); //$NON-NLS-1$
		btnReady.setText(messages.getString("Trip.36")); //$NON-NLS-1$
		lblState.setText(messages.getString("Trip.37")); //$NON-NLS-1$
		button.setText(messages.getString("Trip.38")); //$NON-NLS-1$
		btnNewButton.setText(messages.getString("Trip.39")); //$NON-NLS-1$
		lblTrip.setText(messages.getString("Trip.40")); //$NON-NLS-1$
		lbltrip.setText(messages.getString("Trip.41")); //$NON-NLS-1$
		label.setText(messages.getString("Trip.42")); //$NON-NLS-1$
		
		
		
	}
	private Locale getLocale (String loc){
		if(loc != null && loc.length() > 0){
			return new Locale(loc);
		}else{
			return Locale.UK;
		}
	}
	public static boolean isNumeric(String str){  
	    try {  
	      Double.parseDouble(str);  
	    }  
	    catch(NumberFormatException nfe){  
	      return false;  
	    }  
	    return true;  
	}
	 
	 public void close(){

			WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
			Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

			}
}

