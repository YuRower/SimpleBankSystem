package system.myBank.dataATM;
public class Account {

    protected int balance;

    protected Account(int balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            this.balance = 0;
        }
    }

    public Account() {
        this.balance = 0;
    }

   
    public boolean deposit(int amt) throws Exception {
        if (amt > 0) {
            balance += amt;
            return true;
        }
        throw new Exception("Ошибка");
    }

    
    public boolean withdraw(int amt) throws Exception {
        if (amt <= balance) {
            balance -= amt;
            return true;
        }
        throw new Exception("Недостаточно средств");
    }

    
    public int getBalance() {
        return balance;
    }

}
