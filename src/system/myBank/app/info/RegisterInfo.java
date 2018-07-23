package system.myBank.app.info;

import java.awt.Color;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Container;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;

import javax.swing.*;

import system.myBank.app.entity.Registration;
import system.myBank.app.entity.Transaction;
import system.myBank.app.entity.TransactionInfo;
import system.myBank.app.security.Regexp;
import system.myBank.app.storage.CustomerDetailStorage;
import system.myBank.app.storage.RegisterOperation;
//import system.myBank.app.storage.AddRegistrDetailStorage;
import system.myBank.app.storage.SearchDetailStorage;
import system.myBank.app.storage.TransactionOperation;

enum RegisterValid {
	INVALID_ENTER;
}

public class RegisterInfo extends JFrame implements ActionListener {
	public static String name;
	ArrayList arrlist;
	public RegisterOperation register = new RegisterOperation();
	public TransactionOperation transaction = new TransactionOperation();
	private JLabel lName, lDob, lGender, lPhoneN, lAdress, lEmail, lAccType, lDepAmo, l12, laccNo, regstr;
	public JTextField tName;
	private JTextField tPhoneNum;
	private JTextField tAddress;
	private JTextField tEmailId;
	private JTextField tDepositAmount;
	private JTextField tAccountNum;
	private JButton bSubmit, bReset, bSearch;
	private JComboBox day, month, year;
	private final int MAX_DAY = 31;
	private final int MAX_MONTH = 12;
	private final int START_YEAR = 1898;
	private Pattern pattern;
	private Matcher matcher;

	private JRadioButton rmale, rfemale, rsaving, rcurrent;
	private int findID;
	RegisterValid validName, validAddress, validPhone, validEmail, validDepositAm, validAccN, withdrawalamount;

	ArrayList<Registration> listRegister;
	ArrayList<Transaction> listTransaction;
	ArrayList<TransactionInfo> listTransactionInfo;

