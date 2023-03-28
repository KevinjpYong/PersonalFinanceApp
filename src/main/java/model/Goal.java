package model;

/**
 *  Model of Goal
 * **/
public class Goal {
    private String name;
    private double amount;

    public Goal(String name, double amount){
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount(){
        return this.amount;
    }

    // calculate the difference of user's saving and goal's target amount
    public double calAmountRemaining(User user){
        return this.amount - user.getSaving().getAmount();
    }

    // calculate estimate duration to achieve goal's target amount based on past statements' net income
    public int estimateDurationRequired(User user){
        double remaining = calAmountRemaining(user);
        double averageNetIncome = user.getAverageNetIncome();
        // return 0 if goal's target achieved
        if(remaining <= 0){
            return 0;
        }else{
            // return -1 if net income is negative -> impossible to achieve target amount
            if(averageNetIncome <= 0){
                return -1;
            }else{
                int estimatedDuration = (int) Math.ceil(remaining/averageNetIncome);
                return estimatedDuration;
            }
        }
    }
}
