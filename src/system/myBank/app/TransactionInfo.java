package system.myBank.app;

import java.io.Serializable;

public class TransactionInfo implements Serializable {
    private static final long serialVersionUID = 6960215485292099914L;
   
    private String accountno;
    private String deposit;
    private String withdraw;
  
    public TransactionInfo(String accountno,  String withdraw,String deposit) {
        super();
     
        this.accountno = accountno;
        this.deposit = deposit;
        this.withdraw = withdraw;
      
    }

    public TransactionInfo(){
    }


    public void setaccountno(String accountno) {
        this.accountno = accountno;
    }


    public String getaccountno() {
        return accountno;
    }


    public String getDeposit() {
        return deposit;
    }



    public String getWithdraw() {
        return withdraw;
    }



}

