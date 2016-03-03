package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.*;

import InformationProvider.SessionInformation;
import InformationProvider.Service.ServiceType;

public class SessionFrame {

	protected JFrame frame;
	protected JPanel panelOuter;
	protected JPanel panelCenter;
	protected JPanel panelBottom;
	protected JLabel labelName;
	protected JLabel labelService;
	protected JLabel labelSignal;
	protected JLabel labelSubscription;
	protected JLabel labelTerminal;
	protected JLabel labelTime;
	protected JLabel labelInfo;
	protected JButton buttonOK;

	public SessionFrame(SessionInformation info) {
		frame = new JFrame("Simulate Session");
		panelOuter = new JPanel(new BorderLayout());
		panelCenter = new JPanel();
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelBottom = new JPanel(new FlowLayout());
		
		labelName = new JLabel("Subscriber Name: "+info.getUsername());
		labelService = new JLabel("Service used: "+info.getService());
		labelSignal = new JLabel("Signal available: "+info.getSignal());
		labelSubscription = new JLabel("Subscription booked: "+info.getSubscription());
		labelTerminal = new JLabel("Terminal used: "+info.getTerminal());
		labelTime = new JLabel("Time in Sec: "+info.getTime());
		labelInfo = new JLabel("Additional Information: "+info.getInfo());
		buttonOK = new JButton("OK");
		
		buttonOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();				
			}
		});
		
		panelCenter.add(labelName);
		panelCenter.add(new JLabel(" "));
		panelCenter.add(labelService);
		panelCenter.add(new JLabel(" "));
		panelCenter.add(labelSignal);
		panelCenter.add(new JLabel(" "));
		panelCenter.add(labelSubscription);
		panelCenter.add(new JLabel(" "));
		panelCenter.add(labelTerminal);
		panelCenter.add(new JLabel(" "));
		panelCenter.add(labelTime);
		panelCenter.add(new JLabel(" "));
		panelCenter.add(labelInfo);
		
		panelBottom.add(buttonOK);
		
		panelOuter.add(panelCenter, BorderLayout.CENTER);
		panelOuter.add(panelBottom, BorderLayout.SOUTH);
		
		frame.add(panelOuter);
		frame.pack();
		frame.setSize(frame.getWidth()+20, frame.getHeight());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height	/ 2 - frame.getSize().height / 2);
		frame.setVisible(true);
	}
}
