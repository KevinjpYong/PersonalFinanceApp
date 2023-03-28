package model;

public interface BudgetingExpensesStrategy {

    double calculateBudgetingExpenses(double totalCost, Liability loan, User user);
}
