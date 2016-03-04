package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WarningFrame extends JDialog{

	protected JPanel auﬂen;
	protected JPanel innen;
	protected JLabel label;
	protected JButton button;
	
	public WarningFrame(String exception) {
		this.auﬂen = new JPanel(new BorderLayout());
		this.innen = new JPanel(new BorderLayout());
		
		this.label = new JLabel(exception, SwingConstants.CENTER);
		
		this.button = new JButton("Close");
		this.button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				WarningFrame.this.dispose();
			}
		});
		
		this.innen.add(this.button, BorderLayout.EAST);
		this.auﬂen.add(this.innen, BorderLayout.SOUTH);
		this.auﬂen.add(this.label, BorderLayout.CENTER);
		
		this.add(auﬂen);
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
