package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class User {
    String name;
    int age;
    Saving saving;
    Goal goal;
    ArrayList<Liability> liabilities;
    HashMap<Date, Statement> statements;

    public User(String name, int age){
        this.name = name;
        this.age = age;
        this.saving = new Saving(0);
        this.liabilities = new ArrayList<>();
        this.statements = new HashMap<>();
    }

    public void setSaving(double amount){
        saving.setAmount(amount);
    }

    public Saving getSaving(){
        return this.saving;
    }

    public void addLiabilities(Liability liability){
        this.liabilities.add(liability);
    }

    public ArrayList<Liability> getLiabilities(){
        return this.liabilities;
    }
    public void addStatement(Statement statement){
        this.statements.put(statement.getDate(),statement);
    }

    public HashMap<Date,Statement> getStatements(){ return this.statements;}

    public ArrayList<Expense> getLiabilitiesRepayment(){
        ArrayList<Expense> expenses = new ArrayList<>();
        for(Liability liability: liabilities){
            expenses.add(liability.createExpense());
        }
        return  expenses;
    }

    public Statement getStatement(Date date){
        return this.statements.get(date);
    }
}
