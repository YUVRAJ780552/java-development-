import java.util.Scanner;

// Class to represent the user's bank account
class BankAccount {
    private double balance;

    // Constructor to initialize the account balance
    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            System.out.println("Initial balance cannot be negative. Setting balance to 0.");
            this.balance = 0;
        }
    }

    // Method to get the current balance
    public double getBalance() {
        return balance;
    }

    // Method to deposit an amount into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be greater than zero.");
        }
    }

    // Method to withdraw an amount from the account
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew: $" + amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient funds. Withdrawal failed.");
        } else {
            System.out.println("Withdrawal amount must be greater than zero.");
        }
        return false;
    }
}

// Class to represent the ATM machine
class ATM {
    private final BankAccount account;

    // Constructor to link the ATM with a bank account
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Method to display the ATM menu and handle user interaction
    public void displayMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            
            do {
                System.out.println("\n===== ATM Menu =====");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    scanner.next();
                }
                choice = scanner.nextInt();
                
                switch (choice) {
                    case 1 -> checkBalance();
                    case 2 -> deposit(scanner);
                    case 3 -> withdraw(scanner);
                    case 4 -> System.out.println("Thank you for using the ATM. Goodbye!");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 4);
        }
    }

    // Method to check and display the current balance
    private void checkBalance() {
        System.out.println("Your current balance is: $" + account.getBalance());
    }

    // Method to handle deposits
    private void deposit(Scanner scanner) {
        System.out.print("Enter the amount to deposit: $");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.next();
        }
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    // Method to handle withdrawals
    private void withdraw(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: $");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.next();
        }
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }
}

// Main class to run the program
@SuppressWarnings("unused")
class main {
     void mainmain(String[] args) {
        // Create a bank account with an initial balance of $500
        BankAccount userAccount = new BankAccount(500);

        // Create an ATM linked to the user's bank account
        ATM atm = new ATM(userAccount);

        // Display the ATM menu
        atm.displayMenu();
    }
}
