package strategies.score;

import entities.Child;
import strategies.score.AverageScoreStrategy;

public class BabyAverageScore extends AverageScoreStrategy {
    @Override
    public void computeAverageScore(Child child) {
        child.setAverageScore(10d);
    }
}
