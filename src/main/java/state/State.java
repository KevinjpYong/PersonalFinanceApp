package state;

import model.User;

/**
 * Interface for state
 * **/
public interface State {

   public State handle(User user);
}
