package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HouseBudgetingExpensesStrategyTest {

    @Test
    public void calcBudgetingExpensesTest(){
        User user = new User("Tester",25);
        Liability loan = new Liability("House Loan", 400000,0.0514,360);
        HouseBudgetingExpensesStrategy strategy = new HouseBudgetingExpensesStrategy();
        double totalCost = 800000;
        double actualExpense = loan.getMonthlyPayment();
        assertEquals(actualExpense, strategy.calcBudgetingExpenses(totalCost,loan, user));
    }

    @Test
    public void calcBudgetingExpensesWithLMiTest(){
        User user = new User("Tester",25);
        Liability loan = new Liability("House Loan", 800000,0.0514,360);
        HouseBudgetingExpensesStrategy strategy = new HouseBudgetingExpensesStrategy();
        double totalCost = 1000000;
        double lmiMonthlyPayment = new Liability("LMI", 8000,0.01,360).getMonthlyPayment();
        double actualExpense = loan.getMonthlyPayment()+lmiMonthlyPayment;
        assertEquals(actualExpense, strategy.calcBudgetingExpenses(totalCost,loan, user));
    }
}
