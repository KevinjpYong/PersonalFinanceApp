import state.SelectionState;
import state.State;
import model.User;
import state.StateFactory;
import state.StateType;

import java.util.Scanner;

public class PersonalFinanceApp {
    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        User user = handleUser();

        State state = StateFactory.getInstance().createState(StateType.SELECTION_STATE);
        while(true){
            state = state.handle(user);
        }
    }


    private static User handleUser(){
        System.out.println("================================================");
        System.out.println("Welcome to your Personal Finance App!");
        System.out.println("================================================");
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Please enter your age: ");
        int age = Integer.parseInt(scanner.nextLine());
        User user = new User(name, age);
        return user;
    }









}
