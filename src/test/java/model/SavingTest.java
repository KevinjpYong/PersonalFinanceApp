package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingTest {

    @Test
    public void savingIncomeUpdateTest(){
        Saving saving = new Saving(0);
        Income income = new Income("Salary", 6000);
        saving.update(income);

        assertEquals(5000,saving.amount);
    }

    @Test
    public void savingIncomeUpdateTillPositiveTest(){
        Saving saving = new Saving(-100);
        Income income = new Income("Salary", 6000);
        saving.update(income);

        assertEquals(5900,saving.amount);
    }

    @Test
    public void savingExpenseUpdateTest(){
        Saving saving = new Saving(5000);
        Expense expense = new Expense("food", 100);
        saving.update(expense);

        assertEquals(4900,saving.amount);
    }

    @Test
    public void savingExpenseUpdateTillNegativeTest(){
        Saving saving = new Saving(50);
        Expense expense = new Expense("food", 100);
        saving.update(expense);

        assertEquals(-50,saving.amount);
    }
}
