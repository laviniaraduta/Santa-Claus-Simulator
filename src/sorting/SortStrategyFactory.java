package sorting;

import entities.Child;
import enums.CityStrategyEnum;
import strategies.BabyAverageScore;
import strategies.KidAverageScore;
import strategies.TeenAverageScore;

import java.util.List;

public class SortStrategyFactory {
    private static SortStrategyFactory sortStrategyFactory = null;
    private SortStrategyFactory() {
    }

    public static SortStrategyFactory getSortStrategyFactory() {
        if (sortStrategyFactory == null) {
            sortStrategyFactory = new SortStrategyFactory();
        }
        return sortStrategyFactory;
    }

    public static SortStrategy createSortStrategy(final CityStrategyEnum type) {
        return switch (type) {
            case NICE_SCORE_CITY -> new SortByCity();
            case ID -> new SortById();
            case NICE_SCORE -> new SortByScore();
        };
    }
}
