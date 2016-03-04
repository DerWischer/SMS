package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AuthDialog extends JDialog {

	private static final long serialVersionUID = -5472107903390889662L;

	private JLabel lblLogin;	
	private JTextField tfPassword;
	private JButton btnLogin;
	
	private final String PASSWORD = "adm!n";
	private boolean isAuthenticated = false;
	private boolean wasClosed = false;
	
	public AuthDialog(){		
		this.setTitle("Authentification");
		this.setModal(true);
		this.setResizable(false);
		
		int height = 150;
		int width = 250;
		this.setSize(width, height);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height	/ 2 - this.getSize().height / 2);
		
		this.add(createPanel());
		this.pack();
		this.setVisible(true);			
	}
	
	public boolean isAuthenticated(){
		return isAuthenticated;
	}
	
	public boolean wasClosed() {
		return wasClosed;
	}
	
	private JPanel createPanel() {
		lblLogin = new JLabel("Please enter your password");
		tfPassword = new JPasswordField();
		btnLogin = new JButton("Login");		
		
		JPanel pnl = new JPanel(new GridLayout(3, 1));
		pnl.add(lblLogin);		
		pnl.add(tfPassword);
		pnl.add(btnLogin);	
		pnl.setVisible(true);		
		
		tfPassword.addActionListener(action_login);		
		btnLogin.addActionListener(action_login);		
		
		return pnl;
	}	
	
	private ActionListener action_login =new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String text = tfPassword.getText();
			isAuthenticated = text.equals(PASSWORD);		
			tfPassword.setText("");
			
			if (isAuthenticated)
				AuthDialog.this.dispose();
		}
	};  
}
