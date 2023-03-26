package state;

import model.Expense;
import model.Income;
import org.junit.jupiter.api.Test;

public class StatementStateTest {

    @Test
    public void displayStatementTest(){
        Income income =  new Income("salary", 6000);
        Expense expense = new Expense("food", 25);

    }
}
