package com.sangarius.opp.practice4.entity;

public class Account {
    private double balance;
    private final String currency;
    private Person owner;
    private BankCard bankCard;

    public Account() {
        this.balance = 0.0;  // Початковий баланс 0
        this.currency = "USD";  // Початкова валюта USD (можна змінити за потребою)
    }

    public Account(Person owner, BankCard bankCard, double initialBalance, String currency) {
        this.balance = initialBalance;
        this.currency = currency;
        this.owner = owner;
        this.bankCard = bankCard;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public Person getOwner() {
        return owner;
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    public String getCurrency() {
        return currency;
    }
}
