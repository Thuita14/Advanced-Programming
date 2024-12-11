package Lecture4_interfaces_abstract_classes;

//import org.jetbrains.annotations.NotNull;
import java.util. UUID;
import java.util.Calendar;


public interface TransactionInterface{
    double getAmount();
    Calendar getDate();
    String getTransactionID();
    void printTransactionDetails();
    void apply(BankAccount ba);
}

public abstract class BaseTransaction implements TransactionInterface{
    private double amount;
    private Calendar date;
    private String transactionID;

    public BaseTransaction(double amount, Calendar date){
        this.amount = amount;
        this.date = date;
       // int uniq = (int) (Math.random() * 1000000);
        this.transactionID = UUID.randomUUID().toString();//Generates a unique ID
}

@Override
public double getAmount(){
    return amount;
}

@Override
public Calendar getDate(){
    return date;
}
@Override
public String getTransactionID(){
    return transactionID;
}
@Override
public void printTransactionDetails(){
    System.out.println("Transaction ID: " + transactionID);
    System.out.println("Amount: " + amount);
    System.out.println("Date: " + date.getTime());
}

@Override
public void apply(BankAccount ba){
    System.out.println("Applying transaction to bank account...");
}
}

public class BankAccount{
    private double balance;

    public BankAccount(double initialBalance){
        this.balance = initialBalance;
    }
    public double getBalance(){
        return balance;
    }
    public void deposit(double amount){
        balance += amount;
    }
    public void withdraw(double amount){
        if(amount <= balance){
            balance -= amount;
            }else{
        System.out.println("Insufficient funds!");
        }
    }
    @Override
    public String toString(){
        return "BankAccount balance: " + balance;
    }
}

public class DepositTransaction extends BaseTransaction{
    public DepositTransaction(double amount, Calendar date){
        super(amount, date);
    }

    @Override
    public void apply(BankAccount ba){
        ba.deposit(getAmount());
        System.out.println("Deposit of" + getAmount() + "applied to bank account.");
    }
}

public class WithdrawalTransaction extends BaseTransaction{
    public WithdrawalTransaction(double amount, Calendar date){
        super(amount, date);
    }

    @Override
    public void apply(BankAccount ba){
        ba.withdraw(getAmount());
        System.out.println("Withdraw of " + getAmount() + "applied to bank account.");
    }
}

public class TransactionTest{

    public static void main(String[]args){
        BankAccount account = new BankAccount(2000);

        Calendar depositDate = Calendar.getInstance();
        DepositTransaction deposit = new DepositTransaction(1000, depositDate);

        Calendar withdrawalDate = Calendar.getInstance();
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(500, withdrawalDate);

        //Apply Transactions
        System.out.println("Before transactions: " + account);
        deposit.apply(account);
        withdrawal.apply(account);
        System.out.println("After transactions: " + account);

        //Print transaction details
        deposit.printTransactionDetails();
        withdrawal.printTransactionDetails();
    }
}