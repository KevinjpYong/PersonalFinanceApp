package handler;
import model.User;

public class SelectionState {

    private static void stateSelection(User user){
        System.out.println("-------------------------------------");
        System.out.println("Options");
        System.out.println("-------------------------------------");
        System.out.println("1.Display/Change Saving Account");
        System.out.println("2.Display/Add Liabilities");
        System.out.println("3.Display/Add Statement");
        System.out.println("4.Display/Change Goal.");
        System.out.println("5.Budgeting.");
        System.out.print("Please select action: ");

        String choice = scanner.nextLine();
        switch (choice){
            case ("1"):
                handleSaving(user);
                break;
        }
    }
}
