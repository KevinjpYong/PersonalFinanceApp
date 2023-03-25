package state;

import model.User;

public interface State {

   public State handle(User user);
}
