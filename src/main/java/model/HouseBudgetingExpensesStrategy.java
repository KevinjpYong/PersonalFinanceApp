package model;

public class HouseBudgetingExpensesStrategy implements BudgetingExpensesStrategy {

    private final double LMI = 0.01;
    @Override
    public double calculateBudgetingExpenses(double totalCost, Liability loan, User user) {
        // loan to value ratio
        double lvr = loan.getAmount()/totalCost;

        if(lvr>0.8){
            return loan.getMonthlyPayment();
        }else{
            Liability lmiLiability = new Liability("lmi",loan.getAmount()*LMI,LMI,loan.getDuration());
            return loan.getMonthlyPayment()+lmiLiability.getMonthlyPayment();
        }
    }


}
