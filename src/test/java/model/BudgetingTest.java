package model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetingTest {

    @Test
    public void calcCurrentPaymentTest(){
        User user = new User("Tester",25);
        Liability loan = new Liability("Car Loan", 10000, 0.1, 36);
        double totalCost = 38000;
        Budgeting budgeting = new Budgeting(BudgetingType.CAR_PURCHASE, totalCost, loan, user);

        double actualInitialPayment = 28000;

        assertEquals(actualInitialPayment, budgeting.calCurrentPayment());
    }

    @Test
    public void calcCurrentPaymentZeroTest(){
        User user = new User("Tester",25);
        Liability loan = new Liability("Car Loan", 38000, 0.1, 36);
        double totalCost = 38000;
        Budgeting budgeting = new Budgeting(BudgetingType.CAR_PURCHASE, totalCost, loan, user);

        double actualInitialPayment = 0;

        assertEquals(actualInitialPayment, budgeting.calCurrentPayment());
    }

    @Test
    public void calcCurrentPaymentZeroLoanTest(){
        User user = new User("Tester",25);
        Liability loan = new Liability("Car Loan", 0, 0.1, 36);
        double totalCost = 38000;
        Budgeting budgeting = new Budgeting(BudgetingType.CAR_PURCHASE, totalCost, loan, user);

        double actualInitialPayment = 38000;

        assertEquals(actualInitialPayment, budgeting.calCurrentPayment());
    }

    @Test
    public void calcExtraExpenseTest(){
        User user = new User("Tester",25);
        Liability loan = new Liability("Car Loan", 10000, 0.12, 12);
        double totalCost = 28000;
        Budgeting budgeting = new Budgeting(BudgetingType.CAR_PURCHASE, totalCost, loan, user);

        double insuranceUnder25 = 114.20;

        double actualExtraExpenses = loan.getMonthlyPayment() + insuranceUnder25;

        assertEquals(actualExtraExpenses, budgeting.calExtraExpenses());
    }

    @Test
    public void calcInitialPaymentSavingTest(){
        User user = new User("Tester",25);
        user.getSaving().setAmount(20000);
        Liability loan = new Liability("Car Loan", 10000, 0.12, 12);
        double totalCost = 28000;
        Budgeting budgeting = new Budgeting(BudgetingType.CAR_PURCHASE, totalCost, loan, user);


        double actualDifference = 2000;

        assertEquals(actualDifference, budgeting.calInitialPaymentWithSaving());
    }

    @Test
    public void calcNetIncomeExpenseTest() throws ParseException {
        User user = new User("Tester",25);

        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        String dateString1 = "03/2023";
        Date date1 = formatter.parse(dateString1);
        Statement statement1 = new Statement(date1);

        statement1.addIncome(new Income("Salary", 6000));

        String dateString2 = "04/2023";
        Date date2 = formatter.parse(dateString2);
        Statement statement2 = new Statement(date2);

        statement2.addIncome(new Income("Salary", 8000));

        user.addStatement(statement1);
        user.addStatement(statement2);


        Liability loan = new Liability("Car Loan", 10000, 0.12, 12);
        double totalCost = 28000;
        Budgeting budgeting = new Budgeting(BudgetingType.CAR_PURCHASE, totalCost, loan, user);

        double actualExtraExpenses = 7000-1002.69;

        assertEquals(actualExtraExpenses, budgeting.calNetIncomeAfterExtraExpenses());
    }
}
