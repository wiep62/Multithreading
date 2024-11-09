package org.example;

public class Account {


    private long balance;
    private int accNumber;
    private boolean isBlocked = false;

    public Account(int accNumber,long balance) {
        this.accNumber = accNumber;
        this.balance = balance;
    }

    public long getBalance() {
        return balance;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public boolean isBlocked() {
        return isBlocked;
    }


/*    public synchronized void withdrawMoney(long money) {

            balance -= money;

    }*/
    public synchronized boolean withdrawMoney(long money) {
        if (balance >= money) {
            balance -= money;
            return true;
        }
        return false;
    }
//ложим деньги на счет:
    public synchronized void putMoney(long money) {
        balance += money;
    }

    public synchronized void blockAccount() {
        isBlocked = true;
    }

}
