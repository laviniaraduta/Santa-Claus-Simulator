package strategies.score;

import entities.Child;
import strategies.score.AverageScoreStrategy;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TeenAverageScore extends AverageScoreStrategy {
    @Override
    public void computeAverageScore(Child child) {
        Double sum = 0d;
        int n = child.getNiceScoreHistory().size();
        for (int i = 0 ; i < child.getNiceScoreHistory().size(); i++) {
            sum += (Double) child.getNiceScoreHistory().get(i) * (i + 1);
        }
        child.setAverageScore((Double) (2 * sum) / (n * (n + 1)));
    }
}
