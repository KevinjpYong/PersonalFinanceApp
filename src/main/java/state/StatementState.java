package state;

import model.Expense;
import model.Income;
import model.Statement;
import model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Statement state
 * View statement or create new statement
 * add income and expenses
 * **/

public class StatementState implements State{
    Scanner scanner = new Scanner(System.in);

    // handle state
    @Override
    public State handle(User user) {
        System.out.println("---------------");
        System.out.println("Statement Menu");
        System.out.println("---------------");
        // display options
        String option = displayOption();
        switch (option){
            case("1"):
                displayAllStatements(user);
                return StateFactory.getInstance().createState(StateType.STATEMENT_STATE);
            case("2"):
                return addStatement(user);
            case("3"):
                return StateFactory.getInstance().createState(StateType.SELECTION_STATE);
            default:
                System.out.println("Invalid Option");
                return StateFactory.getInstance().createState(StateType.STATEMENT_STATE);
        }
    }

    // display options
    private String displayOption(){
        System.out.println("1. Display Statements.");
        System.out.println("2. Add Statement.");
        System.out.println("3. Back to Main Menu.");
        return scanner.nextLine();
    }

    // display all user's monthly statements
    private State displayAllStatements(User user){
        System.out.println("---------------");
        System.out.printf("%5s","Statement Date\n");
        System.out.println("---------------");
        // display no statement
        if(user.getStatements().isEmpty()){
            System.out.println("No Statement has been created");
        }
        for(Statement statement: user.getStatements().values()){
            System.out.printf("%s\n", convertDateToString(statement.getDate()));
        }
        System.out.println("--------------------------------------------");
        // display options for view all statements
        return displayOptionsForAllStatements(user);
    }

    // display option after viewing all statements
    private State displayOptionsForAllStatements(User user){
        System.out.println("1. View Statement.");
        System.out.println("2. Back to Statement Menu.");
        String option = scanner.nextLine();
        return handleOptionsForAllStatements(option, user);
    }

    // handle option
    private State handleOptionsForAllStatements(String option, User user){
        switch (option){
            case("1"):
                // view one statement
                return handleOptionToViewStatement(user);
            case("2"):
                // return to statement menu
                return StateFactory.getInstance().createState(StateType.STATEMENT_STATE);
            default:
                System.out.println("Invalid Choice!");
                displayOptionsForAllStatements(user);
        }
        return null;
    }

    // get date of statement from user
    private State handleOptionToViewStatement(User user){
        Date date = handleDate();
        // check if date of statement exist
        if(user.getStatements().containsKey(date)){
            return displayOneStatement(user.getStatement(date), user);
        }else{
            System.out.println("Invalid Date!");
            return displayOptionsForAllStatements(user);
        }

    }

    // display details of statement include incomes and expenses and net income
    private State displayOneStatement(Statement statement, User user){
        System.out.println("--------------------------------------------");
        System.out.printf("Statement for %s \n", convertDateToString(statement.getDate()));
        System.out.println("--------------------------------------------");
        System.out.println("----------");
        System.out.println("INCOME");
        System.out.println("----------");
        if(statement.getIncomes().isEmpty()){
            System.out.println("No Income has been added");
        }
        for(Income income: statement.getIncomes()){
            System.out.printf("%-15s%-15.2f\n",income.getName(), income.getAmount());
        }
        System.out.println("----------");
        System.out.println("Expenses");
        System.out.println("----------");
        if(statement.getExpenses().isEmpty()){
            System.out.println("No Expense has been added");
        }
        for(Expense expense: statement.getExpenses()){
            System.out.printf("%-30s%-30.2f\n", expense.getName(),expense.getAmount());
        }
        System.out.println("------------------------------------------------");
        System.out.printf("%-40s %.2f\n","Net Income/Lose:", statement.getNetIncome());
        System.out.println("------------------------------------------------");
        return displayOptionForOneStatement(statement, user );
    }

    // display option after viewing statement
    private State displayOptionForOneStatement(Statement statement, User user){
        System.out.println("1. Add Income.");
        System.out.println("2. Add Expense.");
        System.out.println("3. Back to view all statement.");
        String option =  scanner.nextLine();
        return handleOptionsForOneStatement(option, statement, user);
    }

    // handle option
    private State handleOptionsForOneStatement(String option, Statement statement, User user){
        switch (option){
            case("1"):
                return handleAddIncome(statement, user);
            case("2"):
                return handleAddExpense(statement, user);
            case("3"):
                return displayAllStatements(user);
            default:
                System.out.println("Invalid Choice!");
                return displayOptionForOneStatement(statement, user);
        }
    }

    // get income name and amount
    // create and add income to statement
    // redisplay statement
    private State handleAddIncome(Statement statement,User user){
        System.out.print("Enter Income Name: ");
        String name = scanner.nextLine();
        double amount = handleAmount();
        statement.addIncome(new Income(name, amount));
        return displayOneStatement(statement, user);
    }

    // get amount from user input
    private double handleAmount(){
        System.out.print("Enter Amount: ");
        String amount = scanner.nextLine();
        try{
            double amountDouble  = Double.parseDouble(amount);
            if(amountDouble >= 0){
                // round number to 2 decimal to represent money
                double roundOff = Math.round(amountDouble * 100.0) / 100.0;
                return roundOff;
            }else{
                // negative value re-ask for input
                System.out.println("Invalid input");
                return handleAmount();
            }
        }catch(NumberFormatException nfe){
            // input unable to convert to number re-ask user for input
            System.out.println("Invalid input");
            return handleAmount();
        }
    }

    // get expense name and amount
    // create and add expense to statement
    // redisplay statement
    private State handleAddExpense(Statement statement, User user){
        System.out.print("Enter Expense Name: ");
        String name = scanner.nextLine();
        double amount = handleAmount();
        statement.addExpense(new Expense(name, amount));
        return displayOneStatement(statement, user);
    }

    // add statement to user's statement
    private State addStatement(User user){
        // get date format
        Date date = handleDate();
        // check if statement date exist
        if(!user.getStatements().containsKey(date)){
            Statement statement = new Statement(date);
            // register saving as a observer for update saving amount
            statement.registerObserver(user.getSaving());
            // create expenses from liabilities and add to statement
            statement.addExpenses(user.getLiabilitiesRepayment());
            // add statement to user's statements
            user.addStatement(statement);
            // display statement
            return displayOneStatement(statement, user);
        }else{
            // date exist in user's statement
            // back to statement menu
            System.out.println("Statement Already Exist. Unable to create repeated statement");
            return handle(user);
        }

    }

    private Date handleDate(){
        System.out.print("Enter statement date(mm/yyyy): ");
        String date = scanner.nextLine();
        // date format months and year
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        try{
            // convert String to date with parser
            return formatter.parse(date);
        }catch (ParseException e){
            // invalid date format re-ask user for input
            System.out.println("Invalid date. Please Re-enter Date.");
            return handleDate();
        }
    }


    // convert date to string with formatter
    private String convertDateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        return formatter.format(date);
    }

}
