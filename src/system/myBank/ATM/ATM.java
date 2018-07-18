package system.myBank.ATM;



import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.util.ArrayList;

import system.myBank.dataATM.*;
import system.myBank.app.*;

public class ATM extends javax.swing.JFrame implements  KeyListener {


    Bank bank;
    Customer currentCustomer;
    Account currentAccount;
    static Button button;

    private static boolean flag;
    private String buttonPressed;
   
    Register tname;
int customerID = 0;
int b=0;
private ArrayList<Registration> lst;
private ArrayList<Transaction> lstTrans;
    public ATM() {
        bank = Bank.getBank();
        String data[][]=new String[30][3];
        ArrayList<Registration> list=new ArrayList<Registration>();
        ArrayList<Transaction> folder = new ArrayList<Transaction>();
        try {
            FileInputStream fin=new FileInputStream("Banking.dat");
            ObjectInputStream oin=new ObjectInputStream(fin);
            folder=(ArrayList<Transaction>)oin.readObject();

            int cnt=0,c=0;
            for(Transaction tn : folder)
            {

                data[cnt][1]=tn.getdepositamount();


                cnt++;
                c=0;
            }
        }catch(Exception e)
        {
             e.printStackTrace();
         }
        try {
        	int r =0 ;
            FileInputStream finRe=new FileInputStream("Register.dat");
            ObjectInputStream oinRe=new ObjectInputStream(finRe);
            list=(ArrayList<Registration>)oinRe.readObject();
            for(Registration re : list){
                data[r][0]=re.getName();
               
                data[r][2]=re.getaccountno();

                
                b=0;
                Customer firstCustomer = new Customer(data[r][customerID], "");
          SavingsAccount cSavings = new SavingsAccount(Integer.parseInt(data[0][1]), 5);
                  CheckingAccount cAccount = new CheckingAccount(500, 1000);
               
                  firstCustomer.addAccount(cSavings);
                  firstCustomer.addAccount(cAccount);
                
                  bank.addCustomer(firstCustomer);
                  r++;
            }
        }catch(Exception e)
           {
                e.printStackTrace();
            }
        
   

        

        this.setLocationRelativeTo(null);
        this.setSize(900, 400);

        initComponents();

        for (Component c11 : jPanel3.getComponents()) {
            if ((c11.getClass() == JButton.class) && (((JButton) c11).getText() != "ENTER")) {
                JButton currentButton = (JButton) c11;
                currentButton.addActionListener(new ActionListener()  {
                	@Override
                	public void actionPerformed(ActionEvent e ){
                	
                    addDigit(e);
                }
                });
            }
        }
    }

