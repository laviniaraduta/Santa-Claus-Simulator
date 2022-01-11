package strategies;

import entities.Child;

public abstract class AverageScoreStrategy {
    /**
     * Computes the average score of the child according to the rules
     * @param child the current child
     */
    public abstract void computeAverageScore(Child child);
}
