package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        // Create a BankAccount with an initial balance
        BankAccount account = new BankAccount(5000);

        // Create a DepositTransaction
        Calendar depositDate = Calendar.getInstance();
        DepositTransaction deposit = new DepositTransaction(2000, depositDate);

        // Create a WithdrawalTransaction
        Calendar withdrawalDate = Calendar.getInstance();
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(3000, withdrawalDate);

        // Test DepositTransaction (Directly)
        System.out.println("Testing DepositTransaction:");
        System.out.println("Before deposit: " + account);
        deposit.apply(account);
        System.out.println("After deposit: " + account);
        deposit.printTransactionDetails();

        // Test WithdrawalTransaction (Directly)
        System.out.println("\nTesting WithdrawalTransaction:");
        System.out.println("Before withdrawal: " + account);
        try {
            withdrawal.apply(account);
        } catch (InsufficientFundsException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
        System.out.println("After withdrawal: " + account);
        withdrawal.printTransactionDetails();

        // Test BaseTransaction behavior (Type casting)
        System.out.println("\nTesting BaseTransaction behavior using type casting:");
        BaseTransaction baseDeposit = (BaseTransaction) deposit; // Upcasting
        BaseTransaction baseWithdrawal = (BaseTransaction) withdrawal; // Upcasting

        // Using BaseTransaction references
        System.out.println("Testing apply() on BaseTransaction reference:");
        System.out.println("Before base deposit: " + account);
        baseDeposit.apply(account); // Polymorphic call
        System.out.println("After base deposit: " + account);

        System.out.println("Before base withdrawal: " + account);
        try {
            baseWithdrawal.apply(account); // Polymorphic call
        } catch (InsufficientFundsException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
        System.out.println("After base withdrawal: " + account);

        // Reversal Test
        System.out.println("\nTesting reversal functionality:");
        withdrawal.reverse();
        System.out.println("After reversal: " + account);
    }
}

