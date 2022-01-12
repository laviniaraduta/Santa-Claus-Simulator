package sorting;

import enums.CityStrategyEnum;

public final class SortStrategyFactory {
    private static SortStrategyFactory sortStrategyFactory = null;
    private SortStrategyFactory() {
    }

    /**
     * @return an instance of the factory
     */
    public static SortStrategyFactory getSortStrategyFactory() {
        if (sortStrategyFactory == null) {
            sortStrategyFactory = new SortStrategyFactory();
        }
        return sortStrategyFactory;
    }

    /**
     * Creates the correct strategy according to the type given
     * @param type the type of strategy
     * @return the concrete strategy
     */
    public static SortStrategy createSortStrategy(final CityStrategyEnum type) {
        return switch (type) {
            case NICE_SCORE_CITY -> new SortByCity();
            case ID -> new SortById();
            case NICE_SCORE -> new SortByScore();
        };
    }
}
