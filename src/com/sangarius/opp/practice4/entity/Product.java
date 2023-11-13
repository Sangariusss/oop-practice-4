package com.sangarius.opp.practice4.entity;

public class Product {
    private final String name;
    private final double price;
    private final String currency;

    public Product(String name, double price, String currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }
}


