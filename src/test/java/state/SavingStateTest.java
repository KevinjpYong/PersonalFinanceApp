package state;

import model.User;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class SavingStateTest {

    @Test
    public void handleReturnToSelectionMenu(){
        String choice = "2\n";
        String input = choice;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = new User("Tester", 25);
        SavingState savingState = new SavingState();
        assertTrue(savingState.handle(user) instanceof SelectionState);
    }



    @Test
    public void handleChangeSavingAmount_givenInteger(){
        String choice = "1\n";
        String amount = "100\n";
        String input = choice + amount;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = new User("Tester", 25);
        SavingState savingState = new SavingState();
        assertTrue(savingState.handle(user) instanceof SavingState);
        assertEquals(100, user.getSaving().getAmount());
    }

    @Test
    public void handleChangeSavingAmount_givenTwoDecimals(){
        String choice = "1\n";
        String amount = "100.50\n";
        String input = choice + amount;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = new User("Tester", 25);
        SavingState savingState = new SavingState();
        assertTrue(savingState.handle(user) instanceof SavingState);
        assertEquals(100.50, user.getSaving().getAmount());
    }

    @Test
    public void handleChangeSavingAmount_givenThreeDecimals(){
        String choice = "1\n";
        String amount = "100.551\n";
        String input = choice + amount;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = new User("Tester", 25);
        SavingState savingState = new SavingState();
        assertTrue(savingState.handle(user) instanceof SavingState);
        assertEquals(100.55, user.getSaving().getAmount());
    }

    @Test
    public void handleChangeSavingAmount_givenChar(){
        String choice = "1\n";
        String amount = "abc\n";
        String trueAmount = "100\n";
        String input = choice + amount + trueAmount;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = new User("Tester", 25);
        SavingState savingState = new SavingState();
        assertTrue(savingState.handle(user) instanceof SavingState);
        assertEquals(100, user.getSaving().getAmount());
    }

    @Test
    public void handleChangeSavingAmount_givenNegativeInteger(){
        String choice = "1\n";
        String amount = "-100\n";
        String trueAmount = "50\n";
        String input = choice + amount + trueAmount;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = new User("Tester", 25);
        SavingState savingState = new SavingState();
        assertTrue(savingState.handle(user) instanceof SavingState);
        assertEquals(50, user.getSaving().getAmount());
    }




    @Test
    public void handleInvalidOption_Integer(){
        String choice = "3\n";
        String input = choice;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = new User("Tester", 25);
        SavingState savingState = new SavingState();
        assertTrue(savingState.handle(user) instanceof SavingState);
        assertEquals(0, user.getSaving().getAmount());
    }

    @Test
    public void handleInvalidOption_Char(){
        String choice = "abc\n";
        String input = choice;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = new User("Tester", 25);
        SavingState savingState = new SavingState();
        assertTrue(savingState.handle(user) instanceof SavingState);
        assertEquals(0, user.getSaving().getAmount());
    }
}
