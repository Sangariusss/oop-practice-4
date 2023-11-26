package com.sangarius.opp.practice4.entity;

import java.util.Random;

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

    public class BankCard {
        private final Account account;
        private final String cardNumber;

        public BankCard(Account account, Person owner) {
            this.account = account;
            this.cardNumber = generateCardNumber();
        }

        public Account getAccount() {
            return account;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public String generateCardNumber() {
            StringBuilder cardNumber = new StringBuilder();
            Random random = new Random();

            // Починаємо з першого рядка номера картки (зазвичай 4)
            cardNumber.append("4");

            // Генеруємо 15 цифр для решти номера картки
            for (int i = 0; i < 15; i++) {
                int digit = random.nextInt(10);
                cardNumber.append(digit);
            }

            return cardNumber.toString();
        }
    }
}
