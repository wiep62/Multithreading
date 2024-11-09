package org.example;

import java.util.HashMap;
import java.util.Random;

public class Bank {
//список аккаунтов (пользователей) банка:
    private HashMap<Integer, Account> accounts;
    private final Random random = new Random(); //todo : Random() - создаёт генератор чисел, использующий уникальное начальное число

    {
        accounts = fillAccounts();
    }

//Общий баланс банка:

    public long getTotalBalance() {
        return accounts.values().stream().mapToLong(Account::getBalance).sum();
    }
//Проверка на мошенничество:
    private synchronized boolean isFraud(int fromAccountNum, int toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000); //приостанавливает программу на 1 сек
        return random.nextBoolean();
    }

    //todo: чтобы  public void transfer код не приводил к взаимной блокировке: ввести некоторый порядок на счетах
    // * у счетов есть какой-то уникальный идентификатор
    // * мы всегда можем сначала занимать монитор меньшего счета, а потом большего (или наоборот)


//перевод денег:
    //todo^ InterruptedException сигнализирует о том,
    // что поток просят завершить его работу.
    // При этом вас не просят немедленно завершить свою работу.

    public void transfer(int fromAccountNum, int toAccountNum, int amount)

            throws InterruptedException {

        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);
        if (fromAccount.isBlocked() || toAccount.isBlocked()) { //если счет откуда перевод или счет куда перевод
            return;
        }

        if (fromAccount.getAccNumber() < toAccount.getAccNumber()) {
            synchronized (fromAccount) {
                synchronized (toAccount) {
                    transaction(amount, fromAccount, toAccount);  //todo тут происходит перевод денег
                }
            }
        } else {
                synchronized (toAccount) {
                    synchronized (fromAccount) {
                        transaction(amount, fromAccount, toAccount);  //todo тут происходит перевод денег
                    }
                }

        }




        if (amount > 50000) { //проверка счета
            if (isFraud(fromAccountNum, toAccountNum, amount)) {
                transaction(amount, toAccount, fromAccount);
                fromAccount.blockAccount();
                toAccount.blockAccount();
            }

        }
    }




/**
 * InterruptedException — это проверенное исключение, которое возникает,
 * если поток ожидает, спит или блокируется для какого-либо события,
 * и это событие прерывается другим потоком. Ошибка может произойти,
 * когда поток ожидает ввода, освобождения блокировки или завершения
 * какой-либо другой операции, а другой поток прерывает ожидающий поток.
  * */

//метод перевода денег с 1 счета на другой:
    private void transaction(int amount, Account fromAccount, Account toAccount) throws InterruptedException {
        if (fromAccount.getBalance() < 0) throw new InterruptedException();
        else{
            toAccount.putMoney(amount); //добавляет получателю на счет деньги
            fromAccount.withdrawMoney(amount); //отнимает у отправителя деньги
        }

    }

/*//метод перевода денег с 1 счета на другой:
private void transaction(int amount, Account fromAccount, Account toAccount)  {
    if (fromAccount.withdrawMoney(amount)) {
        toAccount.putMoney(amount);
    }

}*/


    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(int accountNum) {
        Account account = accounts.get(accountNum);
        return account.getBalance();
    }

    public void setAccounts(HashMap<Integer, Account> accounts) {
        this.accounts = accounts;
    }

    //Создаем заполнение 100 аккаунтов:
    private static HashMap<Integer, Account> fillAccounts() {
        HashMap<Integer, Account> accountMap = new HashMap<>();
        for (int i = 1; i <= 100; i++) {
            long initialValue = (long) (80000 + 20000 * Math.random()); //создаем баланс на счете
            Account account = new Account(i, initialValue); //берем конструктор Account и добавляем в него значения
            accountMap.put(i, account); //создаем список из аккаунтов
        }
        return accountMap;
    }


}