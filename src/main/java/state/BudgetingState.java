package state;

import model.Budgeting;
import model.BudgetingType;
import model.Liability;
import model.User;
import java.util.Scanner;


/**
 * Budgeting State
 * create new budgeting for user
 * **/

public class BudgetingState implements State{

    Scanner scanner = new Scanner(System.in);

    // handle the State
    @Override
    public State handle(User user) {
        System.out.println("---------------");
        System.out.println("Budgeting Menu");
        System.out.println("---------------");
        // get budgeting type, total cost, loan amount and interest rate from user input
        BudgetingType budgetingType = handleBudgetType();
        double totalCost = handleTotalCost();
        double loanAmount = handleLoanAmount();
        double interestRates = handleRates();
        int loanDuration = handleLoanDuration();
        // create a new liability for loan
        Liability loan = new Liability(budgetingType.name(),loanAmount,interestRates,loanDuration);
        // create a budgeting
        Budgeting budgeting = new Budgeting(budgetingType, totalCost, loan, user);
        System.out.println("---------------------------------------------------");
        System.out.println("Budgeting Result");
        System.out.println("---------------------------------------------------");
        // display initial payment report
        displayInitialPaymentReport(user, budgeting);
        // display monthly net income prediction report
        displayMonthlyPaymentReport(user, budgeting);
        // display viability of the budgeting
        if(budgeting.calcInitialPaymentWithSaving()>=0 && budgeting.calcNetIncomeAfterExtraExpenses()>=0){
            System.out.println("Sufficient Saving and Income to carry out plan.");
        }else{
            System.out.println("Insufficient Saving or Income to carry out plan. ");
        }
        return handleReturnMainMenu();
    }

    // display initial payment affordability report
    // display user's saving
    // display required initial payment
    private void displayInitialPaymentReport(User user, Budgeting budgeting){
        System.out.println("Initial Payment Report");
        System.out.println("------------------------");
        System.out.printf("%-30s %-15.2f \n", "Saving:",user.getSaving().getAmount());
        System.out.printf("%-45s %-15.2f \n", "InitialPayment:", budgeting.calcCurrentPayment());
        System.out.println("------------------------------------------------");
        // display affordability result
        if(budgeting.calcInitialPaymentWithSaving()>=0){
            System.out.println("Sufficient saving for initial payment.");
        }else{
            System.out.printf("%-60s %-15.2f \n", "Required Amount:", budgeting.calcInitialPaymentWithSaving()*-1);
        }
        System.out.println("------------------------------------------------");
    }

    // display user's monthly average net income
    // display plan's extra expenses
    // display viability
    private void displayMonthlyPaymentReport(User user, Budgeting budgeting){
        System.out.println("Monthly Payment Report");
        System.out.println("------------------------");
        System.out.printf("%-30s %-15.2f \n", "Average Net Income:",user.getAverageNetIncome());
        System.out.printf("%-45s %-15.2f \n", "Extra Expenses:", budgeting.calcExtraExpenses());
        System.out.println("------------------------------------------------");
        // calculate if average net income can afford purchase plan
        if(budgeting.calcNetIncomeAfterExtraExpenses()>=0){
            System.out.println("Sufficient Monthly Net Income.");
        }else{
            System.out.printf("%-60s %-15.2f \n", "Required Extra Income:", budgeting.calcNetIncomeAfterExtraExpenses()*-1);
        }
        System.out.println("------------------------------------------------");
    }

    // get budget type from user input
    private BudgetingType handleBudgetType(){
        System.out.print("Choose Budget Type: \n");
        System.out.println("1.Car Purchase\n2.House Purchase");
        String option = scanner.nextLine();
        switch (option){
            case("1"):
                return BudgetingType.CAR_PURCHASE;
            case("2"):
                return BudgetingType.HOUSE_PURCHASE;
            default:
                // invalid option get user input again
                System.out.println("Invalid Option");
                return handleBudgetType();
        }
    }

    // get total cost from user input
    private double handleTotalCost(){
        System.out.print("Enter Total Cost: ");
        String amount = scanner.nextLine();
        try{
            double amountDouble  = Double.parseDouble(amount);
            // check if amount is positive
            if(amountDouble >= 0){
                // round amount to 2 decimal representing money
                double roundOff = Math.round(amountDouble * 100.0) / 100.0;
                return roundOff;
            }else{
                // negative value re-ask user for input
                System.out.println("Invalid input");
                return handleTotalCost();
            }
        }catch(NumberFormatException nfe){
            // input not numbers re-ask user for input
            System.out.println("Invalid input");
            return handleTotalCost();
        }
    }

    // get loan amount from user input
    private double handleLoanAmount(){
        System.out.print("Enter Loan Amount: ");
        String amount = scanner.nextLine();
        try{
            double amountDouble  = Double.parseDouble(amount);
            if(amountDouble >= 0){
                // round amount to 2 decimal representing money
                double roundOff = Math.round(amountDouble * 100.0) / 100.0;
                return roundOff;
            }else{
                // negative value re-ask user for input
                System.out.println("Invalid input");
                return handleLoanAmount();
            }
        }catch(NumberFormatException nfe){
            // input not numbers re-ask user for input
            System.out.println("Invalid input");
            return handleLoanAmount();
        }
    }

    // get interest rate from user input
    private double handleRates(){
        System.out.print("Enter interest rates in %: ");
        String rates = scanner.nextLine();
        try{
            double ratesDouble  = Double.parseDouble(rates);
            ratesDouble = ratesDouble/100;
            if(ratesDouble >= 0){
                return ratesDouble;
            }else{
                // negative value re-ask user for input
                System.out.println("Invalid input");
                return handleRates();
            }
        }catch(NumberFormatException nfe){
            // input not numbers re-ask user for input
            System.out.println("Invalid input");
            return handleRates();
        }
    }

    // get loan duration from user input
    private int handleLoanDuration(){
        System.out.print("Enter loan duration(months): ");
        String duration = scanner.nextLine();
        try{
            int durationInt  = Integer.parseInt(duration);
            if(durationInt >= 0){
                return durationInt;
            }else{
                // negative value re-ask user for input
                System.out.println("Invalid input");
                return handleLoanDuration();
            }
        }catch(NumberFormatException nfe){
            // invalid input re-ask user for input
            System.out.println("Invalid input");
            return handleLoanDuration();
        }
    }

    // redirect user back to main menu
    private State handleReturnMainMenu(){
        System.out.println("1. Return to Main Menu.");
        String option = scanner.nextLine();
        // back to selection state
        if(option.equals("1")){
            return StateFactory.getInstance().createState(StateType.SELECTION_STATE);
        }else{
            // invalid option re-ask user for input
            System.out.println("Invalid Option.");
            return handleReturnMainMenu();
        }
    }


}
