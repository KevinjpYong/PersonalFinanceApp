package state;

/**
 * Singleton factory to create State of the System
 * **/

public class StateFactory {

    private static StateFactory instance = null;

    private StateFactory(){

    }

    public static synchronized StateFactory getInstance(){
        if(instance == null){
            instance = new StateFactory();
        }
        return instance;
    }

    public State createState(StateType type){
        switch (type){
            case SELECTION_STATE:
                return new SelectionState();
            case SAVING_STATE:
                return new SavingState();
            case LIABILITY_STATE:
                return new LiabilityState();
            case STATEMENT_STATE:
                return new StatementState();
            case GOAL_STATE:
                return new GoalState();
            case BUDGETING_STATE:
                    return new BudgetingState();
            default:
                throw new IllegalArgumentException("Invalid state type: " + type);
        }
    }
}
