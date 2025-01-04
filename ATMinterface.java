import java.util.Scanner;
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited $%.2f.%n", amount);
        } else {
            System.out.println("Invalid deposit amount. Please try again.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.printf("Successfully withdrew $%.2f.%n", amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance for withdrawal.");
            return false;
        } else {
            System.out.println("Invalid withdrawal amount. Please try again.");
            return false;
        }
    }
}
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- ATM Interface ---");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount to withdraw: ");
                    double amount = scanner.nextDouble();
                    account.withdraw(amount);
                }
                case 2 -> {
                    System.out.print("Enter amount to deposit: ");
                    double amount = scanner.nextDouble();
                    account.deposit(amount);
                }
                case 3 -> System.out.printf("Current balance: $%.2f%n", account.getBalance());
                case 4 -> System.out.println("Thank you for using the ATM. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
public class ATMinterface {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(500.00);
        ATM atm = new ATM(account);
        atm.showMenu();
    }
}
