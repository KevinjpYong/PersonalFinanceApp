package state;

import model.Goal;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoalStateTest {

    @Test
    public void addGoalTest(){
        String displayStatementsOption = "1\n";
        String goalName = "Saving\n";
        String goalAmount = "10000\n";
        String input = displayStatementsOption +
                goalName +
                goalAmount;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        User user = new User("Tester", 25);
        GoalState goalState = new GoalState();
        assertTrue(goalState.handle(user) instanceof GoalState);
        assertEquals("Saving", user.getGoal().getName());
        assertEquals(10000, user.getGoal().getAmount());
    }

    @Test
    public void addGoalGivenWrongAmountInputTypeTest(){
        String displayStatementsOption = "1\n";
        String goalName = "Saving\n";
        String wrongAmount = "abcde\n";
        String goalAmount = "10000\n";
        String input = displayStatementsOption +
                goalName +
                wrongAmount +
                goalAmount;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        User user = new User("Tester", 25);
        GoalState goalState = new GoalState();
        assertTrue(goalState.handle(user) instanceof GoalState);
        assertEquals("Saving", user.getGoal().getName());
        assertEquals(10000, user.getGoal().getAmount());
    }

    @Test
    public void addGoalGivenNegativeAmountTest(){
        String displayStatementsOption = "1\n";
        String goalName = "Saving\n";
        String wrongAmount = "-10000\n";
        String goalAmount = "10000\n";
        String input = displayStatementsOption +
                goalName +
                wrongAmount +
                goalAmount;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        User user = new User("Tester", 25);
        GoalState goalState = new GoalState();
        assertTrue(goalState.handle(user) instanceof GoalState);
        assertEquals("Saving", user.getGoal().getName());
        assertEquals(10000, user.getGoal().getAmount());
    }
}
