package system.myBank.app;

import java.awt.Color;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import system.myBank.app.security.PasswordStorage;
import system.myBank.app.security.PasswordStorage.CannotPerformOperationException;
import system.myBank.app.security.PasswordStorage.InvalidHashException;

enum Status {
	VALID_NAME, VALID_PASSWORD, GRANTED, DENIED;

}

public class Administrator extends JFrame implements ActionListener {

	private Status statusName, statusPassword, ok;
	private JLabel logInlab, IDLab, passwordLab, skipLab;
	private JTextField idField;
	private JPasswordField passwordField;
	private JButton okButton;
	private Pattern pattern;
	private Matcher matcher;
	Administrator admin, admin1;
	static Logger logger = Logger.getLogger(Administrator.class);

	private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
	private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16})";
	/*static {
		new DOMConfigurator().doConfigure("log4j2.xml",
				LogManager.getLoggerRepository());
	}*/
	public Administrator(String title) {
		super(title);

		Container c = getContentPane();
		c.setLayout(new GridLayout(4, 2));

		okButton = new JButton("OK");
		okButton.addActionListener(this);

		logInlab = new JLabel("Log In");

		logInlab.setForeground(Color.BLUE);

		IDLab = new JLabel("Login ID:");

		IDLab.setForeground(Color.BLACK);
		idField = new JTextField(20);

		passwordLab = new JLabel("Password:");

		passwordLab.setForeground(Color.BLACK);
		passwordField = new JPasswordField(20);

		skipLab = new JLabel("");

		c.add(logInlab);
		c.add(new JLabel(""));
		c.add(IDLab);
		c.add(idField);
		c.add(passwordLab);
		c.add(passwordField);
		c.add(skipLab);
		c.add(okButton);

		setSize(450, 200);
		setLocation(300, 200);
		setResizable(false);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		String hash;
		if (ae.getSource() == okButton) {
			do {
				String id = idField.getText();
				pattern = Pattern.compile(USERNAME_PATTERN);
				boolean correctName = validate(id);
				if (!correctName) {
					idField.setText("");
					JOptionPane.showMessageDialog(this, "Invalid name\nEnter Valid Name..");
				} else {
					statusName = Status.VALID_NAME;
				}

				String password = String.valueOf(passwordField.getPassword());
				pattern = Pattern.compile(PASSWORD_PATTERN);
				boolean correctPassword = validate(password);

				if (!correctPassword) {
					passwordField.setText("");
					JOptionPane.showMessageDialog(this, "INVALID...");
				} else {
					statusPassword = Status.VALID_PASSWORD;
				}

				if (statusName == Status.VALID_NAME && statusPassword == Status.VALID_PASSWORD) {
					try {
						hash = PasswordStorage.createHash(password);
						System.out.println(hash);
						if (!PasswordStorage.verifyPassword(password, hash)) {
							JOptionPane.showMessageDialog(this, "FAILURE: GOOD PASSWORD NOT ACCEPTED!");
							ok = Status.DENIED;

						} else {
							ok = Status.GRANTED;

						}
					} catch (CannotPerformOperationException | InvalidHashException e) {
						e.printStackTrace();
					}

				}
			} while (ok == Status.GRANTED);

			if (ok == Status.GRANTED) {
				SignIn.accessGranted = true;
				new SignIn(" Signed as an Administrator ");
			} else {
				JOptionPane.showMessageDialog(this, "Wrong user name or password please re enter");
				passwordField.setText("");
				idField.setText(" ");
			}

		}
	}

	public boolean validate(final String username) {

		matcher = pattern.matcher(username);
		return matcher.matches();

	}

	public static void main(String args[]) {
		new Administrator("Administrator");

	}

}
