import java.util.Scanner;

class BankAccount {
    private double balance;
    private String accountNumber;

    // Constructor to initialize the account with balance and account number
    public BankAccount(double initialBalance, String accountNumber) {
        this.balance = initialBalance;
        this.accountNumber = accountNumber;
    }

    // Method to withdraw money and validate if there's enough balance
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true; // Successful withdrawal
        } else {
            return false; // Insufficient balance
        }
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to get the current balance
    public double getBalance() {
        return balance;
    }

    // Method to get the account number
    public String getAccountNumber() {
        return accountNumber;
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    // Constructor to initialize ATM with a BankAccount object
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // Method to display the ATM menu and handle transactions
    public void showMenu() {
        int choice;

        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Withdraw Money");
            System.out.println("2. Deposit Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // Validate input for menu selection
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.next(); // Clear the invalid input
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleWithdraw();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    handleCheckBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose between 1 and 4.");
            }
        } while (choice != 4);
    }

    // Method to handle withdrawal
    private void handleWithdraw() {
        System.out.print("Enter amount to withdraw: ");
        double withdrawAmount = scanner.nextDouble();

        // Validate withdrawal amount
        if (withdrawAmount <= 0) {
            System.out.println("Amount must be positive.");
        } else if (account.withdraw(withdrawAmount)) {
            System.out.println("Withdrawal successful! New balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient balance. Transaction failed.");
        }
    }

    // Method to handle deposit
    private void handleDeposit() {
        System.out.print("Enter amount to deposit: ");
        double depositAmount = scanner.nextDouble();

        // Validate deposit amount
        if (depositAmount <= 0) {
            System.out.println("Deposit amount must be positive.");
        } else {
            account.deposit(depositAmount);
            System.out.println("Deposit successful! New balance: " + account.getBalance());
        }
    }

    // Method to check the balance
    private void handleCheckBalance() {
        System.out.println("Your current balance for account " + account.getAccountNumber() + " is: " + account.getBalance());
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter a 5-digit account number
        String accountNumber;
        do {
            System.out.print("Please enter a 5-digit account number: ");
            accountNumber = scanner.nextLine();
        } while (accountNumber.length() != 5 || !accountNumber.matches("\\d{5}"));

        // Create a bank account with an initial balance of $1000 and the given account number
        BankAccount account = new BankAccount(1000.00, accountNumber);

        // Display the account number and balance
        System.out.println("Account Number: " + accountNumber + " | Current Balance: " + account.getBalance());

        // Create an ATM instance and associate it with the created bank account
        ATM atm = new ATM(account);
        
        // Show ATM menu for the user to interact with
        atm.showMenu();
    }
}
