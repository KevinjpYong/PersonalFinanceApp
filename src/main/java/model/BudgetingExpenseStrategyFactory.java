package model;

/**
 * Factory to create budgeting expense calculation strategy
 * **/
public class BudgetingExpenseStrategyFactory {

    // get calculation strategy based on budgeting type
    public BudgetingExpensesStrategy getBudgetingExpensesStrategy(BudgetingType type){
        switch (type){
            case CAR_PURCHASE:
                return new CarBudgetingExpensesStrategy();
            case HOUSE_PURCHASE:
                return new HouseBudgetingExpensesStrategy();
            default:
                throw new IllegalArgumentException("Invalid state type: " + type.name());
        }
    }
}
