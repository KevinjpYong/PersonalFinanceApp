package model;
/**
 * model for Saving
 * implemented observer
 * **/
public class Saving implements ObserverBase {
   double amount;

    public Saving(double amount){
        this.amount = amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public double getAmount(){
        return this.amount;
    }


    // update cash flow from statement to adjust current saving amount
    @Override
    public void update(CashFlow cashFlow) {
        // decrease when cash flow is expense
        if(cashFlow instanceof Expense){
            setAmount(this.amount - cashFlow.amount);
        // increase when cash flow is income
        }else if(cashFlow instanceof Income){
            setAmount(this.amount + cashFlow.amount);
        }
    }
}
