package unosquare.mentoring.bankingClasses;

import unosquare.mentoring.logger.Log;

public abstract class DebitCard {
    /*private String firstName;
    private String lastName;
    private int expirationMonth;
    private int expirationYear;
    private String cardNumber;
    private double balance;*/

    public String firstName;
    public String lastName;
    public int expirationMonth;
    public int expirationYear;
    public String cardNumber;
    public double balance;

    public void deposit(double amount) {
        //balance = balance + amount;
        balance += amount;  //This is the short form for the above line

    }

    public void withdraw(double amount) {
        //balance = balance - amount;
        balance -= amount;  //This is the short form for the above line

    }

    public void checkBalance(){
        Log.print("The current balance is: " +  this.balance);
    }
}
