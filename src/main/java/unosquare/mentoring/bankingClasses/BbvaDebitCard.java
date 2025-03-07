package unosquare.mentoring.bankingClasses;

import unosquare.mentoring.logger.Log;

import java.text.NumberFormat;

public class BbvaDebitCard extends DebitCard implements IDebitCard {

    public static int howMayCreated = 0;

    public BbvaDebitCard(String firstName, String lastName){
        this.firstName = firstName; //"this" is necessary to tell Java that I am going to assign the variable from this class with the value received between ()
        this.lastName = lastName;
        howMayCreated ++;

        String lastFour = String.format("%04d", howMayCreated); // %04d means displayed as 4 digits (0004, 0025, 0123, 5678)
       // String lastFour = String.valueOf(howMayCreated);
        this.cardNumber = "415219209876" +lastFour;
    }

    public BbvaDebitCard(String firstName, String lastName, double initialBalance){

        //Method Constructor chaining
        this(firstName, lastName); //Search for a constructor that is already receives 2 strings (Line 7)

        //Before proceeding I need to review that the initial balance is positive
        if(initialBalance < 0){
            throw new RuntimeException("The balance must be positive: " + String.valueOf(initialBalance));
        }

        this.balance = initialBalance;

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        String formattedAmount = currencyFormat.format(this.balance);

        Log.print("Initial balance is: " +formattedAmount);
    }

    @Override
    public void checkBalance() {
        //super.checkBalance(); //This will call the original implementation (located inside abstract class DebitCard)
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        String formattedAmount = currencyFormat.format(this.balance);

        Log.print("Current balance is: " +formattedAmount);
    }
}
