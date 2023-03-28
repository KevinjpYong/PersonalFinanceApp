package model;

/**
 * Interface for Budgeting Expenses Calculation Strategy
 * **/
public interface BudgetingExpensesStrategy {

    double calcBudgetingExpenses(double totalCost, Liability loan, User user);
}
