package sorting;

import entities.Child;

import java.util.Collections;
import java.util.List;

public final class SortByScore implements SortStrategy {
    @Override
    public void sort(final List<Child> children) {
        // If their average scores are equal, sort ascending by id
        Collections.sort(children, (o1, o2) -> {
            if (Double.compare(o1.getAverageScore(), o2.getAverageScore()) == 0) {
                return o1.getId() - o2.getId();
            } else {
                return Double.compare(o2.getAverageScore(), o1.getAverageScore());
            }
        });
    }
}
