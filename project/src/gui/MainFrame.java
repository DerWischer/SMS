package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import common.Invoice;
import common.SubscriberManager;
import Subscriber.Subscriber;
import sun.security.krb5.internal.PAEncTSEnc;

public class MainFrame {

	protected JFrame frame;
	protected JPanel panelOuter;
	protected JPanel panelOuterCenter;
	protected JPanel panelOuterNorth;
	protected JPanel panelListInner;
	protected JPanel panelListOuter;
	protected JPanel panelListOuterSouth;
	protected JPanel panelDetail;
	protected JPanel panelDetailOuter;
	protected JPanel panelDetailBottom;
	protected JButton buttonAdd;
	protected JButton buttonRemove;
	protected JButton buttonDays;
	protected JButton buttonSession;
	protected JTextField textfieldDays;
	protected JLabel labelDays;
	
	protected Subscriber current;
	protected SubscriberManager manager;
	
	public MainFrame() {
		manager = new SubscriberManager();
		
		frame = new JFrame("Main Frame");
		panelOuter = new JPanel(new BorderLayout());
		panelOuterCenter = new JPanel(new FlowLayout());
		panelOuterNorth = new JPanel(new FlowLayout());
		panelListOuter = new JPanel(new BorderLayout());
		panelListOuterSouth = new JPanel(new FlowLayout());
		panelListInner = new JPanel();
		panelListInner.setLayout(new BoxLayout(panelListInner, BoxLayout.Y_AXIS));
		panelDetailOuter = new JPanel(new BorderLayout());
		panelDetailBottom = new JPanel();
		panelDetail = new JPanel();
		panelDetail.setLayout(new BoxLayout(panelDetail, BoxLayout.Y_AXIS));
		textfieldDays = new JTextField("Days");
		labelDays = new JLabel("01-01-2016");
		
		initialiseButtons();
		putComponentsTogether();		
		repaintList();
		
		frame.pack();
		int height = frame.getHeight() * 2;
		int width = frame.getWidth() * 2;
		frame.setSize(width, height);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height	/ 2 - frame.getSize().height / 2);
		frame.setVisible(true);
	}
	
	private void initialiseButtons() {
		buttonAdd = new JButton("Add Subscriber");
		buttonRemove = new JButton("Remove Subscriber");
		buttonSession = new JButton("Simulate Session");
		buttonDays = new JButton("Simulate Days");
		
		buttonAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddSubscriberFrame(manager);
				repaintList();
			}
		});
		buttonRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (current != null) {
					Invoice invoice = manager.invoiceAndRemoveSubscriber(current);
					new InvoiceFrame(invoice);
					repaintList();
					panelDetail.removeAll();
				}
				else {
					new WarningFrame("Please choose a subscriber you want to remove.");
				}
				
			}
		});
		buttonSession.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (current != null) {
					new SimulateSessionFrame(current, manager);
				}
				else {
					new WarningFrame("Please choose a subscriber you want to simulate a session for.");
				}
				
		
			}
		});
		buttonDays.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		
			}
		});
	}
	
	private void putComponentsTogether() {
		panelDetailBottom.add(buttonSession);
		panelDetailOuter.add(panelDetail, BorderLayout.CENTER);
		panelDetailOuter.add(panelDetailBottom, BorderLayout.SOUTH);
		
		panelListOuterSouth.add(buttonAdd);
		panelListOuterSouth.add(buttonRemove);
		panelListOuter.add(panelListInner, BorderLayout.CENTER);
		panelListOuter.add(panelListOuterSouth, BorderLayout.SOUTH);
		
		panelOuterNorth.add(textfieldDays);
		panelOuterNorth.add(buttonDays);
		panelOuterNorth.add(labelDays);
		
		//panelOuterCenter.add(panelListOuter);
		//panelOuterCenter.add(panelDetail);
		//panelOuter.add(panelOuterCenter, BorderLayout.CENTER);
		panelOuter.add(panelListOuter, BorderLayout.WEST);
		panelOuter.add(panelDetailOuter, BorderLayout.CENTER);
		panelOuter.add(panelOuterNorth, BorderLayout.NORTH);
		
		frame.add(panelOuter);
	}
	
	private void repaintList() {
		panelListInner.removeAll();
		JLabel name;
		Subscriber s;
		for (int i = 0; i < manager.getSubscriberCount(); i++){
			s = manager.getSubscriber(i);
			name = new JLabel("<html> Subscriber Name: "+s.getFullName() +"<br>Subscriber IMSI: "+s.getIMSI() +"</html>");
			name.addMouseListener(new MouseAdapter() {
				@Override
                public void mouseClicked(MouseEvent e) {
                    JLabel source = (JLabel)e.getSource();
                    Component[] coms = panelListInner.getComponents();
                    for (int i = 0; i < coms.length; i+=2) {
                    	if (source.equals((JLabel)coms[i])) {
                    		updateDetails(i);
                    		break;
                    	}
                    }
                }
			});
			panelListInner.add(name);
			name = new JLabel(" ");
			panelListInner.add(name);
		}
		panelListInner.setVisible(false);
		panelListInner.setVisible(true);
	}
	
	protected void updateDetails(int index) {
		panelDetail.removeAll();
		current = manager.getSubscriber(index/2);
		JLabel name = new JLabel("Name: " + current.getFullName());
		JLabel terminal = new JLabel("Terminal: " + current.getTerminalType().toString());
		JLabel subscription = new JLabel("Subscription: "+ current.getSubscriptionType());
		panelDetail.add(name);
		panelDetail.add(new JLabel(" "));
		panelDetail.add(terminal);
		panelDetail.add(new JLabel(" "));
		panelDetail.add(subscription);
		panelDetail.setVisible(false);
		panelDetail.setVisible(true);
	}
	
	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
	}
}
