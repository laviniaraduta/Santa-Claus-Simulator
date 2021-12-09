package strategies.score;

import entities.Child;
import strategies.score.AverageScoreStrategy;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TeenAverageScore extends AverageScoreStrategy {
    @Override
    public void computeAverageScore(Child child) {
        Map<Double, Long> frequencyMap = child.getNiceScoreHistory().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Double weightedAverage = (Double) calculateWeightedAverage(frequencyMap);
        child.setAverageScore(weightedAverage);
    }

    static Double calculateWeightedAverage(Map<Double, Long> map) throws ArithmeticException {
        Double num = 0d;
        Double denom = 0d;
        for (Map.Entry<Double, Long> entry : map.entrySet()) {
            num += entry.getKey() * entry.getValue();
            denom += entry.getValue();
        }
        return (Double) num / denom;
    }
}
