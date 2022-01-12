package strategies;

import entities.Child;

import static common.Constants.FULL_PERCENT;
import static common.Constants.PERFECT_NICE_SCORE;

public final class TeenAverageScore extends AverageScoreStrategy {
    @Override
    public void computeAverageScore(final Child child) {
        Double sum = 0d;
        int n = child.getNiceScoreHistory().size();
        for (int i = 0; i < child.getNiceScoreHistory().size(); i++) {
            sum += (Double) child.getNiceScoreHistory().get(i) * (i + 1);
        }
        Double averageScore = (Double) (2 * sum) / (n * (n + 1));
        averageScore += averageScore * child.getNiceScoreBonus() / FULL_PERCENT;
        if (averageScore > PERFECT_NICE_SCORE) {
            child.setAverageScore(PERFECT_NICE_SCORE);
        } else {
            child.setAverageScore(averageScore);
        }
    }
}
