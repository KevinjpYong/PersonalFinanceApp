package model;

import java.util.ArrayList;

public interface SubjectBase {

    void addObserver(ObserverBase observer);

    void removeObserver(ObserverBase observer);

    void notifyObserver();

}
