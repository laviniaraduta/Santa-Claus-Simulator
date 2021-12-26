package strategies;

import entities.Child;

public final class TeenAverageScore extends AverageScoreStrategy {
    @Override
    public void computeAverageScore(final Child child) {
        Double sum = 0d;
        int n = child.getNiceScoreHistory().size();
        for (int i = 0; i < child.getNiceScoreHistory().size(); i++) {
            sum += (Double) child.getNiceScoreHistory().get(i) * (i + 1);
        }
        child.setAverageScore((Double) (2 * sum) / (n * (n + 1)));
    }
}