    private void initComponents() {
        setLayout(new BorderLayout(15, 0));
        setBackground(new Color(128, 128, 255));

        final Label message = new Label("Вставьте карту чтобы воспользоваться банкоматом ", Label.CENTER);
        add(message, BorderLayout.CENTER);

        button = new Button(" Вставить карту ");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (button.getLabel().equals("Извлечь карту")) {
                    statusField.setText("Вставьте карту чтобы воспользоваться банкоматом ");
                    button.setLabel(" Вставить карту ");
                    historyArea.setText("");
                    amountField.setText("");

                    balanceButton.setEnabled(false);
                    depositButton.setEnabled(false);
                    withdrawButton.setEnabled(false);
                 
                    for (Component c : jPanel3.getComponents()) {
                        if ((c.getClass() == JButton.class) && (((JButton) c).getText() != "ENTER")) {
                            c.setEnabled(false);
                        }

                    }
                } else {
                    flag = true;

                    message.setText("Извлеките карту чтобы закончить работу с банкоматом");
                    button.setLabel("Извлечь карту");
                    statusField.setText("");
                    amountField.setText("");
                    balanceButton.setEnabled(true);
                    depositButton.setEnabled(true);
                    withdrawButton.setEnabled(true);
                    for (Component c : jPanel3.getComponents()) {
                        if ((c.getClass() == JButton.class) && (((JButton) c).getText() == "ENTER")) {
                            c.setEnabled(true);
                        }
                    }
                }
            }
        });
        SlideShow ss = null;
        try {
            ss = new SlideShow();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel p = new JPanel();

        p.add(button);
       
        p.add(ss);
      
        add(p, BorderLayout.EAST);

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                    }

                    if (statusField.isVisible() && !button.getLabel().equals("Извлечь карту"))
                        statusField.setVisible(false);
                    else
                        statusField.setVisible(true);
                }
            }
        }.start();




        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        balanceButton = new javax.swing.JButton();
        depositButton = new javax.swing.JButton();
        withdrawButton = new javax.swing.JButton();
        amountField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        oneButton = new javax.swing.JButton();

        twoButton = new javax.swing.JButton();

        threeButton = new javax.swing.JButton();

        fourButton = new javax.swing.JButton();

        fiveButton = new javax.swing.JButton();

        sixButton = new javax.swing.JButton();

        sevenButton = new javax.swing.JButton();

        eightButton = new javax.swing.JButton();

        nineButton = new javax.swing.JButton();

        zeroButton = new javax.swing.JButton();

        pointButton = new javax.swing.JButton();

        enterButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        historyArea = new javax.swing.JTextArea();
        statusField = new javax.swing.JTextField();

        // setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("my Bank ATM");
        setAlwaysOnTop(true);
        setPreferredSize(new java.awt.Dimension(801, 350));
        setSize(new java.awt.Dimension(800, 350));

        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        jPanel2.setLayout(new java.awt.GridLayout(4, 1));


        balanceButton.setText("Проверка баланса на счете");
        balanceButton.setEnabled(false);
        balanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceButtonActionPerformed(evt);
            }
        });
        jPanel2.add(balanceButton);

        depositButton.setText("Внести сумму");
        depositButton.setEnabled(false);
        depositButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositButtonActionPerformed(evt);
            }
        });
        jPanel2.add(depositButton);

        withdrawButton.setText("Снять сумму");
        withdrawButton.setEnabled(false);
        withdrawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawButtonActionPerformed(evt);
            }
        });
        jPanel2.add(withdrawButton);

        amountField.setToolTipText("");
        jPanel2.add(amountField);
        amountField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    amountField.getText();
                    enterButton.doClick();
                }
            }

        });
        jPanel1.add(jPanel2);

        jPanel3.setLayout(new java.awt.GridLayout(4, 3));

        oneButton.setText("1");
        jPanel3.add(oneButton);

        twoButton.setText("2");
        jPanel3.add(twoButton);

        threeButton.setText("3");
        jPanel3.add(threeButton);

        fourButton.setText("4");
        jPanel3.add(fourButton);

        fiveButton.setText("5");
        jPanel3.add(fiveButton);

        sixButton.setText("6");
        jPanel3.add(sixButton);

        sevenButton.setText("7");
        jPanel3.add(sevenButton);

        eightButton.setText("8");
        jPanel3.add(eightButton);

        nineButton.setText("9");
        jPanel3.add(nineButton);

        zeroButton.setText("0");
        jPanel3.add(zeroButton);

        pointButton.setText(".");
        jPanel3.add(pointButton);

        for (Component c : jPanel3.getComponents()) {
            if ((c.getClass() == JButton.class) && (((JButton) c).getText() != "ENTER")) {
                c.setEnabled(false);
            }
        }
        enterButton.setText("ENTER");
        buttonPressed = "";
        enterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                if (flag) {
                    enterButtonActionPerformed(evt);
                } else {
                    statusField.setText("Вставьте карту что бы воспользоваться банкоматом ");
                }


            }
        });
        jPanel3.add(enterButton);
        


        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1, BorderLayout.WEST);

        historyArea.setEditable(false);
        historyArea.setColumns(20);
        historyArea.setRows(5);
        jScrollPane1.setViewportView(historyArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        statusField.setEditable(false);
        statusField.setText("Добро пожаловать в мой банк! Вставте карту , введите идентификатор клиента и нажмите клавишу ENTER...");
        getContentPane().add(statusField, BorderLayout.SOUTH);

        pack();
    }


    private void enterButtonActionPerformed(java.awt.event.ActionEvent evt) {

        int customerID = 0;
        try {

            customerID = Integer.parseInt(amountField.getText());
            customerID-=1;
            currentCustomer = bank.getCustomer(customerID);
            currentAccount = currentCustomer.getAccount(0);
            historyArea.append("Клиент с ID = " + (customerID+1) + " - " + currentCustomer.getLastName() + ", " + currentCustomer.getFirstName() + "\n");
            balanceButton.setEnabled(true);
            depositButton.setEnabled(true);
            withdrawButton.setEnabled(true);
            for (Component c : jPanel3.getComponents()) {
                if ((c.getClass() == JButton.class) && (((JButton) c).getText() != "ENTER")) {
                    c.setEnabled(true);
                }
            }
            enterButton.setEnabled(false);
        } catch (Exception ex) {
            historyArea.append("ОШИБКА: Клиент не найден или неверен идентификатор клиента!\n");
        }
try{
        amountField.setText("");
        statusField.setText("Клиент: " + currentCustomer.getLastName() + ", " + currentCustomer.getFirstName());
}catch(Exception ex){
	 historyArea.append("Ошибка");
}
    }

    private void balanceButtonActionPerformed(java.awt.event.ActionEvent evt) {

        try {
            historyArea.append("Баланс  " + currentCustomer.getFirstName() + " на счету $" + currentAccount.getBalance());
        } catch (Exception e) {
            statusField.setText("Нажмите Enter");
        }
        if (currentAccount instanceof CheckingAccount) {
            historyArea.append(". Эта учетная запись с защитой от овердрафта $" + ((CheckingAccount) currentAccount).getOverdraftAmount() + "\n");
        } else {
            historyArea.append(". Это сберегательный счет с процентной ставкой " + ((SavingsAccount) currentAccount).getInterestRate() + "%\n");
        }
        statusField.setText("Готово");
    }

    private void depositButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	AmountInfo a;
        try {
            try{
                FileInputStream fin=new FileInputStream("Banking.dat");
                ObjectInputStream oin=new ObjectInputStream(fin);
                 lstTrans = (ArrayList<Transaction>)oin.readObject();
            }catch(Exception e)
            {
                statusField.setText("ОШИБКА\n");
            }
            int amt = Integer.parseInt(amountField.getText());
            currentAccount.deposit(amt);
            historyArea.append("Взнос : $" + amt + ", новий баланс =  $" + currentAccount.getBalance() + "\n");
            statusField.setText("Готово");

            lstTrans.get(customerID).setdepositamount(currentAccount.getBalance()+"");
            try   {
                FileOutputStream fout=new FileOutputStream("Banking.dat");
                ObjectOutputStream oout=new ObjectOutputStream(fout);
                oout.writeObject(lstTrans);
            }catch(Exception e){}
            


        } catch (Exception e) {
            historyArea.append("ОШИБКА: невозможно завершить операцию взноса!\n");
       
        }
        amountField.setText("");

    }

    
    private void withdrawButtonActionPerformed(java.awt.event.ActionEvent evt) {

        try {
            try {
                FileInputStream fin = new FileInputStream("Banking.dat");
                ObjectInputStream oin = new ObjectInputStream(fin);
                lstTrans = (ArrayList<Transaction>) oin.readObject();
            } catch (Exception e) {
                statusField.setText("ОШИБКА");
            }

            int amt = Integer.parseInt(amountField.getText());
            if (currentAccount.withdraw(amt)) {
                historyArea.append("Взнос: $" + amt + ", новий баланс =  $" + currentAccount.getBalance() + "\n");

                statusField.setText("Готово");
            }



        lstTrans.get(customerID).setdepositamount(currentAccount.getBalance()+"");
            try   {
                FileOutputStream fout=new FileOutputStream("Banking.dat");
                ObjectOutputStream oout=new ObjectOutputStream(fout);
                oout.writeObject(lstTrans);
            }catch(Exception e){
                lstTrans=new ArrayList<Transaction>();
            }


        } catch (Exception e) {
            historyArea.append("ОШИБКА: невозможно завершить операцию снятия наличных!\n");
            statusField.setText("ОШИБКА\n");
        }

        amountField.setText("");

    }

    private void addDigit(ActionEvent evt) {

        amountField.setText(amountField.getText() + ((JButton) evt.getSource()).getText());
    }


    public static void main(String args[]) {


        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ATM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ATM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ATM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ATM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
  
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ATM().setVisible(true);
            }
        });

    }

  
    private javax.swing.JTextField amountField;
    private javax.swing.JButton balanceButton;
    private javax.swing.JButton depositButton;
    private javax.swing.JButton eightButton;
    private javax.swing.JButton enterButton;
    private javax.swing.JButton fiveButton;
    private javax.swing.JButton fourButton;
    private javax.swing.JTextArea historyArea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nineButton;
    private javax.swing.JButton oneButton;
    private javax.swing.JButton pointButton;
    private javax.swing.JButton sevenButton;
    private javax.swing.JButton sixButton;
    public javax.swing.JTextField statusField;
    private javax.swing.JButton threeButton;
    private javax.swing.JButton twoButton;
    private javax.swing.JButton withdrawButton;
    private javax.swing.JButton zeroButton;

    @Override
    public void keyTyped(KeyEvent e) {

    }

   
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    class SlideShow extends JComponent {
        BufferedImage img = ImageIO.read(new File("arrow.png"));
        private BufferedImage[] slide;

        private Dimension slideSize;

        private volatile int currSlide;

        private Thread internalThread;

        private volatile boolean noStopRequested;

        public SlideShow() throws IOException {
            currSlide = 0;
            slideSize = new Dimension(50, 50);
            buildSlides();

            setMinimumSize(slideSize);
            setPreferredSize(slideSize);
            setMaximumSize(slideSize);
            setSize(slideSize);

            noStopRequested = true;
            Runnable r = new Runnable() {
                public void run() {
                    try {
                        runWork();
                    } catch (Exception x) {
                        x.printStackTrace();
                    }
                }
            };

            internalThread = new Thread(r, "SS");
            internalThread.start();
        }

        private void buildSlides() {
            RenderingHints renderHints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            renderHints.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);

            slide = new BufferedImage[20];

            Color rectColor = Color.LIGHT_GRAY;
            Color circleColor = Color.GREEN;


            for (int i = 0; i < slide.length; i++) {
                slide[i] = new BufferedImage(slideSize.width, slideSize.height,
                        BufferedImage.TYPE_INT_RGB);

                Graphics2D g2 = slide[i].createGraphics();

                g2.setRenderingHints(renderHints);

                g2.setColor(rectColor);

                g2.fillRect(0, 0, slideSize.width, slideSize.height);

                g2.setColor(circleColor);

                int diameter = 0;
                if (i < (slide.length / 2)) {
                    diameter = 5 + (8 * i);
                } else {
                    diameter = 5 + (8 * (slide.length - i));
                }

                int inset = (slideSize.width - diameter) / 2;
              
                g2.fillRect(inset, inset, diameter, diameter);

                g2.setColor(Color.black);
                g2.drawRect(0, 0, slideSize.width - 1, slideSize.height - 1);

                g2.dispose();
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
           
            int x = (getWidth() - img.getWidth()) / 2;
            int y = (getHeight() - img.getHeight()) / 2;
            g.drawImage(slide[currSlide], 0, 0, this);
            g.drawImage(img, x, y, this);
        }

        private void runWork() {
            while (noStopRequested) {
                try {
                    Thread.sleep(100); 
                    currSlide = (currSlide + 1) % slide.length;
                    repaint();
                } catch (InterruptedException x) {
                    Thread.currentThread().interrupt();
                }
            }
        }

    }
}








