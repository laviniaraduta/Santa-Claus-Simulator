package sorting;

import entities.Child;
import enums.Cities;

import java.util.*;

public class SortByCity implements SortStrategy {
    @Override
    public void sort(List<Child> children) {
        Map<Cities, Integer> cityWithCount = new HashMap<>();
        Map<Cities, LinkedList<Double>> cityWithScores = new HashMap<>();
        Map<Cities, Double> cityWithAverage = new HashMap<>();
        // simultaneously create a map with the key the city and
        // the value the number of children and one with city and a list of the
        // average scores of the children from that city
        for (Child child : children) {
            if (cityWithCount.containsKey(child.getCity())) {
                Integer count = cityWithCount.get(child.getCity());
                cityWithCount.replace(child.getCity(), count + 1);
                LinkedList<Double> list = cityWithScores.get(child.getCity());
                list.addLast(child.getAverageScore());
                cityWithScores.replace(child.getCity(), list);
            } else {
                cityWithCount.put(child.getCity(), 1);
                LinkedList<Double> list = new LinkedList<>();
                list.addLast(child.getAverageScore());
                cityWithScores.put(child.getCity(), list);
            }
        }
        // create a map with entries as city and average score of that city
        for (Map.Entry<Cities, Integer> entry : cityWithCount.entrySet()) {
            Integer count = entry.getValue();
            Cities city = entry.getKey();
            LinkedList<Double> scores = cityWithScores.get(city);
            Double sum = 0D;
            for (Double score : scores) {
                sum += score;
            }
            Double cityAverage = (Double) (sum / count);
            cityWithAverage.put(city, cityAverage);
        }
        // create the sorted list of cities by the average score
        List<Cities> sorted = new ArrayList<>(cityWithAverage.keySet());
        Collections.sort(sorted, (o1, o2) -> {
            if (Double.compare(cityWithAverage.get(o1), cityWithAverage.get(o2)) == 0) {
                return o1.name().compareTo(o2.name());
            } else {
                return Double.compare(cityWithAverage.get(o2), cityWithAverage.get(o1));
            }
        });
        // sort the children by the city list, and ascending by the id
        // they are already sorted by the id so just traverse the list
        List<Child> sortedChildren = new ArrayList<>();
        for (Cities city : sorted) {
            for (Child child : children) {
                if (child.getCity().equals(city)) {
                    sortedChildren.add(sortedChildren.size(), child);
                }
            }
        }
        children.clear();
        children.addAll(sortedChildren);
    }
}
