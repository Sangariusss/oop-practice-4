package com.sangarius.opp.practice4.console;

import com.sangarius.opp.practice4.entity.Account;
import com.sangarius.opp.practice4.entity.Account.BankCard;
import com.sangarius.opp.practice4.entity.BankCard2;
import com.sangarius.opp.practice4.entity.Person;
import com.sangarius.opp.practice4.entity.Product;
import com.sangarius.opp.practice4.entity.Transaction;
import com.sangarius.opp.practice4.service.Bank;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankConsole {
    private final Bank bank;
    private Person currentUser;
    private final List<Person> registeredUsers;
    private List<Product> productList;

    public BankConsole(Bank bank) {
        this.bank = bank;
        productList = new ArrayList<>();
        this.productList = bank.getProductList();

        registeredUsers = new ArrayList<>();

        // Додавання зареєстрованих користувачів
        registeredUsers.add(new Person("Volodymyr", "passwordTest", new Account()));
        registeredUsers.add(new Person("Араквіель", "passwordTest", new Account()));
    }

    public void start() {
        // Додайте ініціалізацію та вхід користувача тут
        // Наприклад, авторизація, створення облікового запису, тощо

        // Приклад авторизації
        currentUser = authenticateUser();

        if (currentUser != null) {
            addBankCardToCurrentUser();
            displayUserInfo(currentUser);

            // Основний цикл для взаємодії з користувачем
            boolean exit = false;
            Scanner scanner = new Scanner(System.in);

            while (!exit) {
                // Виведіть меню та опції для користувача
                System.out.println("1. Перевірити баланс");
                System.out.println("2. Зняти гроші");
                System.out.println("3. Поповнити баланс");
                System.out.println("4. Переказати кошти");
                System.out.println("5. Купити товар");
                System.out.println("6. Вийти");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 ->
                        // Опція для перевірки балансу
                        checkBalance(currentUser);
                    case 2 ->
                        // Опція для зняття грошей
                        withdrawMoney(currentUser);
                    case 3 ->
                        // Опція для поповнення балансу
                        depositMoney(currentUser);
                    case 4 ->
                        // Опція для переказу коштів
                        transferMoney();
                    case 5 ->
                        // Опція для покупки товару
                        buyProduct(currentUser);
                    case 6 -> {
                        // Виведення всіх транзакцій в кінці роботи програми
                        List<Transaction> allTransactions = bank.getTransactionList();
                        System.out.println("Список всіх транзакцій:");
                        for (Transaction transaction : allTransactions) {
                            System.out.println(transaction.getTransactionInfo());
                        }
                        // Вихід з програми
                        exit = true;
                    }
                    default -> System.out.println("Невірний вибір. Спробуйте ще раз.");
                }
            }
        } else {
            System.out.println("Невдала авторизація. Вихід з програми.");
        }
    }

    private void displayUserInfo(Person currentUser) {
        System.out.println("Ім'я користувача: " + currentUser.getName());
        List<BankCard2> userCards = currentUser.getBankCards();

        if (userCards.isEmpty()) {
            System.out.println("У вас немає банківських карток.");
        } else {
            System.out.println("Номери карток:");

            for (BankCard2 card : userCards) {
                System.out.println(card.getCardNumber());
            }
        }
    }

    private void addBankCardToCurrentUser() {
        BankCard2 newCard = new BankCard2(currentUser.getAccount(), currentUser);
        currentUser.addBankCard(newCard);
    }

    private Person authenticateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть ім'я користувача: ");
        String username = scanner.nextLine();
        System.out.print("Введіть пароль: ");
        String password = scanner.nextLine();

        for (Person person : registeredUsers) {
            if (person.getName().equals(username) && person.authenticate(password)) {
                // Авторизація успішна
                return person;
            }
        }

        System.out.println("Невірне ім'я користувача або пароль.");
        return null;
    }

    private void checkBalance(Person currentUser) {
        if (currentUser != null && currentUser.isAccountOwner()) {
            double balance = currentUser.getAccount().getBalance();
            String currency = currentUser.getAccount().getCurrency();
            System.out.println("Ваш поточний баланс: " + balance + " " + currency);
        } else {
            System.out.println("Ви не маєте облікового запису або не авторизовані.");
        }
    }

    private void withdrawMoney(Person currentUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть суму для зняття: ");
        double amount = scanner.nextDouble();

        boolean result = currentUser.getAccount().withdraw(amount);
        if (result) {
            System.out.println("Знято " + amount + " " + currentUser.getAccount().getCurrency());
        } else {
            System.out.println("Недостатньо коштів або невірна сума.");
        }
    }

    private void depositMoney(Person currentUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть суму для поповнення: ");
        double amount = scanner.nextDouble();

        currentUser.getAccount().deposit(amount);
        System.out.println("Поповнено " + amount + " " + currentUser.getAccount().getCurrency());
    }

    private void transferMoney() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть номер банківської картки одержувача: ");
        String receiverCardNumber = scanner.nextLine();

        Account.BankCard receiverCard = bank.getBankCardByNumber(receiverCardNumber);

        if (receiverCard != null) {
            System.out.print("Введіть суму для переказу: ");
            double amount = scanner.nextDouble();

            boolean result = bank.transferFunds(
                this.currentUser.getAccount(),
                receiverCard.getAccount(),
                amount,
                null,
                0
            );

            if (result) {
                System.out.println("Переказано " + amount + " " + this.currentUser.getAccount().getCurrency() + " на картку одержувача.");
            } else {
                System.out.println("Недостатньо коштів або невірна сума для переказу.");
            }
        } else {
            System.out.println("Банківська картка одержувача не знайдена.");
        }
    }

    private void displayProductList() {
        System.out.println("Список товарів:");
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " - " + product.getPrice() + " " + product.getCurrency());
        }
    }

    private void buyProduct(Person currentUser) {
        Scanner scanner = new Scanner(System.in);
        displayProductList();
        System.out.print("Виберіть номер товару для покупки: ");
        int productIndex = scanner.nextInt();

        if (productIndex >= 1 && productIndex <= productList.size()) {
            Product selectedProduct = productList.get(productIndex - 1);

            System.out.print("Введіть кількість товару, яку ви хочете купити: ");
            int quantity = scanner.nextInt();

            double totalAmount = selectedProduct.getPrice() * quantity;
            String currency = selectedProduct.getCurrency();
            BankCard cardNumber = currentUser.getAccount().getBankCard();

            boolean result = currentUser.getAccount().withdraw(totalAmount);

            if (result) {
                // Додавання транзакції
                Transaction transaction = new Transaction(
                    currentUser,
                    cardNumber,
                    selectedProduct,
                    quantity,
                    totalAmount,
                    currency,
                    currentUser.getAccount().getBalance()
                );
                bank.addTransaction(transaction);

                System.out.println(currentUser.getName() + " купив/ла " + selectedProduct.getName() + " (" + quantity + ")");
                System.out.println("Сума: " + totalAmount + " " + currency);
                System.out.println("Залишилось на балансі: " + currentUser.getAccount().getBalance() + " " + currency);
            } else {
                System.out.println("Недостатньо коштів для покупки.");
            }
        } else {
            System.out.println("Невірний номер товару.");
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        BankConsole console = new BankConsole(bank);
        console.start();
    }
}