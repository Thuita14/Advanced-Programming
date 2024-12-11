package Lecture4_interfaces_abstract_classes;
import org.jetbrains.annotations.NotNull;
import java.util.Calendar;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

public class WithdrawalTransaction extends BaseTransaction {
    private boolean reversed = false;
    private BankAccount appliedAccount;
    private double remainingAmount = 0.0; // Keeps track of the amount not withdrawn

    public WithdrawalTransaction(double amount, Calendar date) {
        super(amount, date);
    }

    @Override
    public void apply(BankAccount ba) throws InsufficientFundsException {
        if (getAmount() > ba.getBalance()) {
            throw new InsufficientFundsException("Insufficient funds. Transaction failed.");
        } else if (ba.getBalance() <= 0) {
            throw new InsufficientFundsException("Account balance is zero or negative. Cannot proceed.");
        } else {
            ba.withdraw(getAmount());
            appliedAccount = ba;
            System.out.println("Withdrawal of " + getAmount() + " applied.");
        }
    }

    // Overloaded apply() method
    public void apply(BankAccount ba, boolean partialWithdrawal) {
        try {
            if (partialWithdrawal && ba.getBalance() > 0 && ba.getBalance() < getAmount()) {
                remainingAmount = getAmount() - ba.getBalance();
                System.out.println("Partial withdrawal applied. Remaining amount: " + remainingAmount);
                ba.withdraw(ba.getBalance());
                appliedAccount = ba;
            } else {
                apply(ba); // Call the original apply method
            }
        } catch (InsufficientFundsException e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            System.out.println("Transaction process completed.");
        }
    }

    public boolean reverse() {
        if (reversed) {
            System.out.println("Transaction has already been reversed.");
            return false;
        }

        if (appliedAccount != null) {
            appliedAccount.deposit(getAmount() - remainingAmount);
            reversed = true;
            System.out.println("Withdrawal transaction reversed. Amount " + (getAmount() - remainingAmount) + " restored.");
            return true;
        } else {
            System.out.println("Transaction was not applied to any account; cannot reverse.");
            return false;
        }
    }
}
