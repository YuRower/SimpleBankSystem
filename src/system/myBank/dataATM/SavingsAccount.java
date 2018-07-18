package system.myBank.dataATM;

public class SavingsAccount extends Account {

    private int interestRate;

    public SavingsAccount(int double1, int interestRate) {
        this.balance =double1;
        this.interestRate=interestRate;
    }

    public void addInterestRate()
    {
        this.balance=(int) (this.balance*(1+interestRate));
    }

    public int getInterestRate() {
        return interestRate;
    }


}
