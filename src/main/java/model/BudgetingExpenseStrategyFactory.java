package model;

public class BudgetingExpenseStrategyFactory {
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
