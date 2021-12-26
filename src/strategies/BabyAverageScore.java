package strategies;

import entities.Child;

public class BabyAverageScore extends AverageScoreStrategy {
    @Override
    public void computeAverageScore(Child child) {
        child.setAverageScore(10d);
    }
}
