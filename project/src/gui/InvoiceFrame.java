package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import common.Invoice;

public class InvoiceFrame extends JDialog{

	protected JPanel panelOuter;
	protected JPanel panelCenter;
	protected JPanel panelBottom;
	protected JLabel labelName;
	protected JLabel labelIMSI;
	protected JLabel labelSubscription;
	protected JLabel labelCharges;
	protected JLabel labelExtraMinutes;
	protected JButton buttonOK;
	
	public InvoiceFrame(Invoice invoice) {
		panelOuter = new JPanel(new BorderLayout());
		panelCenter = new JPanel();
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelBottom = new JPanel(new FlowLayout());
		
		labelName = new JLabel("Subscriber Name: "+invoice.getSubscriberName());
		labelIMSI = new JLabel("Subscriber IMSI: "+invoice.getIMSI());
		labelSubscription = new JLabel("Subscription booked: "+invoice.getSubscriptionType());
		labelCharges = new JLabel("Charges applied: "+invoice.getCharges());
		labelExtraMinutes = new JLabel("Extra Minutes used: "+invoice.getUsedExtraMinutes());
		buttonOK = new JButton("OK");
		
		buttonOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InvoiceFrame.this.dispose();				
			}
		});
		
		panelCenter.add(labelName);
		panelCenter.add(new JLabel(" "));
		panelCenter.add(labelIMSI);
		panelCenter.add(new JLabel(" "));
		panelCenter.add(labelSubscription);
		panelCenter.add(new JLabel(" "));
		panelCenter.add(labelExtraMinutes);
		panelCenter.add(new JLabel(" "));
		panelCenter.add(labelCharges);
		
		panelBottom.add(buttonOK);
		
		panelOuter.add(panelCenter, BorderLayout.CENTER);
		panelOuter.add(panelBottom, BorderLayout.SOUTH);
		
		this.add(panelOuter);
		this.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height	/ 2 - this.getSize().height / 2);
		
		this.setTitle("Invoice");
		this.setModal(true);
		this.setVisible(true);
	}
}
