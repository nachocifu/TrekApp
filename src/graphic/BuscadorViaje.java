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
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
import controllers.Session;

import java.awt.ComponentOrientation;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuscadorViaje extends JFrame {

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
						
					BuscadorViaje frame = new BuscadorViaje(null, null);
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
	public BuscadorViaje(final Application instance, final Session session) {
		
		Locale currentLocale = new Locale("en","US");
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
		
		final JLabel lblCityOrigin = new JLabel();
		lblCityOrigin.setFont(new Font("Tahoma", Font.PLAIN, 17)); //$NON-NLS-1$
		lblCityOrigin.setForeground(Color.BLACK);
		lblCityOrigin.setBounds(352, 56, 168, 28);
		panel.add(lblCityOrigin);
		
		textField_2 = new JTextField();
		textField_2.setBounds(530, 56, 149, 29);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		final JLabel lblCityend = new JLabel();
		lblCityend.setForeground(Color.BLACK);
		lblCityend.setFont(new Font("Tahoma", Font.PLAIN, 17)); //$NON-NLS-1$
		lblCityend.setBounds(352, 120, 219, 28);
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
				Options frame = new Options(instance, session);
				frame.setVisible(true);
			    frame.pack();
			    frame.setSize(900, 620);
			    close();
			}
		});
		btnBack.setBounds(765, 538, 93, 23);
		panel.add(btnBack);
		
		//String [] espa�ol = new String[] {"Desde", "Hasta", "Ciudad de Origen", "Ciudad de Finalizacion"};
		//String [] english = new String[] {"Leaving on", "Returning on", "From","To"};
		
		table = new JTable(){
	        /**
			 * 
			 */
			private static final long serialVersionUID = -4125478354676472603L;

			public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
	    
	    /**/
	    
//	    final LinkedList<Viajeback> prueba = new LinkedList<Viajeback>();
//	    Viajeback p1 = new Viajeback("cala", "mu1", "chi", "ta"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
//	    Viajeback p2 = new Viajeback("cal", "mu2", "chi", "ta"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
//	    Viajeback p3 = new Viajeback("ca", "mu3", "chi", "ta"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
//	    Viajeback p4 = new Viajeback("c", "mu4", "chi", "ta"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
//	    prueba.add(p1);
//	    prueba.add(p2);
//	    prueba.add(p3);
//	    prueba.add(p4);
	 
	    /**/
	    
	//	table.addMouseListener(new MouseAdapter() {
//			@Override
		//	public void mouseClicked(MouseEvent arg0) {
				//if (arg0.getClickCount() == 2 && prueba.get(table.getSelectedRow()) != null) {
//					if(instance.getCurrentProfileController().){
//					Grupo frame = new Grupo(1,prueba.get(table.getSelectedRow()),null);
//					frame.setVisible(true);
//					frame.pack();
//					frame.setSize(900, 602);
//					close();
//				}else{
//					Grupo frame = new Grupo(2,prueba.get(table.getSelectedRow()),null);
//					frame.setVisible(true);
//					frame.pack();
//					frame.setSize(900, 602);
//					close();
//				}
					/* ELIMINAR LO QUE SIGUE DESPUES*/
					//Grupo frame = new Grupo(0,prueba.get(table.getSelectedRow()), null, instance, session);
//					frame.setVisible(true);
//				    frame.pack();
//				    frame.setSize(900, 602);
		//		    close();
				    /*HASTA ACA*/
//				  }
	//		}
		//});
		table.setEnabled(true);
		table.setCellSelectionEnabled(false);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setRowHeight(20);
		scrollPane_1.setViewportView(table);
		table.setLocale(new Locale("es", "AR")); //$NON-NLS-1$ //$NON-NLS-2$
		table.setGridColor(Color.WHITE);
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14)); //$NON-NLS-1$
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			 new String[] {messages.getString("BuscadorViaje.28"), messages.getString("BuscadorViaje.29"), messages.getString("BuscadorViaje.30"), messages.getString("BuscadorViaje.31")} //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		));
		table.setBorder(UIManager.getBorder("ScrollPane.border")); //$NON-NLS-1$
		table.setForeground(Color.WHITE);
		table.setBackground(new Color(0, 0, 128));
		table.setToolTipText(""); //$NON-NLS-1$
		
		/**/
		
