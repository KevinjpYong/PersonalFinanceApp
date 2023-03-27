package model;

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

    @Override
    public void update(CashFlow cashFlow) {
        if(cashFlow instanceof Expense){
            setAmount(this.amount - cashFlow.amount);
        }else if(cashFlow instanceof Income){
            setAmount(this.amount + cashFlow.amount);
        }
    }
}
