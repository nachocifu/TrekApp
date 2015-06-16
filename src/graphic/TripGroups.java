package graphic;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;

import controllers.Application;
import controllers.GroupController;
import controllers.MyTripController;
import domain.ControllerNotLoadedException;
import controllers.Session;
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
					TripGroups frame = new TripGroups(null, null,true);
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
	 * @throws ControllerNotLoadedException 
	 * @throws SessionNotActiveException 
	 */
	public TripGroups(final Application instance, final Session session, final boolean language) throws SessionNotActiveException, ControllerNotLoadedException {
		
		Locale currentLocale;
		if(language){
			currentLocale = new Locale("en","US"); 
		}else{
			currentLocale = new Locale("es","AR");
		}
		ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale); 
		
		panel = new ImagePanel(new ImageIcon("TripGroups.jpg").getImage()); //$NON-NLS-1$
		setTitle("TreckApp"); //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 901, 602);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setLayout(null);
		setContentPane(panel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(68, 146, 751, 272);
		panel.add(scrollPane_1);
		
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
	    	ArrayList<GroupController> groupArray = new ArrayList<>(instance.getCurrentProfileController().getGroups());
	    
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
					model.setValueAt(each.getGroupName(), i, 0);
					model.setValueAt(each.getTripController().getStartDate(), i, 1);
					model.setValueAt(each.getTripController().getEndDate(), i, 2);
					model.setValueAt(each.getTripController().getOriginCity(), i, 3);
					model.setValueAt(each.getTripController().getEndCity(), i, 4);
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
		scrollPane_1.setViewportView(table);
		table.setGridColor(Color.WHITE);
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14)); //$NON-NLS-1$
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//			},
//			 new String[] {messages.getString("TripGroups.5"),messages.getString("TripGroups.6"), messages.getString("TripGroups.7"), messages.getString("TripGroups.8"),messages.getString("TripGroups.9")} //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
//		));
		model.addColumn(messages.getString("TripGroups.5"));
	    model.addColumn(messages.getString("TripGroups.6"));
	    model.addColumn( messages.getString("TripGroups.7"));
	    model.addColumn(messages.getString("TripGroups.8"));
	    model.addColumn(messages.getString("TripGroups.9"));
	    
		table.setBorder(UIManager.getBorder("ScrollPane.border")); //$NON-NLS-1$
		table.setForeground(Color.WHITE);
		table.setBackground(new Color(0, 0, 128));
		table.setToolTipText(""); //$NON-NLS-1$
		
		lblGroups = new JLabel();
		lblGroups.setForeground(Color.WHITE);
		lblGroups.setFont(new Font("Tahoma", Font.PLAIN, 22)); //$NON-NLS-1$
		lblGroups.setBounds(330, 42, 310, 46);
		panel.add(lblGroups);
		
		btnBack = new JButton();
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile frame = new Profile(instance, 1, session, language);
				frame.setVisible(true);
			    frame.pack();
			    frame.setSize(900, 620);
			    close();
			}
		});
		btnBack.setText(messages.getString("TripGroups.13")); //$NON-NLS-1$
		btnBack.setBounds(726, 518, 93, 23);
		panel.add(btnBack);
		
		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TripGroups frame;
				try {
					frame = new TripGroups(instance, session,false);
					frame.setVisible(true);
				    frame.pack();
				    frame.setSize(900, 602);
				    close();
				} catch (SessionNotActiveException e) {
					e.printStackTrace();
				} catch (ControllerNotLoadedException e) {
					e.printStackTrace();
				}
				
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
				TripGroups frame;
				try {
					frame = new TripGroups(instance, session,true);
					frame.setVisible(true);
				    frame.pack();
				    frame.setSize(900, 602);
				    close();
				} catch (SessionNotActiveException e1) {
					e1.printStackTrace();
				} catch (ControllerNotLoadedException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		ImageIcon imageE = new ImageIcon("EnglishFlag.jpg");  //$NON-NLS-1$
		panel.add(img2);
		img2.setIcon(imageE); 
		img2.setSize(22,18); 
		img2.setLocation(760,11); 
		img2.setVisible(true); 
		
		lblGroups.setText(messages.getString("TripGroups.16")); //$NON-NLS-1$
		
	}
	
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
}
