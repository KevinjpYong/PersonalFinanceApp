package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import state.State;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoalTest {
    private User user;
    @BeforeEach
    public void init(){
        user = new User("Tester", 25);
    }

    @Test
    public void calAmountRemainingTest(){
        user.getSaving().setAmount(5000);
        Goal goal = new Goal("Car", 38000);
        user.setGoal(goal);
        assertEquals(33000,user.getGoal().calAmountRemaining(user));
    }

    @Test
    public void calAmountRemainingZeroTest(){
        user.getSaving().setAmount(38000);
        Goal goal = new Goal("Car", 38000);
        user.setGoal(goal);
        assertEquals(0,user.getGoal().calAmountRemaining(user));
    }

    @Test
    public void calAmountRemainingNegativeTest(){
        user.getSaving().setAmount(5000);
        Goal goal = new Goal("Car", 1000);
        user.setGoal(goal);
        assertEquals(-4000,user.getGoal().calAmountRemaining(user));
    }

    @Test
    public void estimateDurationRequiredRemainderZeroTest() throws ParseException {
        user.getSaving().setAmount(38000);
        Goal goal = new Goal("Car", 38000);
        user.setGoal(goal);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        String dateString = "03/2023";
        Date date = formatter.parse(dateString);
        Statement statement = new Statement(date);
        Income income = new Income("Salary", 6000);
        user.addStatement(statement);
        statement.addIncome(income);
        assertEquals(0, goal.estimateDurationRequired(user));
    }

    @Test
    public void estimateDurationRequiredNegativeAverageNetIncomeTest() throws ParseException {
        user.getSaving().setAmount(5000);
        Goal goal = new Goal("Car", 38000);
        user.setGoal(goal);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        String dateString = "03/2023";
        Date date = formatter.parse(dateString);
        Statement statement = new Statement(date);
        Expense expense = new Expense("Food", 1000);
        user.addStatement(statement);
        statement.addExpense(expense);
        assertEquals(-1, goal.estimateDurationRequired(user));
    }

    @Test
    public void estimateDurationRequiredTest() throws ParseException {
        user.getSaving().setAmount(5000);
        Goal goal = new Goal("Car", 38000);
        user.setGoal(goal);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        String dateString = "03/2023";
        Date date = formatter.parse(dateString);
        Statement statement = new Statement(date);
        Income income = new Income("Salary", 1000);
        user.addStatement(statement);
        statement.addIncome(income);
        assertEquals(33, goal.estimateDurationRequired(user));
    }

    @Test
    public void estimateDurationRequiredRoundUpTest() throws ParseException {
        user.getSaving().setAmount(5000);
        Goal goal = new Goal("Car", 38000);
        user.setGoal(goal);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        String dateString = "03/2023";
        Date date = formatter.parse(dateString);
        Statement statement = new Statement(date);
        Income income = new Income("Salary", 15000);
        user.addStatement(statement);
        statement.addIncome(income);
        assertEquals(3, goal.estimateDurationRequired(user));
    }

    @Test
    public void estimateDurationRequiredLessThanOneTest() throws ParseException {
        user.getSaving().setAmount(5000);
        Goal goal = new Goal("Car", 38000);
        user.setGoal(goal);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        String dateString = "03/2023";
        Date date = formatter.parse(dateString);
        Statement statement = new Statement(date);
        Income income = new Income("Salary", 40000);
        user.addStatement(statement);
        statement.addIncome(income);
        assertEquals(1, goal.estimateDurationRequired(user));
    }
}
