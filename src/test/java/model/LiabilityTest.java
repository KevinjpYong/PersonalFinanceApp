package model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LiabilityTest {

    private Liability liability;

    @BeforeEach
    public void init(){
        String liabilityName = "House Loan";
        double amount = 400000;
        double rates = 0.0514;
        int duration = 360;

        liability = new Liability(liabilityName, amount, rates, duration);
    }

    @Test
    public void calculateRepaymentFromLiability(){
        assertEquals(2181.64, liability.getMonthlyPayment());
    }

    @Test
    public void createExpenseFromLiabilityTest(){
        Expense expense = liability.createExpense();
        assertEquals("House Loan Repayment", expense.getName());
        assertEquals(2181.64, expense.getAmount());
    }

}
