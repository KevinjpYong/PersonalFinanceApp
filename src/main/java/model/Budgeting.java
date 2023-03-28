package model;

public class Budgeting {
    private BudgetingType type;
    private BudgetingExpensesStrategy budgetingExpensesStrategy;
    private double totalCost;

    private Liability loan;
    private User user;

    public Budgeting(BudgetingType type, double totalCost, Liability loan, User user){
        this.type = type;
        this.budgetingExpensesStrategy = new BudgetingExpenseStrategyFactory().getBudgetingExpensesStrategy(type);
        this.totalCost = totalCost;
        this.loan = loan;
        this.user = user;
    }

    public double calExtraExpenses(){
        return budgetingExpensesStrategy.calculateBudgetingExpenses(this.totalCost, this.loan, this.user);
    }

    public double calCurrentPayment(){
        return totalCost-loan.getAmount();
    }


    public double calInitialPaymentWithSaving(){
        return user.getSaving().getAmount() - calCurrentPayment();
    }

    public double calNetIncomeAfterExtraExpenses(){
        return user.getAverageNetIncome() - calExtraExpenses();
    }


}
