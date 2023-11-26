package com.sangarius.opp.practice4.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private final Person person;
    private final Account.BankCard bankCard;
    private final Product product;
    private final int quantity;
    private final double amount;
    private final String currency;
    private final Date timestamp;
    private final double remainingBalance;

    public Transaction(Person person, Account.BankCard bankCard, Product product, int quantity, double amount, String currency, double remainingBalance) {
        this.person = person;
        this.bankCard = bankCard;
        this.product = product;
        this.quantity = quantity;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = new Date();
        this.remainingBalance = remainingBalance;
    }

    public String getTransactionInfo() {
        String result = "";
        result += "Transaction by " + person.getName() + " using card " + bankCard + "\n"; // Fix variable name
        result += "Product: " + product.getName() + "\n";
        result += "Quantity: " + quantity + "\n";
        result += "Total Amount: " + amount + " " + currency + "\n"; // Use amount and currency variables
        result += "Timestamp: " + formatDate(timestamp) + "\n"; // Format the timestamp
        return result;
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Define the desired date format
        return dateFormat.format(date); // Format the date and return as a string
    }
}


