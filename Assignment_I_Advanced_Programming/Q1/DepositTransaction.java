package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class DepositTrasaction extends BaseTransaction {
    public DepositTrasaction(double amount, Calendar date){
        super(amount, date);
    }
    private boolean checkDepositAmount(double  amount){
        if (amt < 0){
           return false;
        }
        else{
            return  true;
        }
    }

    // Method to print a transaction receipt or details
    public void printTransactionDetails(){
        System.out.println("Deposit Transaction: "+this.toString());
    }

    public void apply(BankAccount ba){
        double curr_balance = ba.getBalance();
        double new_balance = curr_balance + getAmount();
        ba.setBalance(new_balance);
    }
}