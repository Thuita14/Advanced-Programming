import java.util.Calendar;

public class WithdrawalTransaction extends BaseTransaction {
    private boolean reversed = false;  // Track if the transaction has been reversed
    private BankAccount appliedAccount; // Keep track of the account this transaction was applied to

    public WithdrawalTransaction(double amount, Calendar date) {
        super(amount, date);
    }

    @Override
    public void apply(BankAccount ba) {
        if (getAmount() <= ba.getBalance()) {
            ba.withdraw(getAmount());
            appliedAccount = ba; // Store the account for potential reversal
            System.out.println("Withdrawal of " + getAmount() + " applied.");
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }

    public boolean reverse() {
        if (reversed) {
            System.out.println("Transaction has already been reversed.");
            return false;
        }

        if (appliedAccount != null) {
            appliedAccount.deposit(getAmount());
            reversed = true;
            System.out.println("Withdrawal transaction reversed. Amount " + getAmount() + " restored.");
            return true;
        } else {
            System.out.println("Transaction was not applied to any account; cannot reverse.");
            return false;
        }
    }

public class TransactionTest {
    public static void main(String[] args) {
        // Create a bank account with an initial balance
        BankAccount account = new BankAccount(5000);

        // Create a withdrawal transaction
        Calendar withdrawalDate = Calendar.getInstance();
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(1500, withdrawalDate);

        // Apply withdrawal transaction
        System.out.println("Before transaction: " + account);
        withdrawal.apply(account);
        System.out.println("After withdrawal: " + account);

        // Reverse the withdrawal
        boolean success = withdrawal.reverse();
        if (success) {
            System.out.println("After reversal: " + account);
        }

        // Attempt to reverse again
        withdrawal.reverse();
    }
}
}