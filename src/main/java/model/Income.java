package model;

/**
 * Model of Income
 * Inherit CashFlow class
 * **/
public class Income extends CashFlow {
    public Income(String name, double amount){
        this.name = name;
        this.amount = amount;
    }
}
