package com.sangarius.opp.practice4.entity;

import java.util.Random;

public class BankCard2 {
    private final Account account;
    private final String cardNumber;

    public BankCard2(Account account, Person owner) {
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