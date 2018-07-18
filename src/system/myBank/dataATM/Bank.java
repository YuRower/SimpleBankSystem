package system.myBank.dataATM;

import java.util.ArrayList;

public class Bank {

private  ArrayList<Customer> customers = new ArrayList<>();

private  int numOfClients=0;

private static Bank myBank = new Bank();

public static Bank getBank() {
    return myBank;
}

private Bank() {

}

public  Customer getCustomer(int custNo) {
    if (custNo < customers.size()) {
        return customers.get(custNo);
    }
    return null;
}

public  void addCustomer(Customer newCustomer) {
    customers.add(newCustomer);
    numOfClients++;

}

public  int getNumOfClients() {
    return numOfClients;
}

}
