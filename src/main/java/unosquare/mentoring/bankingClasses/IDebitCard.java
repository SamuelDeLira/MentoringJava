package unosquare.mentoring.bankingClasses;

public interface IDebitCard {
    void withdraw(double amount);
    void deposit(double amount);
    void checkBalance();
}
