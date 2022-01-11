package sorting;

import entities.Child;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortById implements SortStrategy {

    @Override
    public void sort(List<Child> children) {
        Collections.sort(children, Comparator.comparingInt(Child::getId));
    }
}
