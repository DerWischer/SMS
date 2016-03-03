package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.*;
import common.SubscriberManager;
import InformationProvider.SessionInformation;
import InformationProvider.Service.ServiceType;
import Subscriber.Subscriber;

public class SimulateSessionFrame {

	protected JFrame frame;
	protected JPanel panelOuter;
	protected JPanel panelCenter;
	protected JPanel panelBottom;
	protected JComboBox<ServiceType> comboBoxServices;
	protected JTextField textFieldTime;
	protected JLabel labelService;
	protected JLabel labelTime;
	protected JLabel labelInfo;
	protected JButton buttonSimulate;
	protected JButton buttonCancel;
	
	public SimulateSessionFrame(final Subscriber subscriber, final SubscriberManager manager) {
		frame = new JFrame("Simulate Session");
		panelOuter = new JPanel(new BorderLayout());
		panelCenter = new JPanel(new GridLayout(2, 2));
		panelBottom = new JPanel(new FlowLayout());
		comboBoxServices = new JComboBox<>(ServiceType.values());
		textFieldTime = new JFormattedTextField(NumberFormat.getInstance());
		labelService = new JLabel("Service: ");
		labelTime = new JLabel("Time in Seconds: ");
		labelInfo = new JLabel("You are simulating a session for"+" "+":");
		buttonSimulate = new JButton("Simulate");
		buttonCancel = new JButton("Cancel");
		
		buttonSimulate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxServices.getSelectedItem()!=null && textFieldTime.getText()!="") {
					try {
						int time = Integer.parseInt(textFieldTime.getText());
						SessionInformation info = manager.simulateSession(subscriber, (ServiceType)comboBoxServices.getSelectedItem(), time);
						new SessionFrame(info);
						frame.dispose();
					}
					catch (NumberFormatException exception){
						new WarningFrame("Invalid input for time. Please enter time in seconds.");
					}
				}
			}
		});
		
		buttonCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();				
			}
		});
		
		panelCenter.add(labelService);
		panelCenter.add(comboBoxServices);
		panelCenter.add(labelTime);
		panelCenter.add(textFieldTime);
		
		panelBottom.add(buttonSimulate);
		panelBottom.add(buttonCancel);
		
		panelOuter.add(panelCenter, BorderLayout.CENTER);
		panelOuter.add(panelBottom, BorderLayout.SOUTH);
		
		frame.add(panelOuter);
		
		frame.pack();
		int height = frame.getHeight() * 2;
		int width = frame.getWidth() * 2;
		frame.setSize(width, height);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height	/ 2 - frame.getSize().height / 2);
		frame.setVisible(true);
	}
	
}
