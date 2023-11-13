package com.sangarius.opp.practice4.entity;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private final String name;
    private final Account account;
    private final String password;

    private final List<BankCard> bankCards;

    public Person(String name, String password, Account account) {
        this.name = name;
        this.password = password;
        this.account = account;
        this.bankCards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Account getAccount() {
        return account;
    }

    public List<BankCard> getBankCards() {
        return bankCards;
    }

    public void addBankCard(BankCard bankCard) {
        bankCards.add(bankCard);
    }

    public boolean authenticate(String enteredPassword) {
        return password.equals(enteredPassword);
    }

    // Метод для перевірки власності облікового запису
    public boolean isAccountOwner() {
        return account != null;
    }
}

