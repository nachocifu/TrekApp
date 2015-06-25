package graphic;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import controllers.Application;
import controllers.CurrentProfileController;
import controllers.GroupController;
import controllers.MyGroupController;
import controllers.MyTripController;
import controllers.ProfileController;
import controllers.TripController;
import domain.ControllerNotLoadedException;
import domain.GroupNameAlreadyExistsException;
import domain.InvalidPermissionException;
import domain.RequestStatus;
import controllers.Session;
import domain.SessionNotActiveException;
import domain.UserNameAlreadyExistsException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Choice;


public class Group extends JFrame {

    private static JPanel panel;
    private JTextField tFName;
    private JTextField tFCap;
    private JTextField tFAdmin;
    private JButton btnBack;
    private JTextField tFFAge;
    private JTextField tFFCity;
    private CurrentProfileController currentProfile;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Group frame = new Group(0,null, null,null,null, null,null,true);
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
     * @param choice
     * @param myTrip
     * @param trip
     * @param auxText
     * @param instance
     * @param session
     * @param groupController
     * @param language
     */
    // choice = 0 creando, choice = 1 viendo el propio, choice = 2 viendo el de otro
    public Group(final Integer choice, final MyTripController myTrip, final TripController trip, final ArrayList<String> auxText, final Application instance, final Session session, final GroupController groupController, final boolean language){

        if(instance != null){
            try {
                currentProfile = instance.getCurrentProfileController();
            } catch (SessionNotActiveException e1) {
                e1.printStackTrace();
            }
        }

        Locale currentLocale;
        if(language){
            currentLocale = new Locale("en","US"); //$NON-NLS-1$ //$NON-NLS-2$
        }else{
            currentLocale = new Locale("es","AR"); //$NON-NLS-1$ //$NON-NLS-2$
        }

        final ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);  //$NON-NLS-1$

        setTitle("TrekApp"); //$NON-NLS-1$
        setBounds(0, 0, 902, 602);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel = new ImagePanel(new ImageIcon("Group.jpg").getImage()); //$NON-NLS-1$
        panel.setBackground(new Color(25, 25, 112));
        panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        setContentPane(panel);
        panel.setLayout(null);

        final JLabel lblGroupName = new JLabel();
        lblGroupName.setForeground(Color.BLACK);
        lblGroupName.setFont(new Font("Tahoma", Font.PLAIN, 19)); //$NON-NLS-1$
        lblGroupName.setHorizontalAlignment(SwingConstants.LEFT);
        lblGroupName.setBounds(200, 58, 173, 34);
        panel.add(lblGroupName);

        tFName = new JTextField();
        tFName.setFont(new Font("Tahoma", Font.PLAIN, 17)); //$NON-NLS-1$
        tFName.setDisabledTextColor(new Color(0, 0, 0));
        tFName.setBounds(408, 64, 185, 25);
        panel.add(tFName);
        tFName.setColumns(10);

        final JLabel lblCapacity = new JLabel();
        lblCapacity.setFont(new Font("Tahoma", Font.PLAIN, 19)); //$NON-NLS-1$
        lblCapacity.setForeground(Color.BLACK);
        lblCapacity.setBounds(200, 103, 159, 34);
        panel.add(lblCapacity);

        tFCap = new JTextField();
        tFCap.setForeground(Color.BLACK);
        tFCap.setDisabledTextColor(new Color(0, 0, 0));
        tFCap.setFont(new Font("Tahoma", Font.PLAIN, 17)); //$NON-NLS-1$
        tFCap.setHorizontalAlignment(SwingConstants.CENTER);
        tFCap.setBounds(408, 105, 43, 30);
        panel.add(tFCap);
        tFCap.setColumns(10);

        tFFAge = new JTextField();

        tFFCity = new JTextField();
        final Object[] fields = {
                messages.getString("Group.9"), tFFAge,  //$NON-NLS-1$
                messages.getString("Group.10"), tFFCity //$NON-NLS-1$
        };

        final Object[] options = {messages.getString("Group.0"),messages.getString("Group.1")}; //$NON-NLS-1$ //$NON-NLS-2$

