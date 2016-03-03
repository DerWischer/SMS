package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WarningFrame {

	protected JFrame frame;
	protected JPanel auﬂen;
	protected JPanel innen;
	protected JLabel label;
	protected JButton button;
	
	public WarningFrame(String exception) {
		this.frame = new JFrame();
		this.auﬂen = new JPanel(new BorderLayout());
		this.innen = new JPanel(new BorderLayout());
		
		this.label = new JLabel(exception, SwingConstants.CENTER);
		
		this.button = new JButton("Close");
		this.button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
			
		});
		
		this.innen.add(this.button, BorderLayout.EAST);
		this.auﬂen.add(this.innen, BorderLayout.SOUTH);
		this.auﬂen.add(this.label, BorderLayout.CENTER);
		this.frame.add(this.auﬂen);
		
		this.frame.pack();
		int height = this.frame.getHeight() * 2;
		int width = this.frame.getWidth() * 2;
		this.frame.setSize(width, height);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame.setLocation(dim.width / 2 - this.frame.getSize().width / 2, dim.height	/ 2 - this.frame.getSize().height / 2);
		
		this.frame.setVisible(true);
	}
	
}
