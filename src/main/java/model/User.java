package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * model for user
 * **/

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
        this.goal = null;
    }


    public Saving getSaving(){
        return this.saving;
    }

    public int getAge(){
        return this.age;
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

    // create expenses from all liabilities
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

    public void setGoal(Goal goal){
        this.goal = goal;
    }

    public Goal getGoal(){
        return this.goal;
    }

    // calculate net income from historical monthly statements
    public double getAverageNetIncome(){
        double averageNetIncome = 0;
        for(Statement statement: this.statements.values()){
            averageNetIncome += statement.getNetIncome();
        }

        if(!this.statements.isEmpty()){
            return averageNetIncome/this.statements.size();
        }else{
            return 0;
        }

    }
}