	public RegisterInfo(String title) {
		super(title);
		listRegister = new ArrayList<Registration>();
		listTransactionInfo = new ArrayList<TransactionInfo>();
		listTransaction = new ArrayList<Transaction>();

		Container c = getContentPane();
		c.setLayout(new GridLayout(16, 2));

		tName = new JTextField(20);
		tPhoneNum = new JTextField(20);
		tAddress = new JTextField(20);
		tEmailId = new JTextField(20);
		tDepositAmount = new JTextField(20);
		tAccountNum = new JTextField(20);

		rmale = new JRadioButton("Male");
		rmale.addActionListener(this);
		rmale.setSelected(true);

		rfemale = new JRadioButton("Female");
		rfemale.addActionListener(this);
		rfemale.setSelected(true);

		rsaving = new JRadioButton("saving");
		rsaving.addActionListener(this);
		rsaving.setSelected(true);

		rcurrent = new JRadioButton("current");
		rcurrent.addActionListener(this);
		rcurrent.setSelected(true);

		ButtonGroup rgroup = new ButtonGroup();

		rgroup.add(rmale);
		rgroup.add(rfemale);
		JPanel gpanel = new JPanel();

		gpanel.add(rmale);
		gpanel.add(rfemale);

		ButtonGroup rgroup2 = new ButtonGroup();

		rgroup2.add(rsaving);
		rgroup2.add(rcurrent);

		JPanel gpanel2 = new JPanel();

		gpanel2.add(rsaving);
		gpanel2.add(rcurrent);

		String dValue[] = new String[MAX_DAY];
		for (int i = 0; i <= MAX_DAY - 1; i++) {
			dValue[i] = String.valueOf(i + 1);
		}
		day = new JComboBox(dValue);

		String mValue[] = new String[MAX_MONTH];
		for (int i = 0; i <= MAX_MONTH - 1; i++) {
			mValue[i] = String.valueOf(i + 1);
		}
		month = new JComboBox(mValue);

		int currentYear = Year.now().getValue();

		String yValue[] = new String[currentYear - START_YEAR + 1];
		int cnt = 0;
		for (int i = START_YEAR; i <= currentYear; i++) {
			yValue[cnt] = String.valueOf(i);
			cnt++;
		}
		year = new JComboBox(yValue);

		JPanel cpanel = new JPanel();
		cpanel.add(day);
		cpanel.add(month);
		cpanel.add(year);

		regstr = new JLabel("Registration");

		regstr.setForeground(Color.BLACK);

		bSubmit = new JButton("Create");
		bSubmit.addActionListener(this);

		bReset = new JButton("Show All");
		bReset.addActionListener(this);

		bSearch = new JButton("Search");
		bSearch.addActionListener(this);

		lName = new JLabel("   Name:");

		lName.setForeground(Color.BLUE);

		lDob = new JLabel("   DOB:");

		lDob.setForeground(Color.BLUE);

		lGender = new JLabel("   Gender:");

		lGender.setForeground(Color.BLUE);

		lPhoneN = new JLabel("   Phone N.:");

		lPhoneN.setForeground(Color.BLUE);

		lAdress = new JLabel("   Address:");

		lAdress.setForeground(Color.BLUE);

		lEmail = new JLabel("   Email id:");

		lEmail.setForeground(Color.BLUE);

		lAccType = new JLabel("   Account type:");
		lAccType.setForeground(Color.BLUE);

		lDepAmo = new JLabel("   Deposit Amount:");
		lDepAmo.setForeground(Color.BLUE);

		laccNo = new JLabel("   Account N.:");
		laccNo.setForeground(Color.BLUE);

		c.add(regstr);
		c.add(new JLabel(""));
		c.add(lName);
		c.add(tName);
		c.add(lDob);
		c.add(cpanel);
		c.add(lGender);
		c.add(gpanel);
		c.add(lPhoneN);
		c.add(tPhoneNum);
		c.add(lAdress);
		c.add(tAddress);
		c.add(lEmail);
		c.add(tEmailId);

		c.add(lAccType);
		c.add(gpanel2);
		c.add(lDepAmo);
		c.add(tDepositAmount);

		c.add(laccNo);
		c.add(tAccountNum);
		c.add(bSubmit);
		c.add(new JLabel(""));
		c.add(bReset);
		c.add(new JLabel(""));
		c.add(bSearch);

		setSize(800, 650);
		setLocation(200, 40);
		setResizable(false);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {
		String name = tName.getText();
		String phoneNum = tPhoneNum.getText();
		String address = tAddress.getText();
		String emailID = tEmailId.getText();
		String depositAmount = tDepositAmount.getText();
		String accountno = tAccountNum.getText();

		String gender = "";
		if (rmale.isSelected()) {
			gender = "Male";
		} else if (rfemale.isSelected()) {
			gender = "Female";
		}

		String accounttype = "";
		if (rsaving.isSelected()) {
			accounttype = "Saving";
		} else if (rcurrent.isSelected()) {
			accounttype = "Current";
		}

		String d = (String) day.getSelectedItem();
		String m = (String) month.getSelectedItem();
		String y = (String) year.getSelectedItem();
		String dob = String.format("%1$s - %2$s - %3$s", d, m, y);
		Registration r = new Registration(name, phoneNum, address, emailID, depositAmount, accountno, gender,
				accounttype, dob);
		Transaction ts = new Transaction(depositAmount, accountno);

		RegisterOperation scr = new RegisterOperation();
		findID = scr.searchInfoID(r);

		if (ae.getSource() == rmale) {
			JOptionPane.showMessageDialog(this, "Your Gender : Male");
		} else if (ae.getSource() == rfemale) {
			JOptionPane.showMessageDialog(this, "Your Gender : Female");
		}
		else if (ae.getSource() == rsaving) {
			JOptionPane.showMessageDialog(this, "Your account type : Saving");
		}
		else if (ae.getSource() == rcurrent) {
			JOptionPane.showMessageDialog(this, "Your account type : Current");
		}
		if (ae.getSource() == bSubmit) {
			int con = JOptionPane.showConfirmDialog(this, "Are You Sure to Register?");
			if (con == JOptionPane.YES_OPTION) {
				String fieldName = tName.getText();
				pattern = Pattern.compile(Regexp.NAME_PATTERN);
				boolean result1 = validate(fieldName);
				if (!result1) {
					tName.setText("");
					JOptionPane.showMessageDialog(this, "Enter Valid Name..");
					validName = RegisterValid.INVALID_ENTER;
				}
				String fieldPhoneNum = tPhoneNum.getText();
				pattern = Pattern.compile(Regexp.PHONE_PATTERN);
				boolean result2 = validate(fieldName);
				if (!result2) {
					tPhoneNum.setText("");
					JOptionPane.showMessageDialog(this, "Enter Valid phone no...");
					validPhone = RegisterValid.INVALID_ENTER;
				}
				String fieldAddress = tAddress.getText();
				pattern = Pattern.compile(Regexp.ADDRESS_PATTERN);
				boolean result3 = validate(fieldName);
				if (result3) {
					tAddress.setText("");
					JOptionPane.showMessageDialog(this, "Enter valid Address...");
					validAddress = RegisterValid.INVALID_ENTER;
				}
				String fieldEmail = tEmailId.getText();
				pattern = Pattern.compile(Regexp.EMAIL_PATTERN);
				boolean result4 = validate(fieldName);
				if (result4) {
					tEmailId.setText("");
					JOptionPane.showMessageDialog(this, "Enter valid emailid...");
					validEmail = RegisterValid.INVALID_ENTER;
				}
				String fieldDeposit = tDepositAmount.getText();
				pattern = Pattern.compile(Regexp.ACCOUNT_PATTERN);
				boolean result5 = validate(fieldName);
				if (result5) {
					tDepositAmount.setText("");
					JOptionPane.showMessageDialog(this, "Enter valid Deposit Amount....");
					validDepositAm = RegisterValid.INVALID_ENTER;
				}
				String fieldAccount = tAccountNum.getText();
				pattern = Pattern.compile(Regexp.ACCOUNT_PATTERN);
				boolean result6 = validate(fieldAccount);
				if (result6) {
					tAccountNum.setText("");
					JOptionPane.showMessageDialog(this, "Enter valid Account no. ....");
					validAccN = RegisterValid.INVALID_ENTER;
					if (!(validName == RegisterValid.INVALID_ENTER) && !(validAddress == RegisterValid.INVALID_ENTER)
							&& !(validPhone == RegisterValid.INVALID_ENTER)
							&& !(validEmail == RegisterValid.INVALID_ENTER)
							&& !(validDepositAm == RegisterValid.INVALID_ENTER)
							&& !(validAccN == RegisterValid.INVALID_ENTER))
						JOptionPane.showMessageDialog(this, "Your data saved..");
				}

				if ((result1) && (result2) && (result3) && (result4) && (result5) && (result6)) {
					if (findID != -1) {
						JOptionPane.showMessageDialog(this,
								"This account already exists. Please enter another no.acc...");
					} else {
						JOptionPane.showMessageDialog(this, "Your data saved successfully...");
						register.addInfo(r);
						transaction.addInfo(ts);
					}
				}

			}
		}
		else if (ae.getSource() == bReset) {
			new CustomerDetailStorage();
		}
		else if (ae.getSource() == bSearch) {
			if (findID == -1) {
				JOptionPane.showMessageDialog(this, "NO DATA FOUND");
			} else {
				new SearchDetailStorage(findID);
			}
		}

	}
	public boolean validate(String str) {
		matcher = pattern.matcher(str);
		return matcher.matches();
	}
	public static void main(String args[]) {
		new RegisterInfo("Register...");

	}

}
