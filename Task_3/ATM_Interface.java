import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM_Interface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account account = new Account("sanskruti", "2222", 3000.0);

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (account.getUserId().equals(userId) && account.verifyPin(pin)) {
            System.out.println("Login successful!");
            boolean quit = false;
            while (!quit) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Transaction History");
                System.out.println("3. Withdraw");
                System.out.println("4. Deposit");
                System.out.println("5. Transfer");
                System.out.println("6. Quit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        account.checkBalance();
                        break;
                    case 2:
                        account.displayTransactionHistory();
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: $");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline character
                        account.withdraw(withdrawAmount);
                        break;
                    case 4:
                        System.out.print("Enter deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline character
                        account.deposit(depositAmount);
                        break;
                    case 5:
                        System.out.print("Enter recipient's User ID: ");
                        String recipientId = scanner.nextLine();
                        Account recipient = new Account(recipientId, "", 0.0);
                        System.out.print("Enter transfer amount: $");
                        double transferAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline character
                        account.transfer(recipient, transferAmount);
                        break;
                   
                    case 6:
                        System.out.println("Thank you for using the ATM. Have a nice day!");
                        quit = true;
                        break;
                   
                    default:
                        System.out.println("Invalid choice. Please try again later.");
                }
            }
        } else {
            System.out.println("Invalid User ID or PIN. Login failed.");
        }

        scanner.close();
    }
}

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class Account {
    private String userId;
    private String pin;
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public boolean verifyPin(String pin) {
        return this.pin.equals(pin);
    }

    public void checkBalance(){
        System.out.println("Your Balance is: "+ balance);
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
            System.out.println("Successfully withdrawn: $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println("Successfully deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add(new Transaction("Transfer", amount));
            System.out.println("Successfully transferred: $" + amount);
        } else {
            System.out.println("Invalid transfer amount or insufficient balance.");
        }
    }

    public void displayTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transaction history available.");
        } else {
            System.out.println("Transaction History:");
            for (Transaction transaction : transactionHistory) {
                System.out.println(transaction.getType() + ": $" + transaction.getAmount());
            }
        }
    }
}

