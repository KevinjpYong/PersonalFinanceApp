package model;
import java.util.ArrayList;
import java.util.Date;

/**
 * model for Statement
 * **/

public class Statement implements SubjectBase{
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

    // register observer
    public void registerObserver(ObserverBase observer){
        this.observers.add(observer);
    }


    public void addIncome(Income income){
        this.incomes.add(income);
        // notify observer (saving) to update when there is a new income
        notifyObserver(income);
    }

    public void addExpense(Expense expense){
        this.expenses.add(expense);
        // notify observer (saving) to update when there is a new expense
        notifyObserver(expense);
    }

    public void addExpenses(ArrayList<Expense> expenses){
        for(Expense expense: expenses){
            this.expenses.add(expense);
            // notify observer (saving) to update when there is a new expense
            notifyObserver(expense);
        }
    }

    // notify observer to update
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

    // calculate the monthly net income
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
