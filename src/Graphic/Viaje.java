package Graphic;

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
import java.util.ArrayList;
import java.util.Locale;
import java.util.Date;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JScrollPane;


public class Viaje extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8548069016778574983L;
	private static JPanel panel;
	private ObservingTextField textField;
	private ObservingTextField textField_1;
	private JTextField textField_2;
	private DatePicker dp1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private Viajeback prueba;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Viaje frame = new Viaje(1,null,null);
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
	public Viaje(final Integer i, final Viajeback prueba, final ArrayList<String> aux) {
		this.prueba = prueba;
		panel = new ImagePanel(new ImageIcon("Trip.jpg").getImage());
		setContentPane(panel);
		setTitle("TreckApp");
		setBounds(0, 0, 766, 616);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		panel.setEnabled(true);
		panel.setLayout(null);
		
		final JButton btnNewButton = new JButton();
		
		textField = new ObservingTextField();
		textField.setEnabled(false);
		textField.setDisabledTextColor(Color.BLACK);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(176, 77, 106, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i == 1 || i == 0){					
					String lang = null;
					final Locale locale = getLocale(lang);
					DatePicker dp = new DatePicker(textField, locale);
					Date selectedDate = dp.parseDate(textField.getText());
					dp.setSelectedDate(selectedDate);
					dp.start(textField);
				}else{
					btnNewButton.setEnabled(false);
				}
			}
		});
		
		btnNewButton.setBounds(36, 77, 121, 28);
		panel.add(btnNewButton);
		
		final JLabel lblTrip = new JLabel();
		lblTrip.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrip.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblTrip.setForeground(Color.WHITE);
		lblTrip.setBounds(390, 20, 92, 35);
		panel.add(lblTrip);
		
		textField_1 = new ObservingTextField();
		textField_1.setDisabledTextColor(Color.BLACK);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(176, 123, 106, 28);
		panel.add(textField_1);
		
		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i == 1 || i == 0){
					String lang = null;
					final Locale locale = getLocale(lang);
					dp1 = new DatePicker(textField_1, locale);
					Date selectedDate = dp1.parseDate(textField_1.getText());
					dp1.setSelectedDate(selectedDate);
					dp1.start(textField_1);
				}else{
					button.setEnabled(false);
				}
				
			}
		});
		button.setBounds(36, 123, 121, 28);
		panel.add(button);
		
		final JLabel lblState = new JLabel();
		lblState.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblState.setForeground(Color.WHITE);
		lblState.setBounds(412, 87, 180, 35);
		panel.add(lblState);
		
		textField_2 = new JTextField();
		textField_2.setDisabledTextColor(Color.BLACK);
		textField_2.setEnabled(false);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_2.setBounds(602, 87, 138, 35);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
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
					day1 = Integer.parseInt(textField.getText().substring(0, 2));
					day2 = Integer.parseInt(textField_1.getText().substring(0, 2));
					month1 = Integer.parseInt(textField.getText().substring(3, 5));
					month2 = Integer.parseInt(textField_1.getText().substring(3, 5));
					year1 = Integer.parseInt(textField.getText().substring(6, 8));
					year2 = Integer.parseInt(textField_1.getText().substring(6, 8));
					
					if(day1 > day2 && month1 == month2 & year1 == year2 || month1 > month2 && year1 == year2 || year1 > year2){	
						flag = 2;
					}else if(textField_3.getText().isEmpty()){
						flag = 3;
					} else if(textField_4.getText().isEmpty()){
						flag = 4;
					}else if(textField_5.getText().isEmpty()){
						flag = 5;
					}else if(!isNumeric(textField_5.getText())){
						flag = 6;
					}else if(textArea.getText().isEmpty()){
						flag = 7;
					}else{
						flag = 1;
					}
					
				}catch (Exception e1){
					System.err.println("No introdujo fechas");
				}

				
				switch(flag){
					case 1:
						//viaje = new Viaje();
						if(i == 0){
							Grupo frame = new Grupo(0,null/*viaje*/,aux);
							frame.setVisible(true);
							frame.pack();
							frame.setSize(900, 602);
							close();
						}else if(i == 1){
							Grupo frame = new Grupo(1,null/*viaje*/,aux);
							frame.setVisible(true);
							frame.pack();
							frame.setSize(900, 602);
							close();
						}else if(i == 2){
							Grupo frame = new Grupo(2 ,null/*viaje*/,aux);
							frame.setVisible(true);
							frame.pack();
							frame.setSize(900, 602);
							close();
						}
						break;
					case 2:
						JOptionPane.showMessageDialog(new Viaje(i, prueba,aux), "No introdujo una fecha correcta", "ERROR", JOptionPane.ERROR_MESSAGE);
						break;
					case 3:
						JOptionPane.showMessageDialog(new Viaje(i, prueba,aux), "No introdujo una ciudad de origen", "ERROR", JOptionPane.ERROR_MESSAGE);
						break;
					case 4:
						JOptionPane.showMessageDialog(new Viaje(i, prueba,aux), "No introdujo una ciudad de finalizacion", "ERROR", JOptionPane.ERROR_MESSAGE);
						break;
					case 5:
						JOptionPane.showMessageDialog(new Viaje(i, prueba,aux), "No introdujo un costo estimado del viaje", "ERROR", JOptionPane.ERROR_MESSAGE);
						break;
					case 6: 
						JOptionPane.showMessageDialog(new Viaje(i, prueba,aux), "No introdujo un costo estimado del viaje correcto", "ERROR", JOptionPane.ERROR_MESSAGE);
						break;
					case 7:
						JOptionPane.showMessageDialog(new Viaje(i, prueba,aux), "No introdujo una descripcion del viaje que quiere crear", "ERROR", JOptionPane.ERROR_MESSAGE);
						break;
					default:
						JOptionPane.showMessageDialog(new Viaje(i, prueba,aux), "No introdujo datos obligatorios", "ERROR", JOptionPane.ERROR_MESSAGE);
						break;
				}
			}
		});
		btnReady.setBounds(651, 544, 89, 23);
		panel.add(btnReady);
		
		final JLabel lblDescription = new JLabel();
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDescription.setBounds(25, 379, 246, 35);
		panel.add(lblDescription);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(245, 369, 472, 119);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		final JLabel lblFrom = new JLabel();
		lblFrom.setForeground(Color.WHITE);
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblFrom.setBounds(26, 212, 180, 35);
		panel.add(lblFrom);
		
		textField_3 = new JTextField();
		textField_3.setBounds(253, 222, 134, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		final JLabel lblTo = new JLabel();
		lblTo.setForeground(Color.WHITE);
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTo.setBounds(26, 267, 211, 35);
		panel.add(lblTo);
		
		final JLabel lbltrip = new JLabel();
		lbltrip.setForeground(Color.WHITE);
		lbltrip.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbltrip.setBounds(79, 298, 106, 35);
		panel.add(lbltrip);
		
		textField_4 = new JTextField();
		textField_4.setBounds(253, 293, 134, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);

		final JLabel label = new JLabel();
		label.setFont(new Font("Tahoma", Font.BOLD, 17));
		label.setForeground(Color.WHITE);
		label.setBounds(224, 283, 25, 35);
		panel.add(label);
		
		final JLabel lblCost = new JLabel();
		lblCost.setForeground(Color.WHITE);
		lblCost.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblCost.setBounds(452, 253, 180, 35);
		panel.add(lblCost);
		
		textField_5 = new JTextField();
		textField_5.setBounds(640, 263, 86, 20);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		if(i == 1 || i == 0){
			textField_3.setEditable(true);
			textField_4.setEditable(true);
			textField_5.setEditable(true);
			textArea.setEditable(true);
		}else if(i == 2){
			textField_3.setEditable(false);
			textField_4.setEditable(false);
			textField_5.setEditable(false);
			textArea.setEditable(false);
		}
		if(prueba != null){
			textField.setText(prueba.getDesde());
			textField_1.setText(prueba.getHasta());
		}
		
		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//language = 1;
				lblCost.setText("Costo Aproximado :");
				lblTo.setText("Ciudad de Finalizaci\u00F3n");
				lblFrom.setText("Ciudad de Origen : ");
				lblDescription.setText("Descripcion del Viaje : ");
				btnReady.setText("Listo");
				lblState.setText("Estado del Viaje : ");
				button.setText("Hasta");
				btnNewButton.setText("Desde");
				lblTrip.setText("Viaje");
				lbltrip.setText("del Viaje");
				label.setText(":");
				
				lblState.setBounds(412, 87, 180, 35);
				lblFrom.setBounds(26, 212, 180, 35);
				lblTo.setBounds(26, 267, 211, 35);
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
				lblCost.setText("Estimated cost :");
				lblTo.setText("To :");
				lblFrom.setText("From : ");
				lblDescription.setText("Trip Description : ");
				btnReady.setText("Ready");
				lblState.setText("Trip Status : ");
				button.setText("Returning on");
				btnNewButton.setText("Leaving on");
				lblTrip.setText("Trip");
				lbltrip.setText("");
				label.setText("");
				
				lblState.setBounds(450, 87, 180, 35);
				lblFrom.setBounds(170, 212, 180, 35);
				lblTo.setBounds(180, 285, 211, 35);
			}
		});
		ImageIcon imageE = new ImageIcon("EnglishFlag.jpg"); 
		panel.add(img2);
		img2.setIcon(imageE); 
		img2.setSize(22,18); 
		img2.setLocation(760,11); 
		img2.setVisible(true); 
		
		
		lblCost.setText("Costo Aproximado :");
		lblTo.setText("Ciudad de Finalizaci\u00F3n");
		lblFrom.setText("Ciudad de Origen : ");
		lblDescription.setText("Descripcion del Viaje : ");
		btnReady.setText("Listo");
		lblState.setText("Estado del Viaje : ");
		button.setText("Hasta");
		btnNewButton.setText("Desde");
		lblTrip.setText("Viaje");
		lbltrip.setText("del Viaje");
		label.setText(":");
		
		
		
	}
	private Locale getLocale (String loc){
		if(loc != null && loc.length() > 0){
			return new Locale(loc);
		}else{
			return Locale.UK;
		}
	}
	 public static boolean isNumeric(String str)  
	  {  
	    try  
	    {  
	      Double.parseDouble(str);  
	    }  
	    catch(NumberFormatException nfe)  
	    {  
	      return false;  
	    }  
	    return true;  
	  }
	 
	 public void close(){

			WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
			Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

			}
}

