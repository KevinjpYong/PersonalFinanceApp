package model;

/**
 * Strategy to calculate house purchase's monthly expenses
 * **/
public class HouseBudgetingExpensesStrategy implements BudgetingExpensesStrategy {

    // estimated Lenders' Mortgage Insurance rate
    private final double LMI = 0.01;

    // calculate monthly expenses of a house purchase
    // include loan repayment and mortgage insurance
    @Override
    public double calcBudgetingExpenses(double totalCost, Liability loan, User user) {
        // loan to value ratio
        double lvr = loan.getAmount()/totalCost;

        // include lmi if lvr if above 80 percent
        if(lvr<0.8){
            return loan.getMonthlyPayment();
        }else{
            // create a liability object to calculate monthly repayment
            Liability lmiLiability = new Liability("lmi",loan.getAmount()*LMI,LMI,loan.getDuration());
            return loan.getMonthlyPayment()+lmiLiability.getMonthlyPayment();
        }
    }


}
