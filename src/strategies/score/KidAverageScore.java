package strategies.score;

import entities.Child;
import strategies.score.AverageScoreStrategy;

public class KidAverageScore extends AverageScoreStrategy {

    @Override
    public void computeAverageScore(Child child) {
        Double sum = 0d;
        for (Double score : child.getNiceScoreHistory()) {
            sum += score;
        }
        child.setAverageScore((Double) sum / child.getNiceScoreHistory().size());
    }
}
