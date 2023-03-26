package state;

import model.Liability;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LiabilityStateTest {
    @Test
    public void handleDisplayLiabilities_choice1(){
        String choice = "1\n";
        String input = choice;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = new User("Tester", 25);
        LiabilityState liabilityState = new LiabilityState();
        assertTrue(liabilityState.handle(user) instanceof LiabilityState);
    }

    @Test
    public void handleAddLiability_choice2_givenStringDoubleDoubleInt(){

        String choice = "2\n";
        String nameTest = "LiabilityTest\n";
        String amountTest = "1000000\n";
        String ratesTest = "3.5\n";
        String durationTest = "12\n";

        String input = choice + nameTest + amountTest + ratesTest + durationTest;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = new User("Tester", 25);
        LiabilityState liabilityState = new LiabilityState();


        String actualName = "LiabilityTest";
        Double actualAmount = 1000000.0;
        Double actualRates = 3.5/100;
        int actualDuration = 12;
        assertTrue(liabilityState.handle(user) instanceof LiabilityState);
        assertEquals(actualName, user.getLiabilities().get(0).getName());
        assertEquals(actualAmount, user.getLiabilities().get(0).getAmount());
        assertEquals(actualRates, user.getLiabilities().get(0).getRates());
        assertEquals(actualDuration, user.getLiabilities().get(0).getDuration());
    }

    @Test
    public void handleAddLiability_choice2_givenInvalidAmount(){

        String choice = "2\n";
        String testName = "LiabilityTest\n";
        String invalidAmount = "abc\n";
        String testAmount = "1000000\n";
        String testRates = "3.5\n";
        String testDuration = "12\n";

        String input = choice + testName + invalidAmount + testAmount + testRates + testDuration;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = new User("Tester", 25);
        LiabilityState liabilityState = new LiabilityState();


        String actualName = "LiabilityTest";
        Double actualAmount = 1000000.0;
        Double actualRates = 3.5/100;
        int actualDuration = 12;
        assertTrue(liabilityState.handle(user) instanceof LiabilityState);
        assertEquals(actualName, user.getLiabilities().get(0).getName());
        assertEquals(actualAmount, user.getLiabilities().get(0).getAmount());
        assertEquals(actualRates, user.getLiabilities().get(0).getRates());
        assertEquals(actualDuration, user.getLiabilities().get(0).getDuration());
    }
    @Test
    public void handleAddLiability_choice2_givenNegativeAmount(){

        String choice = "2\n";
        String testName = "LiabilityTest\n";
        String negativeAmount = "-1500000\n";
        String testAmount = "1000000\n";
        String testRates = "3.5\n";
        String testDuration = "12\n";

        String input = choice + testName + negativeAmount + testAmount + testRates + testDuration;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = new User("Tester", 25);
        LiabilityState liabilityState = new LiabilityState();


        String actualName = "LiabilityTest";
        Double actualAmount = 1000000.0;
        Double actualRates = 3.5/100;
        int actualDuration = 12;
        assertTrue(liabilityState.handle(user) instanceof LiabilityState);
        assertEquals(actualName, user.getLiabilities().get(0).getName());
        assertEquals(actualAmount, user.getLiabilities().get(0).getAmount());
        assertEquals(actualRates, user.getLiabilities().get(0).getRates());
        assertEquals(actualDuration, user.getLiabilities().get(0).getDuration());
    }

    @Test
    public void handleAddLiability_choice2_givenInvalidRates(){

        String choice = "2\n";
        String testName = "LiabilityTest\n";
        String testAmount = "1000000\n";
        String invalidRates = "abc\n";
        String testRates = "3.5\n";
        String testDuration = "12\n";

        String input = choice + testName + testAmount + invalidRates + testRates + testDuration;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = new User("Tester", 25);
        LiabilityState liabilityState = new LiabilityState();


        String actualName = "LiabilityTest";
        Double actualAmount = 1000000.0;
        Double actualRates = 3.5/100;
        int actualDuration = 12;
        assertTrue(liabilityState.handle(user) instanceof LiabilityState);
        assertEquals(actualName, user.getLiabilities().get(0).getName());
        assertEquals(actualAmount, user.getLiabilities().get(0).getAmount());
        assertEquals(actualRates, user.getLiabilities().get(0).getRates());
        assertEquals(actualDuration, user.getLiabilities().get(0).getDuration());
    }

    @Test
    public void handleAddLiability_choice2_givenNegativeRates(){

        String choice = "2\n";
        String testName = "LiabilityTest\n";
        String testAmount = "1000000\n";
        String invalidRates = "-5.5\n";
        String testRates = "3.5\n";
        String testDuration = "12\n";

        String input = choice + testName + testAmount + invalidRates + testRates + testDuration;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = new User("Tester", 25);
        LiabilityState liabilityState = new LiabilityState();


        String actualName = "LiabilityTest";
        Double actualAmount = 1000000.0;
        Double actualRates = 3.5/100;
        int actualDuration = 12;
        assertTrue(liabilityState.handle(user) instanceof LiabilityState);
        assertEquals(actualName, user.getLiabilities().get(0).getName());
        assertEquals(actualAmount, user.getLiabilities().get(0).getAmount());
        assertEquals(actualRates, user.getLiabilities().get(0).getRates());
        assertEquals(actualDuration, user.getLiabilities().get(0).getDuration());
    }

    @Test
    public void handleAddLiability_choice2_givenInvalidDuration(){

        String choice = "2\n";
        String testName = "LiabilityTest\n";
        String testAmount = "1000000\n";
        String testRates = "3.5\n";
        String invalidDuration = "abc\n";
        String testDuration = "12\n";

        String input = choice + testName + testAmount + testRates + invalidDuration + testDuration;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = new User("Tester", 25);
        LiabilityState liabilityState = new LiabilityState();


        String actualName = "LiabilityTest";
        Double actualAmount = 1000000.0;
        Double actualRates = 3.5/100;
        int actualDuration = 12;
        assertTrue(liabilityState.handle(user) instanceof LiabilityState);
        assertEquals(actualName, user.getLiabilities().get(0).getName());
        assertEquals(actualAmount, user.getLiabilities().get(0).getAmount());
        assertEquals(actualRates, user.getLiabilities().get(0).getRates());
        assertEquals(actualDuration, user.getLiabilities().get(0).getDuration());
    }

    @Test
    public void handleAddLiability_choice2_givenNegativeDuration(){

        String choice = "2\n";
        String testName = "LiabilityTest\n";
        String testAmount = "1000000\n";
        String testRates = "3.5\n";
        String negativeDuration = "-24\n";
        String testDuration = "12\n";

        String input = choice + testName + testAmount  + testRates + negativeDuration + testDuration;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = new User("Tester", 25);
        LiabilityState liabilityState = new LiabilityState();


        String actualName = "LiabilityTest";
        Double actualAmount = 1000000.0;
        Double actualRates = 3.5/100;
        int actualDuration = 12;
        assertTrue(liabilityState.handle(user) instanceof LiabilityState);
        assertEquals(actualName, user.getLiabilities().get(0).getName());
        assertEquals(actualAmount, user.getLiabilities().get(0).getAmount());
        assertEquals(actualRates, user.getLiabilities().get(0).getRates());
        assertEquals(actualDuration, user.getLiabilities().get(0).getDuration());
    }

    @Test
    public void handleReturnToSelectionMenu_choice3(){
        String choice = "3\n";
        String input = choice;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = new User("Tester", 25);
        LiabilityState liabilityState = new LiabilityState();
        assertTrue(liabilityState.handle(user) instanceof SelectionState);
    }

}
