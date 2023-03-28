package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarBudgetingExpensesStrategyTest {

    @Test
    public void calcBudgetingExpensesTest(){
        User user = new User("Tester",25);
        Liability loan = new Liability("Car Loan", 10000,0.12,12);
        CarBudgetingExpensesStrategy strategy = new CarBudgetingExpensesStrategy();
        double totalCost = 10000;
        double insurance = 114.20;
        double actualExpense = loan.getMonthlyPayment()+insurance;
        assertEquals(actualExpense, strategy.calcBudgetingExpenses(totalCost,loan, user));
    }
    @Test
    public void calcBudgetingExpensesAge24Test(){
        User user = new User("Tester",24);
        Liability loan = new Liability("Car Loan", 10000,0.12,12);
        CarBudgetingExpensesStrategy strategy = new CarBudgetingExpensesStrategy();
        double totalCost = 10000;
        double insurance = 142.70;
        double actualExpense = loan.getMonthlyPayment()+insurance;
        assertEquals(actualExpense, strategy.calcBudgetingExpenses(totalCost,loan, user));
    }
    @Test
    public void calcBudgetingExpensesAgeZeroTest(){
        User user = new User("Tester",0);
        Liability loan = new Liability("Car Loan", 10000,0.12,12);
        CarBudgetingExpensesStrategy strategy = new CarBudgetingExpensesStrategy();
        double totalCost = 10000;
        double insurance = 142.70;
        double actualExpense = loan.getMonthlyPayment()+insurance;
        assertEquals(actualExpense, strategy.calcBudgetingExpenses(totalCost,loan, user));
    }
}
