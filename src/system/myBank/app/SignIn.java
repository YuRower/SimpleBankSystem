package system.myBank.app;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import system.myBank.ATM.ATM;
import system.myBank.app.info.DeleteAccountInfo;
import system.myBank.app.info.DepositInfo;
import system.myBank.app.info.RegisterInfo;
import system.myBank.app.info.TransferInfo;
import system.myBank.app.info.WithdrawInfo;
import system.myBank.app.storage.CustomerDetailStorage;
import system.myBank.app.storage.HistoryTransactionDetailStorage;
import system.myBank.chat.ChatClient;
import system.myBank.chat.server.ChatServer;

import javax.imageio.ImageIO;
import javax.swing.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.xml.XMLLayout;

public class SignIn extends JFrame implements ActionListener {
	static {
		new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
	}

	static Logger logger = Logger.getLogger(SignIn.class);

	public static boolean accessGranted;
	private final JLabel author,welcome;
	private BufferedImage bg;
	private JPopupMenu popMenu = new JPopupMenu();
	private final JMenu menuEdit;
	private JDesktopPane desktop = new JDesktopPane();
	private final JMenuItem withdraw, history, transfer, deposit, create, info, delete,chat,test;

	private JMenuBar bar;
	private java.util.Date currDate = new java.util.Date();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
	private String d = sdf.format(currDate);

	private JMenu menuCreate, menuInfo, menuDelete, menuTest, menuChat;

	private ChatClient myChat;
	Image img;
	Image bgimage = null;

	private JMenu language;

	private JMenuItem rus_language;

	public SignIn(String title) {
		super(title);
		logger.info("Start app");

		try {
			bg = ImageIO.read(new File("background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("image didnt load");
		}
		JPanel statusBar = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
			}

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(600, 500);
			}
		};

		Container c = getContentPane();
		c.setLayout(new GridLayout(9, 3));
		bar = new JMenuBar();

		setSize(700, 550);
		setJMenuBar(bar);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				quitApp();
			}
		});

		setContentPane(new JPanel(new BorderLayout()));

		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);

		menuCreate = new JMenu("Create Account");
		menuCreate.addActionListener(this);
		create = new JMenuItem("New Account");

		create.addActionListener(this);
		menuCreate.add(create);

		menuInfo = new JMenu("Account Info");
		info = new JMenuItem("Info");
		info.addActionListener(this);
		menuInfo.add(info);

		menuEdit = new JMenu("Operation");
		deposit = new JMenuItem("Deposit Money");
		deposit.addActionListener(this);
		withdraw = new JMenuItem("Withdraw Money");
		withdraw.addActionListener(this);

		history = new JMenuItem("History of Operation");
		history.addActionListener(this);
		transfer = new JMenuItem("Transfer");
		transfer.addActionListener(this);

		menuEdit.add(deposit);
		menuEdit.add(withdraw);
		menuEdit.addSeparator();

		menuEdit.addSeparator();
		menuEdit.add(history);
		menuEdit.add(transfer);

		menuDelete = new JMenu("Delete Account");
		delete = new JMenuItem("Delete Customer");
		delete.addActionListener(this);
		menuDelete.add(delete);

		language = new JMenu("Language1");
		rus_language = new JMenuItem("RUS");
		rus_language.addActionListener(this);
		language.add(rus_language);

		menuTest = new JMenu("Test ATM");
		test = new JMenuItem("ATM");
		test.addActionListener(this);
		menuTest.add(test);
		menuChat = new JMenu("HELP Chat");
		chat = new JMenuItem("HELP");

		chat.addActionListener(this);
		menuChat.add(chat);

		bar.add(menuCreate);
		bar.add(menuEdit);
		bar.add(menuDelete);
		bar.add(menuInfo);
		bar.add(menuTest);
		bar.add(menuChat);
		bar.add(language);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				checkMouseTrigger(me);
			}

			public void mouseReleased(MouseEvent me) {
				checkMouseTrigger(me);
			}

			private void checkMouseTrigger(MouseEvent me) {
				if (me.isPopupTrigger())
					popMenu.show(me.getComponent(), me.getX(), me.getY());
			}
		});

		author = new JLabel(" " + "BankSystem ", Label.LEFT);
		author.setForeground(Color.black);
		author.setToolTipText("Program's Title");

		welcome = new JLabel("Добро пожаловать ,Cегодня  " + d + " ", JLabel.RIGHT);
		welcome.setForeground(Color.black);
		statusBar.setLayout(new BorderLayout());
		statusBar.add(author, BorderLayout.WEST);
		statusBar.add(welcome, BorderLayout.SOUTH);

		getContentPane().add(desktop, BorderLayout.CENTER);
		getContentPane().add(statusBar, BorderLayout.SOUTH);

		setSize(800, 500);
		setResizable(false);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {

		Object obj = ae.getSource();

		getSourceMethod(obj);

	}

	public void getSourceMethod(Object marker) {
		if (marker == chat) {

			myChat = new ChatClient();
			myChat.launchFrame();
		}

		else if (marker == test) {

			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					new ATM().setVisible(true);
				}
			});
		}else if (marker==rus_language) {
			
			String language_country="RU";
			String country="RU";
			Locale current = new Locale(language_country, country);
			ResourceBundle rb = ResourceBundle.getBundle("property.text", current);
			String s1 = rb.getString("menuCreate");
			/*String s2 = rb.getString("create");
			String s3 = rb.getString("menuInfo");
			String s4 = rb.getString("info");
			String s5 = rb.getString("menuEdit");
			String s6 = rb.getString("deposit");
			String s7 = rb.getString("withdraw");
			String s8 = rb.getString("history");*/
			withdraw.setText(s1);
		
		} else if (marker == create) {
			new RegisterInfo("Registration");
		}

		else if (marker == history) {
			new HistoryTransactionDetailStorage();
		}

		else if (marker == transfer) {
			new TransferInfo("  Transfer");
		}

		else if (marker == deposit) {
			new DepositInfo(" Deposit...");
		}

		else if (marker == withdraw) {
			new WithdrawInfo(" withdraw...");
		}

		else if (marker == delete) {
			new DeleteAccountInfo(" Delete Account...");
		}

		else if (marker == info) {
			new CustomerDetailStorage();
		} else {
			throw new RuntimeException("not Found");

		}
	}

	private void quitApp() {

		int reply = JOptionPane.showConfirmDialog(this, "Are you really want to exit\nFrom BankSystem?",
				"BankSystem - Exit", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (reply == JOptionPane.YES_OPTION) {
			setVisible(false);
			dispose();
			System.out.println("Thanks for Using BankSystem");
			System.exit(0);
		} else if (reply == JOptionPane.NO_OPTION) {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}

	public static void main(String args[]) {

		new SignIn(" Admin... ");

	}

}