package system.myBank.dataATM;

public class CheckingAccount extends Account {

    private int overdraftAmount;

    public CheckingAccount(int initBalance, int overdraftAmount) {
        this.balance=initBalance;
        this.overdraftAmount=overdraftAmount;
    }

    public CheckingAccount(int initBalance) {
        this(initBalance, 0);
    }


    public boolean withdraw(int amt) throws Exception{
        if(amt<=balance+overdraftAmount){
            balance=balance-amt;
            return true;
        }
        throw new Exception("Недостаточно средств");
    }

    public double getOverdraftAmount() {
        return overdraftAmount;
    }
}