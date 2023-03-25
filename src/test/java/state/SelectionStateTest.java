package state;

import model.User;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class SelectionStateTest {

    @Test
    public void handleTest(){
        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        User user = mock(User.class);
        SelectionState state = new SelectionState();
        assertTrue(state.handle(user) instanceof SavingState);
    }
}