//		for(int i = 0; i < prueba.size(); i++){
//			table.setValueAt(prueba.get(i).getDesde(), i, 0);
//			table.setValueAt(prueba.get(i).getHasta(), i, 1);
//			table.setValueAt(prueba.get(i).getOrigen(), i, 2);
//			table.setValueAt(prueba.get(i).getLlegada(), i, 3);	
//		}
		
		/**/
		
		/**/
		DefaultListModel<String> hola2 = new DefaultListModel<String>();
		
		LinkedList<String> hola = new LinkedList<String>();
		hola.add("a"); //$NON-NLS-1$
		hola.add("b"); //$NON-NLS-1$
		hola.add("c"); //$NON-NLS-1$
		hola.add("d"); //$NON-NLS-1$
		hola.add("e"); //$NON-NLS-1$
		hola.add("g"); //$NON-NLS-1$
		hola.add("f"); //$NON-NLS-1$
		hola.add("a"); //$NON-NLS-1$
		hola.add("b"); //$NON-NLS-1$
		hola.add("c"); //$NON-NLS-1$
		hola.add("d"); //$NON-NLS-1$
		hola.add("e"); //$NON-NLS-1$
		hola.add("g"); //$NON-NLS-1$
		hola.add("f"); //$NON-NLS-1$
		for(String each : hola){
			hola2.addElement(each);
		}
		
		/**/
		
		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//language = 1;
//				lblSearch.setText("Buscador");
//				btnNewButton.setText("Desde");
//				button.setText("Hasta");
//				lblCityOrigin.setText("Ciudad de Origen :");
//				lblCityend.setText("Ciudad de Finalizacion :");
//				lblSearchDescription.setText("Buscar Descripcion :");
//				btnNewButton_1.setText("Buscar");
//				btnBack.setText("Volver");
				lblCityend.setBounds(352, 120, 219, 28);
				lblCityOrigin.setBounds(352, 56, 168, 28);
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
				//language = 2;
//				lblSearch.setText("Searcher");
//				btnNewButton.setText("Leaving on");
//				button.setText("Returning on");
//				lblCityOrigin.setText("From :");
//				lblCityend.setText("To :");
//				lblSearchDescription.setText("Search Description :");
//				btnNewButton_1.setText("Search");
//				btnBack.setText("Back");
				lblCityend.setBounds(452, 120, 219, 28);
				lblCityOrigin.setBounds(452, 56, 168, 28);
			}
		});

		ImageIcon imageE = new ImageIcon("EnglishFlag.jpg");  //$NON-NLS-1$
		panel.add(img2);
		img2.setIcon(imageE); 
		img2.setSize(22,18); 
		img2.setLocation(760,11); 
		img2.setVisible(true); 
		
		lblSearch.setText(messages.getString("BuscadorViaje.50")); //$NON-NLS-1$
		btnNewButton.setText(messages.getString("BuscadorViaje.51")); //$NON-NLS-1$
		button.setText(messages.getString("BuscadorViaje.52")); //$NON-NLS-1$
		lblCityOrigin.setText(messages.getString("BuscadorViaje.53")); //$NON-NLS-1$
		lblCityend.setText(messages.getString("BuscadorViaje.54")); //$NON-NLS-1$
		lblSearchDescription.setText(messages.getString("BuscadorViaje.55")); //$NON-NLS-1$
		btnNewButton_1.setText(messages.getString("BuscadorViaje.56")); //$NON-NLS-1$
		btnBack.setText(messages.getString("BuscadorViaje.57")); //$NON-NLS-1$
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
