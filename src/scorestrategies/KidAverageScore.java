package scorestrategies;

import entities.Child;

import static common.Constants.FULL_PERCENT;
import static common.Constants.PERFECT_NICE_SCORE;

public final class KidAverageScore extends AverageScoreStrategy {
    @Override
    public void computeAverageScore(final Child child) {
        Double sum = 0D;
        for (Double score : child.getNiceScoreHistory()) {
            sum += score;
        }
        Double averageScore = (Double) sum / child.getNiceScoreHistory().size();
        averageScore += averageScore * child.getNiceScoreBonus() / FULL_PERCENT;
        if (averageScore > PERFECT_NICE_SCORE) {
            child.setAverageScore(PERFECT_NICE_SCORE);
        } else {
            child.setAverageScore(averageScore);
        }

    }
}
