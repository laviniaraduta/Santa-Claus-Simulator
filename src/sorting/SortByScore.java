package sorting;

import entities.Child;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByScore implements SortStrategy {
    @Override
    public void sort(List<Child> children) {
        Collections.sort(children, (o1, o2) -> {
            if (Double.compare(o1.getAverageScore(), o2.getAverageScore()) == 0) {
                return o1.getId() - o2.getId();
            } else {
                return (int) (o2.getAverageScore() - o1.getAverageScore());
            }
        });
    }
}
