package graphic;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.ScrollPaneConstants;

import controllers.Application;
import controllers.GroupController;
import controllers.MyTripController;
import controllers.Session;
import domain.ControllerNotLoadedException;
import domain.SessionNotActiveException;

import java.awt.ComponentOrientation;

public class TripSearch extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2218902648122240426L;
	private DatePicker dp1;
	private DatePicker dp;
	private ObservingTextField textField;
	private ObservingTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private static JPanel panel;
	private JButton btnBack;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
						
					TripSearch frame = new TripSearch(null, null,true);
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
	public TripSearch(final Application instance, final Session session, final boolean language) {	
		final JLabel lblCityend = new JLabel();
		final JLabel lblCityOrigin = new JLabel();
		Locale currentLocale;
		if(language){
			currentLocale = new Locale("en","US");
			lblCityend.setBounds(452, 120, 219, 28);
			lblCityOrigin.setBounds(452, 56, 168, 28);
		}else{
			currentLocale = new Locale("es","AR");
			lblCityend.setBounds(352, 120, 219, 28);
			lblCityOrigin.setBounds(352, 56, 168, 28);
		}
		ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale); 

		panel = new ImagePanel(new ImageIcon("TripSearch.jpg").getImage()); //$NON-NLS-1$
		setTitle("TreckApp"); //$NON-NLS-1$
		setBounds(0, 0, 903, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(panel);
		panel.setEnabled(true);
		panel.setLayout(null);
		setResizable(false);
		
		final JLabel lblSearch = new JLabel();
		lblSearch.setForeground(Color.BLACK);
		lblSearch.setFont(new Font("Dialog", Font.BOLD, 23)); //$NON-NLS-1$
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setBounds(293, 12, 158, 39);
		panel.add(lblSearch);
		
		textField = new ObservingTextField();
		textField.setEnabled(false);
		textField.setDisabledTextColor(Color.BLACK);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15)); //$NON-NLS-1$
		textField.setBounds(172, 57, 106, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		final JButton btnNewButton = new JButton();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String lang = null;
				final Locale locale = getLocale(lang);
				dp = new DatePicker(textField, locale);
				Date selectedDate = dp.parseDate(textField.getText());
				dp.setSelectedDate(selectedDate);
				dp.start(textField);
				
			}
		});
		btnNewButton.setBounds(47, 57, 106, 28);
		panel.add(btnNewButton);
		
		textField_1 = new ObservingTextField();
		textField_1.setDisabledTextColor(Color.BLACK);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15)); //$NON-NLS-1$
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(172, 121, 106, 28);
		panel.add(textField_1);
		
		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String lang = null;
				final Locale locale = getLocale(lang);
				dp1 = new DatePicker(textField_1, locale);
				Date selectedDate = dp1.parseDate(textField_1.getText());
				dp1.setSelectedDate(selectedDate);
				dp1.start(textField_1);
				
			}
		});
		button.setBounds(47, 121, 106, 28);
		panel.add(button);

		lblCityOrigin.setFont(new Font("Tahoma", Font.PLAIN, 17)); //$NON-NLS-1$
		lblCityOrigin.setForeground(Color.BLACK);
		panel.add(lblCityOrigin);
		
		textField_2 = new JTextField();
		textField_2.setBounds(530, 56, 149, 29);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		lblCityend.setForeground(Color.BLACK);
		lblCityend.setFont(new Font("Tahoma", Font.PLAIN, 17)); //$NON-NLS-1$
		panel.add(lblCityend);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(530, 120, 149, 29);
		panel.add(textField_3);
		
		final JLabel lblSearchDescription = new JLabel();
		lblSearchDescription.setFont(new Font("Tahoma", Font.PLAIN, 17)); //$NON-NLS-1$
		lblSearchDescription.setForeground(Color.BLACK);
		lblSearchDescription.setBounds(83, 169, 175, 28);
		panel.add(lblSearchDescription);
		
		JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(252, 160, 339, 69);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 13)); //$NON-NLS-1$
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		final JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setBounds(340, 240, 89, 23);
		panel.add(btnNewButton_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(31, 284, 709, 272);
		panel.add(scrollPane_1);
		
		btnBack = new JButton();
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Options frame = new Options(instance, session,language);
				frame.setVisible(true);
			    frame.pack();
			    frame.setSize(900, 620);
			    close();
			}
		});
		btnBack.setBounds(765, 538, 93, 23);
		panel.add(btnBack);

		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model){
	        /**
			 * 
			 */
			private static final long serialVersionUID = -4125478354676472603L;

			public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };

	    if(instance != null){
	    	ArrayList<GroupController> groupArray = new ArrayList<>(instance.getCurrentProfileController().getGroups());// lo que devuelva el buscador de trips
	    
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try{
						if (arg0.getClickCount() == 2 ) {
							String admin = null;
							if(instance != null){
								try {
									admin = groupArray.get(table.getSelectedRow()).getAdmin().getUserName();
								} catch (SessionNotActiveException e) {
									e.printStackTrace();
								} catch (ControllerNotLoadedException e) {
									e.printStackTrace();
								}
								if( groupArray.get(table.getSelectedRow()) == null ){
									
								}else if(session.getUserName().equals(admin) && instance != null){
									Group frame = new Group(1, (MyTripController)groupArray.get(table.getSelectedRow()).getTripController(), null, null, instance, session, groupArray.get(table.getSelectedRow()), language);
									frame.setVisible(true);
									frame.pack();
									frame.setSize(900, 602);
									close();
								}else{
									Group frame = new Group(2, null, groupArray.get(table.getSelectedRow()).getTripController(), null, instance, session, groupArray.get(table.getSelectedRow()),language);
									frame.setVisible(true);
									frame.pack();
									frame.setSize(900, 602);
									close();
								}
							}
						}
					}catch(IndexOutOfBoundsException e){
						e.printStackTrace();
					} catch (SessionNotActiveException e) {
						e.printStackTrace();
					} catch (ControllerNotLoadedException e) {
						e.printStackTrace();
					}
				}
			});
			
			try {
				int i = 0;
				for(GroupController each : groupArray){
					model.addRow(new Object[] { null, null,null,null,null});
					model.setValueAt(each.getTripController().getStartDate(), i, 0);
					model.setValueAt(each.getTripController().getEndDate(), i, 1);
					model.setValueAt(each.getTripController().getOriginCity(), i, 2);
					model.setValueAt(each.getTripController().getEndCity(), i, 3);
					i++;
			}
			} catch (SessionNotActiveException e1) {
				e1.printStackTrace();
			} catch (ControllerNotLoadedException e1) {
				e1.printStackTrace();
			}
	    }
	    table.getTableHeader().setReorderingAllowed(false);
		table.setEnabled(true);
		table.setCellSelectionEnabled(false);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setRowHeight(20);
		
		table.setLocale(new Locale("es", "AR")); //$NON-NLS-1$ //$NON-NLS-2$
		table.setGridColor(Color.WHITE);
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14)); //$NON-NLS-1$
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
		table.setBorder(UIManager.getBorder("ScrollPane.border")); //$NON-NLS-1$
		table.setForeground(Color.WHITE);
		table.setBackground(new Color(0, 0, 128));
		table.setToolTipText(""); //$NON-NLS-1$
		scrollPane_1.setViewportView(table);
		
		model.addColumn(messages.getString("TripSearch.28"));
	    model.addColumn(messages.getString("TripSearch.29"));
	    model.addColumn( messages.getString("TripSearch.30"));
	    model.addColumn(messages.getString("TripSearch.31"));
		
		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TripSearch frame = new TripSearch(instance, session,false);
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
				TripSearch frame = new TripSearch(instance, session,true);
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
		
		lblSearch.setText(messages.getString("TripSearch.50")); //$NON-NLS-1$
		btnNewButton.setText(messages.getString("TripSearch.51")); //$NON-NLS-1$
		button.setText(messages.getString("TripSearch.52")); //$NON-NLS-1$
		lblCityOrigin.setText(messages.getString("TripSearch.53")); //$NON-NLS-1$
		lblCityend.setText(messages.getString("TripSearch.54")); //$NON-NLS-1$
		lblSearchDescription.setText(messages.getString("TripSearch.55")); //$NON-NLS-1$
		btnNewButton_1.setText(messages.getString("TripSearch.56")); //$NON-NLS-1$
		btnBack.setText(messages.getString("TripSearch.57")); //$NON-NLS-1$
	}
	
	private Locale getLocale (String loc){
		if(loc != null && loc.length() > 0){
			return new Locale(loc);
		}else{
			return Locale.UK;
		}
	}
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

	}
}
