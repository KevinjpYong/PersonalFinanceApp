package state;

import model.User;

import java.util.Scanner;

public class LiabilityState implements State{

    Scanner scanner = new Scanner(System.in);
    @Override
    public State handle(User user) {
        String option = displayOption();
        switch (option){
            case("1"):
                displayLiabilities();
                return StateFactory.getInstance().createState(StateType.LIABILITY_STATE);
            case("2"):
                addLiabitlies();
                return StateFactory.getInstance().createState(StateType.LIABILITY_STATE);
            case("3"):
                return StateFactory.getInstance().createState(StateType.SELECTION_STATE);
        }
        return null;
    }

    private String displayOption(){
        System.out.println("1. Display Liabilities.");
        System.out.println("2. Add Liability.");
        System.out.println("3. Back to Main Menu.");
        return scanner.nextLine();
    }

    private void displayLiabilities(){

    }


   private void addLiabitlies(){

   }
}
