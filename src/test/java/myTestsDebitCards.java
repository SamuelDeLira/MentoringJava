import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import unosquare.mentoring.bankingClasses.*;
import unosquare.mentoring.logger.Log;

public class myTestsDebitCards {
    @Test
    public void usingDebitCards(){
        //DebitCard card1 = new DebitCard(); // It is not possible to create an instance/object of class "Debitcard", because it is abstract (abstract must be inherited)
        BbvaDebitCard card1 = new BbvaDebitCard("Juan", "Perez");
        Log.print("First Name is: " +card1.firstName);
        Log.print("Last Name is: " +card1.lastName);
        card1.checkBalance();
        card1.deposit(100);
        card1.checkBalance();
        card1.withdraw(50);
        card1.checkBalance();

        BbvaDebitCard card2 = new BbvaDebitCard("Pedro", "Infante");
        Log.print("First Name is: " +card2.firstName);
        Log.print("Last Name is: " +card2.lastName);
        card2.checkBalance();
        card2.deposit(100.95);
        card2.checkBalance();
        card2.withdraw(25);
        card2.checkBalance();

        BbvaDebitCard card3 = new BbvaDebitCard("Javier", "Solis", 123456.99);

        Log.print("Card 1 is: " +card1.cardNumber);
        Log.print("Card 2 is: " +card2.cardNumber);
        Log.print("Card 3 is: " +card3.cardNumber);

    }
    @AfterClass

    public void AfterClass(){
        Log.print("The number of cards is: " + BbvaDebitCard.howMayCreated);
    }

}
