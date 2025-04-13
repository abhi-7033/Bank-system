import java.util.*;

class BankAccount {
    private String accountHolder;
    private String accountNumber;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String accountHolder, String accountNumber) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Credited: ₹" + amount);
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            transactionHistory.add("Debited: ₹" + amount);
            System.out.println("₹" + amount + " withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History for account " + accountNumber + ":");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public void displayDetails() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: ₹" + balance);
    }
}

public class BankSystem {
    private static Map<String, BankAccount> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== Welcome to Simple Bank System ====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Transaction History");
            System.out.println("6. View Account Details");
            System.out.println("7. Exit");
            System.out.print("Choose an option (1-7): ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear buffer

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    handleWithdraw();
                    break;
                case 4:
                    handleCheckBalance();
                    break;
                case 5:
                    handleTransactionHistory();
                    break;
                case 6:
                    handleViewDetails();
                    break;
                case 7:
                    System.out.println("Thank you for using the Bank System.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Account Number: ");
        String accNum = scanner.nextLine();

        if (accounts.containsKey(accNum)) {
            System.out.println("Account with this number already exists.");
        } else {
            BankAccount newAccount = new BankAccount(name, accNum);
            accounts.put(accNum, newAccount);
            System.out.println("Account created successfully.");
        }
    }

    private static BankAccount getAccount() {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();
        BankAccount account = accounts.get(accNum);
        if (account == null) {
            System.out.println("Account not found.");
        }
        return account;
    }

    private static void handleDeposit() {
        BankAccount account = getAccount();
        if (account != null) {
            System.out.print("Enter amount to deposit: ₹");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.deposit(amount);
        }
    }

    private static void handleWithdraw() {
        BankAccount account = getAccount();
        if (account != null) {
            System.out.print("Enter amount to withdraw: ₹");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.withdraw(amount);
        }
    }

    private static void handleCheckBalance() {
        BankAccount account = getAccount();
        if (account != null) {
            account.checkBalance();
        }
    }

    private static void handleTransactionHistory() {
        BankAccount account = getAccount();
        if (account != null) {
            account.printTransactionHistory();
        }
    }

    private static void handleViewDetails() {
        BankAccount account = getAccount();
        if (account != null) {
            account.displayDetails();
        }
    }
}
