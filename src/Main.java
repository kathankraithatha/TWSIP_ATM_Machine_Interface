import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ATM_Machine {
    private Map<Integer, Account> accounts;
    private int currentUserAccountNumber;

    public ATM_Machine() {
        accounts = new HashMap<>();
    }

    public void addAccount(int accountNumber, int pin, int balance) {
        accounts.put(accountNumber, new Account(pin, balance));
    }

    public boolean authenticate(int accountNumber, int pin) {
        Account account = accounts.get(accountNumber);
        return account != null && account.getPin() == pin;
    }

    public void Introduction() {
        System.out.println("Welcome to the Topper World ATM");
    }

    public void ATM() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your account number: ");
        int accountNumber = sc.nextInt();

        System.out.println("Enter your PIN: ");
        int pin = sc.nextInt();

        if (authenticate(accountNumber, pin)) {
            currentUserAccountNumber = accountNumber;
            displayMenu();
        } else {
            System.out.println("Authentication failed. Access denied.");
        }
    }

    private void displayMenu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using Topper World ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter withdrawal amount: ");
        int amount = sc.nextInt();

        Account account = accounts.get(currentUserAccountNumber);
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter deposit amount: ");
        int amount = sc.nextInt();

        Account account = accounts.get(currentUserAccountNumber);
        account.deposit(amount);
        System.out.println("Deposit successful. New balance: " + account.getBalance());
    }

    private void checkBalance() {
        Account account = accounts.get(currentUserAccountNumber);
        System.out.println("Account balance: " + account.getBalance());
    }
}

class Account {
    private int pin;
    private int balance;

    public Account(int pin, int balance) {
        this.pin = pin;
        this.balance = balance;
    }

    public int getPin() {
        return pin;
    }

    public int getBalance() {
        return balance;
    }

    public boolean withdraw(int amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ATM_Machine atm = new ATM_Machine();
//        boolean accountSet=false;

        atm.Introduction();
        Scanner sc = new Scanner(System.in);

        System.out.println("setting up the account ");



            System.out.println("Enter account number: ");
            int accountNumber = sc.nextInt();

            System.out.println("Enter PIN: ");
            int pin = sc.nextInt();

            System.out.println("Enter initial balance: ");
            int balance = sc.nextInt();

            atm.addAccount(accountNumber, pin, balance);
            System.out.println("Account set up successfully.");
//          k that an account is set up


        // If an account is set up, proceed with authentication and ATM functionality

    }
}
