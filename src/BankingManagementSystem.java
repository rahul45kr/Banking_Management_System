// BankAccount class to represent individual accounts
class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private String accountType;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + " successfully");
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn $" + amount + " successfully");
        } else {
            System.out.println("Insufficient balance or invalid amount");
        }
    }

    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: $" + balance);
    }
}

// Bank class to manage multiple accounts
class Bank {
    private BankAccount[] accounts;
    private int totalAccounts;
    private static final int MAX_ACCOUNTS = 100;

    public Bank() {
        accounts = new BankAccount[MAX_ACCOUNTS];
        totalAccounts = 0;
    }

    public void addAccount(String accountNumber, String accountHolder, double initialBalance, String accountType) {
        if (totalAccounts < MAX_ACCOUNTS) {
            accounts[totalAccounts] = new BankAccount(accountNumber, accountHolder, initialBalance, accountType);
            totalAccounts++;
            System.out.println("Account created successfully");
        } else {
            System.out.println("Bank is at maximum capacity");
        }
    }

    public BankAccount findAccount(String accountNumber) {
        for (int i = 0; i < totalAccounts; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null;
    }
}

// Main class with banking system implementation
public class BankingManagementSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        while (true) {
            System.out.println("\n=== Banking Management System ===");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    String accNum = scanner.nextLine();
                    System.out.print("Enter Account Holder Name: ");
                    String holder = scanner.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double initialBal = scanner.nextDouble();
                    System.out.print("Enter Account Type (Savings/Checking): ");
                    String type = scanner.next();
                    bank.addAccount(accNum, holder, initialBal, type);
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    String depositAcc = scanner.nextLine();
                    BankAccount depositAccount = bank.findAccount(depositAcc);
                    if (depositAccount != null) {
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        depositAccount.deposit(depositAmount);
                    } else {
                        System.out.println("Account not found");
                    }
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    String withdrawAcc = scanner.nextLine();
                    BankAccount withdrawAccount = bank.findAccount(withdrawAcc);
                    if (withdrawAccount != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        withdrawAccount.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Account not found");
                    }
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    String checkAcc = scanner.nextLine();
                    BankAccount checkAccount = bank.findAccount(checkAcc);
                    if (checkAccount != null) {
                        checkAccount.displayAccountInfo();
                    } else {
                        System.out.println("Account not found");
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using Banking Management System");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
