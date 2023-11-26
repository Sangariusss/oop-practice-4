package com.sangarius.opp.practice4.service;

import com.sangarius.opp.practice4.entity.Account;
import com.sangarius.opp.practice4.entity.Account.BankCard;
import com.sangarius.opp.practice4.entity.Product;
import com.sangarius.opp.practice4.entity.Transaction;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final List<Account.BankCard> bankCards;
    private final List<Product> productList;
    private final List<Transaction> transactionList;

    public Bank() {
        bankCards = new ArrayList<>();
        this.productList = new ArrayList<>();
        this.transactionList = new ArrayList<>();

        // Створення списку товарів
        productList.add(new Product("Товар 1", 100.0, "USD"));
        productList.add(new Product("Товар 2", 200.0, "USD"));
        productList.add(new Product("Товар 3", 150.0, "USD"));
        productList.add(new Product("Товар 4", 300.0, "USD"));
        productList.add(new Product("Товар 5", 50.0, "USD"));
        productList.add(new Product("Товар 6", 180.0, "USD"));
        productList.add(new Product("Товар 7", 250.0, "USD"));
        productList.add(new Product("Товар 8", 120.0, "USD"));
        productList.add(new Product("Товар 9", 270.0, "USD"));
        productList.add(new Product("Товар 10", 80.0, "USD"));
    }

    public Account.BankCard getBankCardByNumber(String cardNumber) {
        for (Account.BankCard bankCard : bankCards) {
            if (bankCard.getCardNumber().equals(cardNumber)) {
                return bankCard;
            }
        }
        return null;
    }

    public boolean transferFunds(Account sender, Account receiver, double amount, Product product, int quantity) {
        if (sender != null && receiver != null && (sender.withdraw(amount))) {
                receiver.deposit(amount);

                Transaction transaction = new Transaction(
                    sender.getOwner(),
                    sender.getBankCard(),
                    product,
                    quantity,
                    amount,
                    sender.getCurrency(),
                    sender.getBalance()
                );

                addTransaction(transaction);

                return true;

        }
        return false;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}

