package score_strategies;

import entities.Child;

import static common.Constants.PERFECT_NICE_SCORE;

public final class BabyAverageScore extends AverageScoreStrategy {
    @Override
    public void computeAverageScore(final Child child) {
        child.setAverageScore(PERFECT_NICE_SCORE);
    }
}
