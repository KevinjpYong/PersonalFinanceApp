package model;

/**
 * Interface for subject to be observed
 * **/

public interface SubjectBase {

    void registerObserver(ObserverBase observer);

    void notifyObserver(CashFlow cashFlow);

}
