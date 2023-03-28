package model;

/**
 * Model of Expense
 * Inherit CashFlow class
 * **/
public class Expense extends CashFlow {

    public Expense(String name, double amount){
        this.name = name;
        this.amount = amount;
    }

}
