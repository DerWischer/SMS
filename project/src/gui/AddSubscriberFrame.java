package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import common.SubscriberManager;
import InformationProvider.Terminal.TerminalType;
import Subscriber.Subscriber;
import Subscriber.SubscriberFactory;
import SubscriptionType.*;

public class AddSubscriberFrame extends JDialog {

	protected JPanel panelOuter;
	protected JPanel panelCenter;
	protected JPanel panelBottom;
	protected JComboBox<TerminalType> comboBoxTerminal;
	protected JComboBox<String> comboBoxSubscription;
	protected JTextField textFieldForname;
	protected JTextField textFieldSurname;
	protected JLabel labelTerminal;
	protected JLabel labelSubscription;
	protected JLabel labelForname;
	protected JLabel labelSurname;
	protected JButton buttonAdd;
	protected JButton buttonCancel;

	public AddSubscriberFrame(final SubscriberManager manager) {
		panelOuter = new JPanel(new BorderLayout());
		panelCenter = new JPanel(new GridLayout(4, 2));
		panelBottom = new JPanel(new FlowLayout());
		comboBoxTerminal = new JComboBox<>(TerminalType.values());
		comboBoxSubscription = new JComboBox<>(new String[] { "GreenMobil S",
				"GreenMobil M", "GreenMobil L" });
		textFieldForname = new JTextField("");
		textFieldSurname = new JTextField("");
		labelTerminal = new JLabel("Terminal: ");
		labelSubscription = new JLabel("Subscription: ");
		labelForname = new JLabel("Forname:");
		labelSurname = new JLabel("Surname:");
		buttonAdd = new JButton("Add Subscriber");
		buttonCancel = new JButton("Cancel");

		buttonAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!textFieldForname.getText().equals("") && !textFieldSurname.getText().equals("") && comboBoxTerminal.getSelectedItem() != null && comboBoxSubscription.getSelectedItem() != null) {
					SubscriptionType subscription = null;
					switch (comboBoxSubscription.getSelectedIndex()) {
					case 0:
						subscription = new GreenMobileS();
						break;
					case 1:
						subscription = new GreenMobileM();
						break;
					case 2:
						subscription = new GreenMobileL();
						break;
					}
					Subscriber s = SubscriberFactory.createSubsriber(textFieldForname.getText(), textFieldSurname.getText(),(TerminalType) comboBoxTerminal.getSelectedItem(),	subscription);
					manager.addSubscriber(s);
					AddSubscriberFrame.this.dispose();
				}
				else {
					new WarningFrame("Please enter data into all fields before adding this subscriber.");
				}
			}
		});
		
		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddSubscriberFrame.this.dispose();
			}
		});

		panelCenter.add(labelForname);
		panelCenter.add(textFieldForname);
		panelCenter.add(labelSurname);
		panelCenter.add(textFieldSurname);
		panelCenter.add(labelTerminal);
		panelCenter.add(comboBoxTerminal);
		panelCenter.add(labelSubscription);
		panelCenter.add(comboBoxSubscription);

		panelBottom.add(buttonAdd);
		panelBottom.add(buttonCancel);

		panelOuter.add(panelCenter, BorderLayout.CENTER);
		panelOuter.add(panelBottom, BorderLayout.SOUTH);
		
		this.add(panelOuter);
		this.pack();
		int height = this.getHeight() * 2;
		int width = this.getWidth() * 2;
		this.setSize(width, height);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height	/ 2 - this.getSize().height / 2);
		
		this.setTitle("Add Subscriber");
		this.setModal(true);
		this.setVisible(true);
		
	}
}
