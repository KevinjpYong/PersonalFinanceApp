package model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatementTest {

    @Test
    public void getNetIncomeTest(){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        String dateString = "03/2023";
        try {
            Date date = formatter.parse(dateString);
            Statement statement = new Statement(date);

            Income income = new Income("Salary", 6000);
            Expense expense = new Expense("Car Loan Repayment", 1000);

            statement.addIncome(income);
            statement.addExpense(expense);

            assertEquals(5000, statement.getNetIncome());

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void getNetIncomeNegativeTest(){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        String dateString = "03/2023";
        try {
            Date date = formatter.parse(dateString);
            Statement statement = new Statement(date);

            Income income = new Income("Salary", 6000);
            Expense expense = new Expense("Car Loan Repayment", 7000);

            statement.addIncome(income);
            statement.addExpense(expense);

            assertEquals(-1000, statement.getNetIncome());

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
