package Port_GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class Interface extends JFrame {
    public Hub hub1 = new Hub();
    public Hub hub2 = new Hub();
    public Hub hub3 = new Hub();
    private JPanel panel;
    private String[] countriesList = {"Austria", "Belgium", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland", "France","Germany", "Greece", "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Romania", "Slovenia", "Spain", "Sweden"};
    private ButtonGroup bg;
    private JComboBox countries;
    private JComboBox countries2;
    private JTextField IDfield;
    private JTextField Weightfield;
    private JTextArea Description;
    private JTextField textRemitent;
    private JTextField textReciver;
    private JButton Pile;
    private JButton unPile;
    private JTextField Col_num;
    private JTextField containerNum;
    private JButton showDescription;
    private JTextArea buttShowDesc;
    private JButton numContainers;
    private JRadioButton priority1;
    private JRadioButton priority2;
    private JRadioButton priority3;
    private JTextArea hubPlan;
    private JTextArea hub2t;
    private JTextArea hub3t;
    private JCheckBox customs;
    private JButton examMethod;
    public Interface() {
        panel = new JPanel(); // generate new panel
        JFrame frame = new JFrame(); // generate new frame
        frame.setSize(1100,960); // resolution/size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close when you press "close" on windows
        ImageIcon icon = new ImageIcon("src/Port_GUI/login-logo.png");
        frame.setIconImage(icon.getImage()); //set icon to image
        frame.setTitle("Port Manager"); // top-left title

        frame.add(panel); // adding the panel to the frame

        panel.setLayout(null);

        JLabel IDlabel = new JLabel("ID number"); // generate ID label + coordinates
        IDlabel.setBounds(10,20,80,25);
        panel.add(IDlabel);

        IDfield = new JTextField(20); // generate ID field + coordinates
        IDfield.setBounds(100,20,300,25);
        panel.add(IDfield);

        JLabel Weightlabel = new JLabel("Weight (tons)"); // generate weight label + coordinates
        Weightlabel.setBounds(10,80,80,25);
        panel.add(Weightlabel);

        Weightfield = new JTextField(20); // generate weight field + coordinates
        Weightfield.setBounds(100,80,300,25);
        panel.add(Weightfield);

        JLabel textDescription = new JLabel("Description"); // label for description
        textDescription.setBounds(10, 210,80,25);
        panel.add(textDescription);

        Description = new JTextArea();
        Description.setBounds(100,120,300,250); // area where the description will be
        Description.getDocument();
        panel.add(Description);

        JLabel Remitent = new JLabel("Remitent company"); // Remitent label
        Remitent.setBounds(10,390,80,25);
        panel.add(Remitent);

        textRemitent = new JTextField(); // field for Remitent
        textRemitent.setBounds(100,390,300,25);
        panel.add(textRemitent);

        JLabel Reciver = new JLabel("Reciver company"); // Reciver label
        Reciver.setBounds(10,420,80,25);
        panel.add(Reciver);

        textReciver = new JTextField(); // field for Reciver
        textReciver.setBounds(100,420,300,25);
        panel.add(textReciver);

        Pile = new JButton("Pile"); // button for "pile"
        Pile.setBounds(100,450,150,30);
        Pile.setFocusable(false);
        Pile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container container = new Container();
                try {
                    int id = Integer.parseInt(IDfield.getText());
                    container.setId(id);

                    int weight = Integer.parseInt(Weightfield.getText());
                    if(weight < 0){
                        throw new myException("Weight cannot be negative");
                    }
                    container.setWeight(weight);

                    container.setCountryOfOrigin((String) countries2.getSelectedItem());
                    container.setInspected(customs.isSelected());
                    container.setReciving(textRemitent.getText());
                    container.setSending(textReciver.getText());
                    container.setContentDescription(Description.getText());

                    if (priority1.isSelected()) {
                        container.setPriority(1);
                    } else if (priority2.isSelected()) {
                        container.setPriority(2);
                    } else if (priority3.isSelected()) {
                        container.setPriority(3);
                    }

                    switch (container.getPriority()) {
                        case 1:
                            if (!hub1.priority1Full()) {
                                hub1.stackContainer(container);
                            } else if (!hub2.priority1Full()) {
                                hub2.stackContainer(container);

                            } else if (!hub3.priority1Full()) {
                                hub3.stackContainer(container);
                            } else {
                                JOptionPane.showMessageDialog(null,"All spaces for priority 1 are full");
                            }
                            break;
                        case 2:
                            if (!hub1.priority2Full()) {
                                hub1.stackContainer(container);
                            } else if (!hub2.priority2Full()) {
                                hub2.stackContainer(container);
                            } else if (!hub3.priority2Full()) {
                                hub3.stackContainer(container);
                            } else {
                                JOptionPane.showMessageDialog(null,"All spaces for priority 2 are full");
                            }
                            break;
                        case 3:
                            if (!hub1.isHubFull()) {
                                hub1.stackContainer(container);
                            } else if (!hub2.isHubFull()) {
                                hub2.stackContainer(container);
                            } else if (!hub3.isHubFull()) {
                                hub3.stackContainer(container);
                            } else {
                                JOptionPane.showMessageDialog(null,"All spaces for priority 3 are full");
                            }
                            break;
                    }
                    hubPlan.setText(hub1.displayHub());
                    hub2t.setText(hub2.displayHub());
                    hub3t.setText(hub3.displayHub());
                }
                catch (NumberFormatException integerWhereItSouldNotBe){
                    JOptionPane.showMessageDialog(null,"Only numbers are allowed in the ID and Weight areas");
                }
                catch (Exception exception){
                    JOptionPane.showMessageDialog(null,"Something went wrong");
                }
                catch (myException negNum){
                    JOptionPane.showMessageDialog(null, negNum.getMessage());
                }

            }
        });
        panel.add(Pile);

        unPile = new JButton("Un-pile"); // button for "un-pile"
        unPile.setBounds(100,485,150,30);
        unPile.setFocusable(false);
        unPile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int column = Integer.parseInt(Col_num.getText());
                    int hubNum = Integer.parseInt(JOptionPane.showInputDialog("Enter hub: "));
                    String errorMessage = "NOT VALID";
                    if (hubNum == 0 || hubNum > 3) {
                        JOptionPane.showMessageDialog(panel, errorMessage);
                    }
                    switch (hubNum) {

                        case 1:
                            try {
                                hub1.removeContainer(column);
                                break;
                            }
                            catch (Exception e1){
                                JOptionPane.showMessageDialog(null,"Column "+column+" is not valid in hub "+hubNum);
                                break;
                            }
                        case 2:
                            try {
                                hub2.removeContainer(column);
                                break;
                            }
                            catch (Exception e2){
                                JOptionPane.showMessageDialog(null,"Column "+column+" is not valid in hub "+hubNum);
                                break;
                            }
                        case 3:
                            try {
                                hub3.removeContainer(column);
                                break;
                            }
                            catch (Exception e3){
                                JOptionPane.showMessageDialog(null,"Column "+column+" is not valid in hub "+hubNum);
                                break;
                            }
                    }
                    hubPlan.setText(hub1.displayHub());
                    hub2t.setText(hub2.displayHub());
                    hub3t.setText(hub3.displayHub());
                }
                catch (NumberFormatException numberFormatException){
                    JOptionPane.showMessageDialog(null,"Only numbers are allowed");
                }
            }
        });
        panel.add(unPile);

        Col_num = new JTextField(); // field for the column number
        Col_num.setBounds(260,485,150,30);
        panel.add(Col_num);

        showDescription = new JButton("Show description"); // button for "Show description"
        showDescription.setBounds(100,525,150,30);
        showDescription.setFocusable(false);
        showDescription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter id:\n"));
                String h1 = hub1.displayContainer(id);
                String h2 = hub2.displayContainer(id);
                String h3 = hub3.displayContainer(id);
                String message = "Hub 1: " + h1 + "\nHub 2: " + h2 + "\nHub 3: " + h3;
                buttShowDesc.setText(message);
            }
        });
        panel.add(showDescription);

        buttShowDesc = new JTextArea(); // area for the description
        buttShowDesc.setBounds(260,520,790,60);
        panel.add(buttShowDesc);

        numContainers = new JButton("Number of containers"); // button for "Number of containers from country"
        numContainers.setBounds(100,600,150,30);
        numContainers.setFocusable(false);
        numContainers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count1 = hub1.countContainersFromCountry((String) countries.getSelectedItem());
                int count2 = hub2.countContainersFromCountry((String) countries.getSelectedItem());
                int count3 = hub3.countContainersFromCountry((String) countries.getSelectedItem());
                JOptionPane.showMessageDialog(panel,"Hub 1: "+count1+" Hub2: "+count2+" Hub3: "+count3);
            }
        });
        panel.add(numContainers);


        countries = new JComboBox(countriesList); // selectable list with all the countries
        countries.setBounds(260,600,150,30);
        countries.setFocusable(false);
        panel.add(countries);

        JLabel country = new JLabel("Country"); // Country Label
        country.setBounds(450,20,80,25);
        panel.add(country);

        countries2 = new JComboBox(countriesList); // again the same list with all the countries
        countries2.setBounds(550,20,150,30);
        countries2.setFocusable(false);
        panel.add(countries2);

        JLabel priority = new JLabel("Priority"); // priority label
        priority.setBounds(450,80,80,25);
        panel.add(priority);

        bg = new ButtonGroup(); // Button Group to avoid having problems with the Radio Buttons
        priority1 = new JRadioButton("1"); // radio button "priority 1"
        priority1.setBounds(550,80,50,25);
        priority1.setFocusable(false);
        panel.add(priority1);
        bg.add(priority1); // element added to the button group

        priority2 = new JRadioButton("2"); // radio button "priority 2"
        priority2.setBounds(600,80,50,25);
        priority2.setFocusable(false);
        panel.add(priority2);
        bg.add(priority2); // element added to the button group

        priority3 = new JRadioButton("3"); // radio button "priority 3"
        priority3.setBounds(650,80,50,25);
        priority3.setFocusable(false);
        panel.add(priority3);
        bg.add(priority3); // element added to the button group

        JLabel stateHP = new JLabel("Hub 1:"); // Hub 1 label
        stateHP.setBounds(470,210,80,25);
        panel.add(stateHP);

        hubPlan = new JTextArea(); // Text area for "Hub1" (Display Hub1)
        hubPlan.setBounds(550,140,200,165);
        hubPlan.setEditable(false);
        hubPlan.setText(hub1.displayHub());
        panel.add(hubPlan);

        JLabel hub2Label = new JLabel("Hub 2:"); // Hub 2 Label
        hub2Label.setBounds(470, 400, 80, 25);
        panel.add(hub2Label);

        hub2t = new JTextArea(); //Hub 2 text area (display Hub2)
        hub2t.setBounds(550,340,200,165);
        hub2t.setEditable(false);
        hub2t.setText(hub2.displayHub());
        panel.add(hub2t);

        JLabel hub3Label = new JLabel("Hub 3:");
        hub3Label.setBounds(780, 400, 80, 25);
        panel.add(hub3Label);

        hub3t = new JTextArea(); //Hub 3 text area (Display Hub3)
        hub3t.setBounds(850,340,200,165);
        hub3t.setEditable(false);
        hub3t.setText(hub3.displayHub());
        panel.add(hub3t);

        JLabel customsInspection = new JLabel("Customs inspection"); // Customs inspection label
        customsInspection.setBounds(720,80,200,25);
        panel.add(customsInspection);

        customs = new JCheckBox(); // Customs inspection checkbox
        customs.setBounds(850,80,50,25);
        customs.setFocusable(false);
        panel.add(customs);

        examMethod = new JButton("Weight-Check");
        examMethod.setBounds(100,650,150,30);
        examMethod.setFocusable(false);
        examMethod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int weight = Integer.parseInt(JOptionPane.showInputDialog("Enter weight: "));
                String string1 = hub1.weightCustoms(weight);
                String string2 = hub2.weightCustoms(weight);
                String string3 = hub3.weightCustoms(weight);
                JOptionPane.showMessageDialog(null, "hub1:"+string1+" hub2:"+string2+" hub3:"+string3);
            }
        });
        panel.add(examMethod);

        frame.setVisible(true); // Set the GUI (graphical user interface) visible
    }

}