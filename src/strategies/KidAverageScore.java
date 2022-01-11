package strategies;

import entities.Child;

public final class KidAverageScore extends AverageScoreStrategy {
    @Override
    public void computeAverageScore(final Child child) {
        Double sum = 0D;
        for (Double score : child.getNiceScoreHistory()) {
            sum += score;
        }
        child.setAverageScore((Double) sum / child.getNiceScoreHistory().size());
    }
}
