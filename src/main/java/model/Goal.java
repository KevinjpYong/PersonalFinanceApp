package model;

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

    public double calAmountRemaining(User user){
        return this.amount - user.getSaving().getAmount();
    }

    public int estimateDurationRequired(User user){
        double remaining = calAmountRemaining(user);
        double averageNetIncome = user.getAverageNetIncome();
        if(remaining <= 0){
            return 0;
        }else{
            if(averageNetIncome <= 0){
                return -1;
            }else{
                int estimatedDuration = (int) Math.ceil(remaining/averageNetIncome);
                return estimatedDuration;
            }
        }
    }
}
