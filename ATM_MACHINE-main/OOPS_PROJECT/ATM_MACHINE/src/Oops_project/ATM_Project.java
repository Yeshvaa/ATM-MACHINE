package Oops_project;

	import java.util.HashMap;
	import java.util.Scanner;

	// Account class
	class Account {
	    private String accountNumber;
	    private String pin;
	    private double balance;

	    public Account(String accountNumber, String pin, double balance) {
	        this.accountNumber = accountNumber;
	        this.pin = pin;
	        this.balance = balance;
	    }

	    public String getAccountNumber() {
	        return accountNumber;
	    }

	    public String getPin() {
	        return pin;
	    }

	    public void setPin(String pin) {
	        this.pin = pin;
	    }

	    public double getBalance() {
	        return balance;
	    }

	    public void setBalance(double balance) {
	        this.balance = balance;
	    }

	    @Override
	    public String toString() {
	        return "Account Number: " + accountNumber+"XXX" + ", Balance: $" + balance;
	    }
	}

	// Bank class
	class Bank {
	    private HashMap<String, Account> accounts = new HashMap<>();

	    // Create a new account
	    public boolean createAccount(String accountNumber, String pin, double initialBalance) {
	        if (accounts.containsKey(accountNumber)) {
	            System.out.println("Account already exists with this account number.");
	            return false;
	        }
	        accounts.put(accountNumber, new Account(accountNumber, pin, initialBalance));
	        System.out.println("Account created successfully!");
	        return true;
	    }

	    // Read account details
	    public Account readAccount(String accountNumber) {
	        return accounts.get(accountNumber);
	    }

	    // Update account PIN or balance
	    public boolean updateAccount(String accountNumber, String newPin, Double newBalance) {
	        Account account = accounts.get(accountNumber);
	        if (account != null) {
	            if (newPin != null) {
	                account.setPin(newPin);
	                System.out.println("PIN updated successfully.");
	            }
	            if (newBalance != null) {
	                account.setBalance(newBalance);
	                System.out.println("Balance updated successfully.");
	            }
	            return true;
	        }
	        System.out.println("Account not found.");
	        return false;
	    }

	    // Delete an account
	    public boolean deleteAccount(String accountNumber) {
	        if (accounts.remove(accountNumber) != null) {
	            System.out.println("Account deleted successfully.");
	            return true;
	        }
	        System.out.println("Account not found.");
	        return false;
	    }

	    // Display all accounts
	    public void displayAllAccounts() {
	        if (accounts.isEmpty()) {
	            System.out.println("No accounts available.");
	            return;
	        }
	        for (Account account : accounts.values()) {
	            System.out.println(account);
	        }
	    }
	}

	// Main class
	public class ATM_Project {
	    public static void main(String[] args) {
	        Bank bank = new Bank();
	        Scanner scanner = new Scanner(System.in);
	        int choice;

	        do {
	            System.out.println("\n--- ATM Management System ---");
	            System.out.println("1. Create Account");
	            System.out.println("2. Read Account Details");
	            System.out.println("3. Update Account");
	            System.out.println("4. Delete Account");
	            System.out.println("5. Display All Accounts");
	            System.out.println("6. Exit");
	            System.out.print("Enter your choice: ");
	            choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (choice) {
	                case 1: // Create Account
	                    System.out.print("Enter account number: ");
	                    String accNum = scanner.nextLine();
	                    System.out.print("Enter PIN: ");
	                    String pin = scanner.nextLine();
	                    System.out.print("Enter initial balance: ");
	                    double balance = scanner.nextDouble();
	                    bank.createAccount(accNum, pin, balance);
	                    break;

	                case 2: // Read Account Details
	                    System.out.print("Enter account number to read: ");
	                    String readAccNum = scanner.nextLine();
	                    Account account = bank.readAccount(readAccNum);
	                    if (account != null) {
	                        System.out.println("Account Details: " + account);
	                    } else {
	                        System.out.println("Account not found.");
	                    }
	                    break;

	                case 3: // Update Account
	                    System.out.print("Enter account number to update: ");
	                    String updateAccNum = scanner.nextLine();
	                    System.out.print("Enter new PIN (or press Enter to skip): ");
	                    String newPin = scanner.nextLine();
	                    newPin = newPin.isEmpty() ? null : newPin;
	                    System.out.print("Enter new balance (or -1 to skip): ");
	                    double newBalance = scanner.nextDouble();
	                    newBalance = (newBalance < 0) ? null : newBalance;
	                    bank.updateAccount(updateAccNum, newPin, newBalance);
	                    break;

	                case 4: // Delete Account
	                    System.out.print("Enter account number to delete: ");
	                    String delAccNum = scanner.nextLine();
	                    bank.deleteAccount(delAccNum);
	                    break;

	                case 5: // Display All Accounts
	                    bank.displayAllAccounts();
	                    break;

	                case 6: // Exit
	                    System.out.println("Exiting system. Thank you!");
	                    break;

	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        } while (choice != 6);

	        scanner.close();
	    }
	}

