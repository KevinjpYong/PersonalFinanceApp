package state;

import model.BudgetingType;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BudgetingStateTest {
    @Test
    public void BudgetingHandleTet(){
        String type = "1\n";
        String totalCost = "10000\n";
        String loanAmount = "5000\n";
        String rates = "8\n";
        String duration = "25\n";
        String backToMenu = "1\n";
        String input = type + totalCost + loanAmount + rates + duration + backToMenu;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        User user = new User("Tester", 25);
        BudgetingState budgetingState = new BudgetingState();
        assertTrue(budgetingState.handle(user) instanceof SelectionState);
    }
}
