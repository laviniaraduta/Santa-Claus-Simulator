package sorting;

import entities.Child;
import enums.Cities;

import java.util.*;
import java.util.stream.Collectors;

public class SortByCity implements SortStrategy {
    @Override
    public void sort(List<Child> children) {
        Map<Cities, Integer> cityWithCount = new HashMap<>();
        Map<Cities, LinkedList<Double>> cityWithScores = new HashMap<>();
        Map<Cities, Double> cityWithAverage = new HashMap<>();
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
//        cityWithAverage = cityWithAverage.entrySet().stream().sorted((o1, o2) -> {
//            if (Double.compare(o1.getValue(), o2.getValue()) == 0) {
//                return o1.getKey().name().compareTo(o2.getKey().name());
//            } else {
//                return Double.compare(o1.getValue(), o2.getValue());
//            }
//        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, Hashtable::new));
//        TreeMap<Cities, Double> sorted = new TreeMap<>(cityWithAverage);
        List<Cities> sorted = new ArrayList<>(cityWithAverage.keySet());
        Collections.sort(sorted, new Comparator<Cities>() {
            @Override
            public int compare(Cities o1, Cities o2) {
                if (Double.compare(cityWithAverage.get(o1), cityWithAverage.get(o2)) == 0) {
                    return o1.name().compareTo(o2.name());
                } else {
                    return Double.compare(cityWithAverage.get(o2), cityWithAverage.get(o1));
                }
            }
        });
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
