package model;

/**
 * Budgeting Model
 * **/

public class Budgeting {
    private BudgetingType type;
    private BudgetingExpensesStrategy budgetingExpensesStrategy;
    private double totalCost;

    private Liability loan;
    private User user;

    // The constructor
    public Budgeting(BudgetingType type, double totalCost, Liability loan, User user){
        this.type = type;
        this.budgetingExpensesStrategy = new BudgetingExpensesStrategyFactory().getBudgetingExpensesStrategy(type);
        this.totalCost = totalCost;
        this.loan = loan;
        this.user = user;
    }

    // calculate the extra expenses from the budgeting using different strategy
    public double calcExtraExpenses(){
        return budgetingExpensesStrategy.calcBudgetingExpenses(this.totalCost, this.loan, this.user);
    }

    // calculate the required amount to pay currently
    public double calcCurrentPayment(){
        return totalCost-loan.getAmount();
    }

    // calculate the difference of user's saving and current amount to pay
    public double calcInitialPaymentWithSaving(){
        return user.getSaving().getAmount() - calcCurrentPayment();
    }

    // calculate the difference of user's average net income and extra monthly expenses
    public double calcNetIncomeAfterExtraExpenses(){
        return user.getAverageNetIncome() - calcExtraExpenses();
    }


}
