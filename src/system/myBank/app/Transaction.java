package system.myBank.app;


import java.io.Serializable;

public class Transaction implements Serializable {

    public   String depositamount;
    private String accountno;

    public Transaction(String depositamount, String accountno){

        super();
        this.depositamount = depositamount;
        this.accountno = accountno;
    }


    public Transaction()
    {

    }

    public void setdepositamount(String depositamount) {
        this.depositamount = depositamount;
    }
    public String getdepositamount() {
        return (depositamount);
    }

    public void setaccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getaccountno() {
        return (accountno);
    }
}
