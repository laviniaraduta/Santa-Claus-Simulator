package sorting;

import entities.Child;

import java.util.List;

public interface SortStrategy {
    /**
     * Function that sorts the list of children by the
     * criteria received in the input
     * @param children the list of children
     */
    void sort(List<Child> children);
}