        JButton btnFilters = new JButton();
        btnFilters.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int confirm = JOptionPane.showOptionDialog(null, fields, messages.getString("Group.11"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,options[1]); //$NON-NLS-1$
                if(confirm == JOptionPane.OK_OPTION){
                    while( (!isIntNumeric(tFFAge.getText()) && !tFFAge.getText().isEmpty())){
                        JOptionPane.showMessageDialog(null, messages.getString("Group.12"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
                        confirm = JOptionPane.showOptionDialog(null, fields, messages.getString("Group.11"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,options[1]); //$NON-NLS-1$
                    }
                }
            }
        });
        btnFilters.setBounds(430, 423, 145, 23);
        panel.add(btnFilters);

        final JButton btnTrip = new JButton();
        if(choice == 0){
            btnTrip.setBounds(430, 386, 145, 23);
        }else{
            btnTrip.setBounds(240, 386, 145, 23);
        }

        panel.add(btnTrip);

        final JLabel lblMembers = new JLabel();
        lblMembers.setFont(new Font("Tahoma", Font.PLAIN, 19)); //$NON-NLS-1$
        lblMembers.setForeground(Color.BLACK);
        lblMembers.setHorizontalAlignment(SwingConstants.LEFT);
        lblMembers.setBounds(200, 208, 203, 34);
        panel.add(lblMembers);

        final DefaultListModel members = new DefaultListModel();
        HashSet<ProfileController> auxMembers = new HashSet<>();
        if(instance != null && groupController != null){
            try {
                auxMembers = groupController.getMembers();
                for(ProfileController each : auxMembers){
                    members.addElement(each.getUserName() + " " + each.getSurname() + " - " + each.getUsername()); //$NON-NLS-1$ //$NON-NLS-2$
                }
            } catch (SessionNotActiveException e1) {
                e1.printStackTrace();
            } catch (ControllerNotLoadedException e1) {
                e1.printStackTrace();
            }

        }
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(null);
        scrollPane.setBounds(408, 206, 202, 169);
        panel.add(scrollPane);

        JList<String> list = new JList<String>(members);
        scrollPane.setViewportView(list);
        list.setForeground(Color.BLACK);
        list.setFont(new Font("Tahoma", Font.PLAIN, 15)); //$NON-NLS-1$
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBorder(null);
        list.setValueIsAdjusting(true);
        list.setEnabled(false);

        final Choice requests = new Choice();
        requests.setVisible(false);
        requests.setBounds(233, 457, 200, 30);

        HashMap<ProfileController, RequestStatus> requestsTrip = null;
        if(instance != null && choice == 1){
            try {
                requestsTrip = ((MyGroupController)groupController).getMemberRequests();
                System.out.println(requestsTrip);
                for (ProfileController key : requestsTrip.keySet()) {
                    requests.add(key.getUserName() + " " + key.getSurname() + " - " + key.getUsername()); //$NON-NLS-1$ //$NON-NLS-2$
                }
            } catch (SessionNotActiveException e1) {
                e1.printStackTrace();
            } catch (ControllerNotLoadedException e1) {
                e1.printStackTrace();
            }
        }
        panel.add(requests);

        final JButton btnReject = new JButton();
        final JButton btnAccept = new JButton();

        final HashMap<ProfileController, RequestStatus> requestsTripaux = requestsTrip;
        btnAccept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    ProfileController profile = getKey(requestsTripaux.keySet(),requests.getSelectedIndex());
                    members.addElement(requests.getSelectedItem());
                    ((MyGroupController)groupController).addMember(profile);
                } catch (SessionNotActiveException e) {
                    e.printStackTrace();
                } catch (ControllerNotLoadedException e) {
                    e.printStackTrace();
                } catch (InvalidPermissionException e) {
                    e.printStackTrace();
                }
                requests.remove(requests.getSelectedItem());
                if((requests.getItemCount()) < 1){
                    btnReject.setEnabled(false);
                    btnAccept.setEnabled(false);
                }
            }
        });
        btnAccept.setBounds(476, 457, 123, 20);
        btnAccept.setVisible(false);
        panel.add(btnAccept);
        btnReject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ProfileController profile = getKey(requestsTripaux.keySet(),requests.getSelectedIndex());
                    ((MyGroupController)groupController).rejectAMemberRequest(profile);
                    requestsTripaux.remove(profile);
                } catch (SessionNotActiveException e1) {
                    e1.printStackTrace();
                } catch (ControllerNotLoadedException e1) {
                    e1.printStackTrace();
                }
                requests.remove(requests.getSelectedItem());
                if((requests.getItemCount()) < 1){
                    btnReject.setEnabled(false);
                    btnAccept.setEnabled(false);
                }
            }
        });
        btnReject.setVisible(false);
        btnReject.setBounds(637, 457, 123, 20);
        panel.add(btnReject);

        final JLabel lblNewRequest = new JLabel();
        lblNewRequest.setVisible(false);
        lblNewRequest.setForeground(Color.WHITE);
        lblNewRequest.setFont(new Font("Tahoma", Font.PLAIN, 18)); //$NON-NLS-1$
        lblNewRequest.setBounds(26, 448, 185, 34);
        panel.add(lblNewRequest);

        final JButton btnDelete = new JButton();
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(instance != null && choice == 1){
                    try {
                        ((MyGroupController)groupController).deleteGroup();
                    } catch (SessionNotActiveException e1) {
                        e1.printStackTrace();
                    } catch (ControllerNotLoadedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        btnDelete.setBounds(397, 386, 145, 23);
        panel.add(btnDelete);

        if(requests.countItems() < 1){
            btnAccept.setEnabled(false);
            btnReject.setEnabled(false);
        }

        final JButton btnCalif = new JButton();
        btnCalif.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Calif frame = new Calif(instance, session, groupController, language);
                frame.setVisible(true);
                frame.pack();
                frame.setSize(900, 602);
                close();
            }
        });
        btnCalif.setBounds(69, 257, 250, 23);
        btnCalif.setVisible(false);
        panel.add(btnCalif);


        try {
            if(groupController != null && groupController.getTripStatus().equals(groupController.getTripStatus().CLOSED)){
                btnCalif.setVisible(true);
            }
        } catch (SessionNotActiveException e3) {
            e3.printStackTrace();
        } catch (ControllerNotLoadedException e3) {
            e3.printStackTrace();
        }


        final JButton btnRequestcheck = new JButton();
        btnRequestcheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(choice == 1){
                    lblNewRequest.setVisible(true);
                    btnAccept.setVisible(true);
                    btnReject.setVisible(true);
                    requests.setVisible(true);
                }
            }
        });
        btnRequestcheck.setBounds(552, 386, 150, 23);
        panel.add(btnRequestcheck);

        final JLabel lblAdmin = new JLabel();
        lblAdmin.setForeground(Color.BLACK);
        lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 19)); //$NON-NLS-1$
        lblAdmin.setBounds(200, 148, 200, 34);
        panel.add(lblAdmin);

        tFAdmin = new JTextField();
        tFAdmin.setFont(new Font("Tahoma", Font.PLAIN, 17)); //$NON-NLS-1$
        tFAdmin.setBounds(408, 152, 185, 25);
        panel.add(tFAdmin);
        tFAdmin.setColumns(10);

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
        btnBack.setBounds(26, 530, 150, 23);
        panel.add(btnBack);

        final JButton btnCreatetrip = new JButton();
        btnCreatetrip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(choice == 0){
                    Integer flag = 0;
                    //flag=1 corresponde a que no hay error
                    //flag=2 no introdujo el nombre del grupo
                    //flag=3 no introdujo la capacidad maxima del viaje planeado
                    //flag=4 no se creo un viaje
                    //flag=5 no introdujo una capacidad correcta
                    //flag=6 no introdujo filtros
                    if(tFName.getText().isEmpty()){
                        flag = 2;
                    } else if(tFCap.getText().isEmpty()){
                        flag = 3;
                    }else if(myTrip == null){
                        flag = 4;
                    }else if(!isIntNumeric(tFCap.getText())){
                        flag = 5;
                    }else if (isIntNumeric(tFCap.getText()) && Integer.parseInt(tFCap.getText()) < 1){
                        flag = 5;
                    }else if(tFFAge.getText().trim().isEmpty() || tFFCity.getText().trim().isEmpty()){
                        flag = 6;
                    }else{
                        flag = 1;
                    }
                    switch(flag){
                    case 1:
                        int confirm = JOptionPane.showConfirmDialog(null, messages.getString("Group.22"), messages.getString("Group.23"), JOptionPane.YES_NO_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
                        if(confirm == JOptionPane.YES_OPTION){

                            try {
                                MyGroupController group;
                                group = instance.registerGroup(tFName.getText(), currentProfile, Integer.parseInt(tFCap.getText()), Integer.parseInt(tFFAge.getText()), tFFCity.getText()); //$NON-NLS-1$
                                group.addGroupTrip(myTrip);

                            } catch (SessionNotActiveException e) {
                                e.printStackTrace();
                            } catch (ControllerNotLoadedException e) {
                                e.printStackTrace();
                            } catch (InvalidPermissionException e) {
                                e.printStackTrace();
                            }catch (NumberFormatException e) {
                                e.printStackTrace();
                            }catch (ServerException e) {
                                e.printStackTrace();
                            }catch (IllegalArgumentException e){
                                e.printStackTrace();
                            } catch (GroupNameAlreadyExistsException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            Options frame = new Options(instance, session,language);
                            frame.setVisible(true);
                            frame.pack();
                            frame.setSize(900, 602);
                            close();
                        }
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, messages.getString("Group.25"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, messages.getString("Group.27"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, messages.getString("Group.29"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(null, messages.getString("Group.4"), "ERROR", JOptionPane.ERROR_MESSAGE);  //$NON-NLS-1$ //$NON-NLS-2$
                        break;
                    case 6:
                        JOptionPane.showMessageDialog(null, messages.getString("Group.90"), "ERROR", JOptionPane.ERROR_MESSAGE);  //$NON-NLS-1$ //$NON-NLS-2$
                        break;
                    }
                }
                }
        });
        btnCreatetrip.setBounds(651, 530, 150, 23);
        panel.add(btnCreatetrip);

        JButton btnLeaveGroup = new JButton();
        btnLeaveGroup.setBounds(637, 260, 145, 23);
        panel.add(btnLeaveGroup);

        if( choice == 1 && instance != null){
            tFName.setEditable(false);
            tFCap.setEditable(true);
            tFAdmin.setEditable(false);
            try {
                tFName.setText(groupController.getGroupName());
                tFCap.setText(new Integer((groupController.getMaxGroupSize() - groupController.groupSize())).toString());
                tFAdmin.setText(groupController.getAdmin().getUsername()); //$NON-NLS-1$
            } catch (SessionNotActiveException e2) {
                e2.printStackTrace();
            } catch (ControllerNotLoadedException e2) {
                e2.printStackTrace();
            }
            btnFilters.setVisible(false);
            btnTrip.setText(messages.getString("Group.32")); //$NON-NLS-1$
            btnTrip.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    Trip frame = null;
                    try {
                        frame = new Trip(1, null, (MyTripController)groupController.getTripController() ,null, instance, session, groupController,language);
                    } catch (SessionNotActiveException e) {
                        e.printStackTrace();
                    } catch (ControllerNotLoadedException e) {
                        e.printStackTrace();
                    }
                    frame.setVisible(true);
                    frame.pack();
                    frame.setSize(900, 602);
                    close();
                }
            });
            btnCreatetrip.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    if(isIntNumeric(tFCap.getText())){
                        if(Integer.parseInt(tFCap.getText()) < 1){
                            JOptionPane.showMessageDialog(null, messages.getString("Group.4"), "ERROR", JOptionPane.ERROR_MESSAGE);  //$NON-NLS-1$ //$NON-NLS-2$
                        }else{
                            try {
                                ((MyGroupController)groupController).changeGroupCapacity(Integer.parseInt(tFCap.getText()));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            } catch (SessionNotActiveException e) {
                                e.printStackTrace();
                            } catch (ControllerNotLoadedException e) {
                                e.printStackTrace();
                            } catch (IllegalArgumentException e){

                            }

                            Options frame = new Options(instance, session,true);
                            frame.setVisible(true);
                            frame.pack();
                            frame.setSize(900, 602);
                            close();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, messages.getString("Group.4"), "ERROR", JOptionPane.ERROR_MESSAGE);  //$NON-NLS-1$ //$NON-NLS-2$
                    }


                }
            });

            btnLeaveGroup.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    if(instance != null){
                        try {
                            currentProfile.leaveGroup(groupController);
                            Options frame = new Options(instance, session,language);
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
                }
            });
            btnRequestcheck.setText(messages.getString("Group.33")); //$NON-NLS-1$
            btnDelete.setVisible(true);
        }else if(choice == 2 && instance != null){
            tFName.setEditable(false);
            tFCap.setEditable(false);
            tFAdmin.setEditable(false);
            btnCreatetrip.setVisible(false);
            try {
                tFName.setText(groupController.getGroupName());
                tFCap.setText(groupController.groupSize().toString());
                tFAdmin.setText(groupController.getAdmin().getUsername()); //$NON-NLS-1$
            } catch (SessionNotActiveException e2) {
                e2.printStackTrace();
            } catch (ControllerNotLoadedException e2) {
                e2.printStackTrace();
            }
            btnFilters.setVisible(false);
            btnTrip.setText(messages.getString("Group.35")); //$NON-NLS-1$
            btnTrip.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    Trip frame = new Trip(2,trip, null,null, instance, session,groupController,language);
                    frame.setVisible(true);
                    frame.pack();
                    frame.setSize(900, 602);
                    close();
                }
            });
            btnDelete.setVisible(false);
            btnRequestcheck.setText(messages.getString("Group.36")); //$NON-NLS-1$
            btnRequestcheck.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        groupController.sendMemberRequest();
                    } catch (SessionNotActiveException e1) {
                        e1.printStackTrace();
                    } catch (ControllerNotLoadedException e1) {
                        e1.printStackTrace();
                    } catch (InvalidPermissionException e1) {
                        JOptionPane.showMessageDialog(null, messages.getString("Group.7"), "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
                    } catch (IllegalArgumentException e1){
                        JOptionPane.showMessageDialog(null,messages.getString("Group.8") , "ERROR", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
                        btnRequestcheck.setEnabled(false);
                    }
                }
            });


            try {
                if(! groupController.getMembers().contains(currentProfile)){
                    btnLeaveGroup.setVisible(false);
                }else{
                    btnRequestcheck.setVisible(false);
                }
            } catch (SessionNotActiveException e1) {
                e1.printStackTrace();
            } catch (ControllerNotLoadedException e1) {
                e1.printStackTrace();
            }

        }else if(choice == 0){
            if(auxText != null){
                tFName.setText(auxText.get(0));
                tFCap.setText(auxText.get(1));
                tFAdmin.setText(auxText.get(2));
            }
            tFAdmin.setEditable(false);
            if(instance != null){
                try {
                    tFAdmin.setText(currentProfile.getUsername());
                } catch (SessionNotActiveException e1) {
                    e1.printStackTrace();
                } catch (ControllerNotLoadedException e1) {
                    e1.printStackTrace();
                }
            }
            btnLeaveGroup.setVisible(false);
            btnFilters.setVisible(true);
            btnCreatetrip.setVisible(true);
            btnTrip.setText(messages.getString("Group.37")); //$NON-NLS-1$
            btnTrip.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    ArrayList<String> aux = new ArrayList<String>();
                    aux.add(tFName.getText());
                    aux.add(tFCap.getText());
                    aux.add(tFAdmin.getText());
                    if(myTrip != null){
                        Trip frame = new Trip(0,myTrip,null,aux, instance, session, groupController,language);
                        frame.setVisible(true);
                        frame.pack();
                        frame.setSize(900, 602);
                        close();
                    }else{
                        Trip frame = new Trip(0,null,null,aux, instance, session, groupController, language);
                        frame.setVisible(true);
                        frame.pack();
                        frame.setSize(900, 602);
                        close();
                    }
                }
            });
            btnDelete.setVisible(false);
            btnRequestcheck.setVisible(false);
        }

        JButton img = new JButton();
        img.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Group frame = new Group(choice,myTrip, trip,auxText,instance, session,groupController,false);
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
                Group frame = new Group(choice,myTrip, trip,auxText,instance, session,groupController,true);
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

        if( choice == 1){
            btnCreatetrip.setText(messages.getString("Group.2")); //$NON-NLS-1$
        }else if(choice == 0){
            btnCreatetrip.setText(messages.getString("Group.41")); //$NON-NLS-1$
        }

        btnCalif.setText(messages.getString("Group.5")); //$NON-NLS-1$
        btnDelete.setText(messages.getString("Group.40")); //$NON-NLS-1$
        btnBack.setText(messages.getString("Group.42")); //$NON-NLS-1$
        lblAdmin.setText(messages.getString("Group.43")); //$NON-NLS-1$
        btnAccept.setText(messages.getString("Group.44")); //$NON-NLS-1$
        lblNewRequest.setText(messages.getString("Group.45")); //$NON-NLS-1$
        btnReject.setText(messages.getString("Group.46")); //$NON-NLS-1$
        lblMembers.setText(messages.getString("Group.47")); //$NON-NLS-1$
        lblGroupName.setText(messages.getString("Group.48")); //$NON-NLS-1$
        lblCapacity.setText(messages.getString("Group.49")); //$NON-NLS-1$
        btnFilters.setText(messages.getString("Group.50"));	 //$NON-NLS-1$
        btnLeaveGroup.setText(messages.getString("Group.3")); //$NON-NLS-1$

    }

    /**
     * Closes a frame after an event
     */
    public void close(){
        WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }

    /**
     * Checks if the string is a number
     * @param str
     * @return true str is number, false str is not a number
     */
    public static boolean isNumeric(String str){
        try {
          Double.parseDouble(str);
        }
        catch(NumberFormatException nfe){
          return false;
        }
        return true;
    }

    /**
     * Checks if the str is a Integer
     * @param str
     * @return true str is Integer, false str is not a Integer
     */
    public static boolean isIntNumeric(String str){
        try {
          Integer.parseInt(str);
        }
        catch(NumberFormatException nfe){
          return false;
        }
        return true;
    }

    /**
     * Searchs the user selected
     * @param keys
     * @param value
     * @return the ProfileController selected
     */
    private ProfileController getKey(Set<ProfileController> keys, Integer value){
        for(ProfileController each : keys){
            if(value == 0){
                return each;
            }
        }
        return null;
    }
}
