package state;

import model.Expense;
import model.Income;
import model.Statement;
import model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class StatementState implements State{
    Scanner scanner = new Scanner(System.in);
    @Override
    public State handle(User user) {
        System.out.println("---------------");
        System.out.println("Statement Menu");
        System.out.println("---------------");
        String option = displayOption();
        switch (option){
            case("1"):
                displayStatements(user);
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
    private String displayOption(){
        System.out.println("1. Display Statements.");
        System.out.println("2. Add Statement.");
        System.out.println("3. Back to Main Menu.");
        return scanner.nextLine();
    }

    private State displayStatements(User user){
        System.out.println("----------");
        System.out.printf("%5s","Statement Date\n");
        System.out.println("----------");
        for(Statement statement: user.getStatements().values()){
            System.out.printf("%s\n", convertDateToString(statement.getDate()));
        }
        System.out.println("--------------------------------------------");
        return displayOptionsForStatements(user);
    }

    private State displayOptionsForStatements(User user){
        System.out.println("1. View Statement.");
        System.out.println("2. Back to Statement Menu.");
        String option = scanner.nextLine();
        return handleOptionsForStatements(option, user);
    }

    private State handleOptionsForStatements(String option, User user){
        switch (option){
            case("1"):
                return handleViewOneStatement(user);
            case("2"):
                return StateFactory.getInstance().createState(StateType.STATEMENT_STATE);
            default:
                System.out.println("Invalid Choice!");
                displayOptionsForStatements(user);
        }
        return null;
    }

    private State handleViewOneStatement(User user){
        Date date = handleDate();
        if(user.getStatements().containsKey(date)){
            return displayStatement(user.getStatement(date), user);
        }else{
            System.out.println("Invalid Date!");
            return displayOptionsForStatements(user);
        }

    }

    private State displayStatement(Statement statement, User user){
        System.out.println("--------------------------------------------");
        System.out.printf("Statement for %s \n", convertDateToString(statement.getDate()));
        System.out.println("--------------------------------------------");
        System.out.println("----------");
        System.out.println("INCOME");
        System.out.println("----------");
        for(Income income: statement.getIncomes()){
            System.out.printf("%-15s%-15.2f\n",income.getName(), income.getAmount());
        }
        System.out.println("----------");
        System.out.println("Expenses");
        System.out.println("----------");
        for(Expense expense: statement.getExpenses()){
            System.out.printf("%-30s%-30.2f\n", expense.getName(),expense.getAmount());
        }
        System.out.println("------------------------------------------------");
        System.out.printf("%-40s %.2f\n","Net Income/Lose:", statement.getNetIncome());
        System.out.println("------------------------------------------------");
        return displayOptionForStatement(statement, user );
    }

    private State displayOptionForStatement(Statement statement, User user){
        System.out.println("1. Add Income.");
        System.out.println("2. Add Expense.");
        System.out.println("3. Back to view all statement.");
        String option =  scanner.nextLine();
        return handleOptionsForStatement(option, statement, user);
    }

    private State handleOptionsForStatement(String option, Statement statement, User user){
        switch (option){
            case("1"):
                return handleAddIncome(statement, user);
            case("2"):
                return handleAddExpense(statement, user);
            case("3"):
                return displayStatements(user);
            default:
                System.out.println("Invalid Choice!");
                displayOptionForStatement(statement, user);
        }
        return null;
    }

    private State handleAddIncome(Statement statement,User user){
        System.out.print("Enter Income Name: ");
        String name = scanner.nextLine();
        double amount = handleAmount();
        statement.addIncome(new Income(name, amount));
        return displayStatement(statement, user);
    }

    private double handleAmount(){
        System.out.print("Enter Amount: ");
        String amount = scanner.nextLine();
        try{
            double amountDouble  = Double.parseDouble(amount);
            if(amountDouble >= 0){
                double roundOff = Math.round(amountDouble * 100.0) / 100.0;
                return roundOff;
            }else{
                System.out.println("Invalid input");
                return handleAmount();
            }
        }catch(NumberFormatException nfe){
            System.out.println("Invalid input");
            return handleAmount();
        }
    }

    private State handleAddExpense(Statement statement, User user){
        System.out.print("Enter Expense Name: ");
        String name = scanner.nextLine();
        double amount = handleAmount();
        statement.addExpense(new Expense(name, amount));
        return displayStatement(statement, user);
    }

    private State addStatement(User user){
        Date date = handleDate();
        if(!user.getStatements().containsKey(date)){
            Statement statement = new Statement(date);
            statement.registerObserver(user.getSaving());
            statement.addExpenses(user.getLiabilitiesRepayment());
            user.addStatement(statement);
            return displayStatement(statement, user);
        }else{
            System.out.println("Statement Already Exist. Unable to create repeated statement");
            return handle(user);
        }

    }

    private Date handleDate(){
        System.out.print("Enter statement date(mm/yyyy): ");
        String date = scanner.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        try{
            return formatter.parse(date);
        }catch (ParseException e){
            System.out.println("Invalid date. Please Re-enter Date.");
            return handleDate();
        }
    }

    private String convertDateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        return formatter.format(date);
    }

}
