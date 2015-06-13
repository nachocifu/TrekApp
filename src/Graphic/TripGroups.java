package Graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;

import controllers.Application;
import controllers.GroupController;
import domain.ControllerNotLoadedException;
import domain.Session;
import domain.SessionNotActiveException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TripGroups extends JFrame {

	private static JPanel panel;
	private JTable table;
	private JLabel lblGroups;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TripGroups frame = new TripGroups(null, null);
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
	public TripGroups(final Application instance, final Session session) {
		panel = new ImagePanel(new ImageIcon("TripGroups.jpg").getImage());
		setTitle("TreckApp");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 901, 602);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setLayout(null);
		setContentPane(panel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(68, 146, 751, 272);
		panel.add(scrollPane_1);
		
		//String [] spanish = new String[] {"Desde", "Hasta", "Ciudad de Origen", "Ciudad de Finalizacion"};
		String [] english = new String[] {"Name of the Group","Leaving on", "Returning on", "From","To"};
		
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
	    final Collection<GroupController> trips = new ArrayList<GroupController>();
	    trips = instance.getCurrentProfileController().getGroups();
	    
	    
	 
	    /**/
	    
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					if (arg0.getClickCount() == 2 ) {
						String admin = null;
						if(instance != null){
							try {
								admin = instance.getMyGroupController().getAdmin().getUserName();
							} catch (SessionNotActiveException e) {
								e.printStackTrace();
							} catch (ControllerNotLoadedException e) {
								e.printStackTrace();
							}
							if( trips.get(table.getSelectedRow()) == null ){
								
							}else if(session.getUserName().equals(admin) && instance != null){
								Grupo frame = new Grupo(1,trips.get(table.getSelectedRow()),null, instance, session);
								frame.setVisible(true);
								frame.pack();
								frame.setSize(900, 602);
								close();
							}else{
								Grupo frame = new Grupo(2,trips.get(table.getSelectedRow()),null, instance, session);
								frame.setVisible(true);
								frame.pack();
								frame.setSize(900, 602);
								close();
							}
						}else{
							Grupo frame = new Grupo(2, null, null, null, null);
							frame.setVisible(true);
							frame.pack();
							frame.setSize(900, 602);
							close();
						}
					}
				}catch(IndexOutOfBoundsException e){
					e.printStackTrace();
				}
			}
		});
		table.setEnabled(true);
		table.setCellSelectionEnabled(false);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setRowHeight(20);
		scrollPane_1.setViewportView(table);
		table.setLocale(new Locale("es", "AR"));
		table.setGridColor(Color.WHITE);
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			english
		));
		table.setBorder(UIManager.getBorder("ScrollPane.border"));
		table.setForeground(Color.WHITE);
		table.setBackground(new Color(0, 0, 128));
		table.setToolTipText("");
		
		lblGroups = new JLabel();
		lblGroups.setForeground(Color.WHITE);
		lblGroups.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblGroups.setBounds(330, 42, 310, 46);
		panel.add(lblGroups);
		
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
		btnBack.setText("Volver");
		btnBack.setBounds(726, 518, 93, 23);
		panel.add(btnBack);
		
		/**/
		
		for(int i = 0; i < trips.size(); i++){
			table.setValueAt(((UIManager) trips).get(i)., i, 0);
			table.setValueAt(trips.get(i).getHasta(), i, 1);
			table.setValueAt(trips.get(i).getOrigen(), i, 2);
			table.setValueAt(trips.get(i).getLlegada(), i, 3);
			
		}
		
		for(GroupController each : trips){
			table.setValueAt(((UIManager) trips).get(i)., i, 0);
			table.setValueAt(instance.each..getHasta(), i, 1);
			table.setValueAt(trips.get(i).getOrigen(), i, 2);
			table.setValueAt(trips.get(i).getLlegada(), i, 3);
		}
		
		/**/
		
		/**/
		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//language = 1;
				lblGroups.setText("Grupos a los que pertenece");
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
				lblGroups.setText("Groups where you belong");
			}
		});
		ImageIcon imageE = new ImageIcon("EnglishFlag.jpg"); 
		panel.add(img2);
		img2.setIcon(imageE); 
		img2.setSize(22,18); 
		img2.setLocation(760,11); 
		img2.setVisible(true); 
		
		lblGroups.setText("Grupos a los que pertenece");
		
	}
	
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
}
