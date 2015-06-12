package Graphic;

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
import javax.swing.ListModel;
import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.ListSelectionModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.Application;
import domain.Session;


public class OldTrips extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7478825638896727278L;
	private static JPanel panel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OldTrips frame = new OldTrips(null, null);
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
	public OldTrips(final Application instance, final Session session) {
		setTitle("TreckApp");
		setBounds(0, 0, 902, 602);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new ImagePanel(new ImageIcon("OldTrips.jpg").getImage());
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
		list_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		list_1.setBorder(UIManager.getBorder("InternalFrame.border"));
		list_1.setBackground(new Color(0, 0, 128));
		
		final JLabel lblOldTrips = new JLabel();
		lblOldTrips.setForeground(Color.WHITE);
		lblOldTrips.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblOldTrips.setBounds(289, 22, 183, 34);
		panel.add(lblOldTrips);
		
		final JLabel lblFrom = new JLabel();
		lblFrom.setForeground(Color.WHITE);
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFrom.setBounds(291, 192, 180, 35);
		panel.add(lblFrom);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setDisabledTextColor(Color.BLACK);
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setBounds(518, 202, 168, 27);
		panel.add(textField);
		
		final JLabel label_1 = new JLabel();
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(343, 261, 106, 35);
		panel.add(label_1);
		
		final JLabel lblTo = new JLabel();
		lblTo.setForeground(Color.WHITE);
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTo.setBounds(289, 241, 183, 35);
		panel.add(lblTo);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_1.setDisabledTextColor(Color.BLACK);
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(517, 249, 168, 27);
		panel.add(textField_1);
		
		final JLabel lblDescription = new JLabel();
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDescription.setBounds(289, 357, 219, 35);
		panel.add(lblDescription);
		
		final JLabel label_4 = new JLabel();
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 15));
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
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		final JLabel lblLeavingOn = new JLabel();
		lblLeavingOn.setForeground(Color.WHITE);
		lblLeavingOn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLeavingOn.setBounds(289, 108, 205, 27);
		panel.add(lblLeavingOn);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_2.setDisabledTextColor(Color.BLACK);
		textField_2.setEnabled(false);
		textField_2.setBounds(518, 108, 168, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		final JLabel lblReturningOn = new JLabel();
		lblReturningOn.setForeground(Color.WHITE);
		lblReturningOn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReturningOn.setBounds(289, 155, 205, 27);
		panel.add(lblReturningOn);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_3.setDisabledTextColor(Color.BLACK);
		textField_3.setEnabled(false);
		textField_3.setColumns(10);
		textField_3.setBounds(518, 155, 168, 26);
		panel.add(textField_3);
		
		
		/**/
		final LinkedList<Viajeback> prueba = new LinkedList<Viajeback>();
	    Viajeback p1 = new Viajeback("cala", "mu1", "chi", "ta");
	    Viajeback p2 = new Viajeback("cal", "mu2", "chi", "ta");
	    Viajeback p3 = new Viajeback("ca", "mu3", "chi", "ta");
	    Viajeback p4 = new Viajeback("c", "mu4", "chi", "ta");
	    prueba.add(p1);
	    prueba.add(p2);
	    prueba.add(p3);
	    prueba.add(p4);  
		
//		if (session != null){
	    DefaultListModel<String> dim1 = new DefaultListModel<String>();
//			for(Entry<String, Consumable> e : consumiblese.entrySet() ){
//					dim1.addElement(e.getValue().getName()  );
//			}
//			list_1.setModel(dim1);
//		}
	    
	    for(int i = 0; i < prueba.size(); i++){
	    	 dim1.addElement(prueba.get(i).getDesde() + " " + prueba.get(i).getHasta() + " " + prueba.get(i).getOrigen() + " " + prueba.get(i).getLlegada());	
		}
	    list_1.setModel(dim1);
	    list_1.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
				textField_2.setText(prueba.get(list_1.getSelectedIndex()).getDesde());
				textField_3.setText(prueba.get(list_1.getSelectedIndex()).getHasta());	
				textField.setText(prueba.get(list_1.getSelectedIndex()).getOrigen());	
				textField_1.setText(prueba.get(list_1.getSelectedIndex()).getLlegada());	
            }
        });
	    
	    /**/
		
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

		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//language = 1;
				lblOldTrips.setText("Viajes Pasados");
				lblFrom.setText("Ciudad de Origen : ");
				label_1.setText("del Viaje");
				lblTo.setText("Ciudad de Finalizaci\u00F3n");
				lblDescription.setText("Descripcion del Viaje :");
				lblReturningOn.setText("Fecha de Finalizaci\u00F3n :");
				lblLeavingOn.setText("Fecha de Comienzo :");
				btnBack.setText("Volver");
				label_4.setText(":");
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
				lblOldTrips.setText("Old Trips");
				lblFrom.setText("From : ");
				label_1.setText("");
				lblTo.setText("To :");
				lblDescription.setText("Trip Description :");
				lblReturningOn.setText("Returning on :");
				lblLeavingOn.setText("Leaving on :");
				btnBack.setText("Back");
				label_4.setText("");
			}
		});
		ImageIcon imageE = new ImageIcon("EnglishFlag.jpg"); 
		panel.add(img2);
		img2.setIcon(imageE); 
		img2.setSize(22,18); 
		img2.setLocation(760,11); 
		img2.setVisible(true); 
		
		lblOldTrips.setText("Viajes Pasados");
		lblFrom.setText("Ciudad de Origen : ");
		label_1.setText("del Viaje");
		lblTo.setText("Ciudad de Finalizaci\u00F3n");
		lblDescription.setText("Descripcion del Viaje :");
		lblReturningOn.setText("Fecha de Finalizaci\u00F3n :");
		lblLeavingOn.setText("Fecha de Comienzo :");
		btnBack.setText("Volver");
	}
	
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
}
