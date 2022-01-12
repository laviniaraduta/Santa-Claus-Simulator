package sorting;

import entities.Child;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class SortById implements SortStrategy {

    @Override
    public void sort(final List<Child> children) {
        Collections.sort(children, Comparator.comparingInt(Child::getId));
    }
}
