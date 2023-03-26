package model;

import java.util.ArrayList;
import java.util.Date;

public class Statement {
    Date date;
    ArrayList<Income> incomes;
    ArrayList<Expense> expenses;

    ArrayList<ObserverBase> observers;

    public Statement(Date date){
        this.date = date;
        this.incomes = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public void registerObserver(ObserverBase observer){
        this.observers.add(observer);
    }

    public void addIncome(Income income){
        this.incomes.add(income);
        notifyObserver(income);
    }

    public void addExpense(Expense expense){
        this.expenses.add(expense);
        notifyObserver(expense);
    }

    public void addExpenses(ArrayList<Expense> expenses){
        for(Expense expense: expenses){
            this.expenses.add(expense);
            notifyObserver(expense);
        }
    }

    public void notifyObserver(CashFlow cashflow){
        for(ObserverBase observer: this.observers){
            observer.update(cashflow);
        }
    }


    public Date getDate() {
        return date;
    }

    public ArrayList<Income> getIncomes() {
        return incomes;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public double getNetIncome(){
        double net = 0;
        for(Income income: this.incomes){
            net += income.getAmount();
        }
        for(Expense expense: this.expenses){
            net -= expense.getAmount();
        }
        return net;
    }
}
