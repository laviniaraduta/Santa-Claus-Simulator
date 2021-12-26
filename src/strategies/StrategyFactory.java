package strategies;

public final class StrategyFactory {
    private static StrategyFactory strategyFactory = null;
    private StrategyFactory() {
    }

    /**
     * @return an instance of the factory
     */
    public static StrategyFactory getStrategyFactory() {
        if (strategyFactory == null) {
            strategyFactory = new StrategyFactory();
        }
        return strategyFactory;
    }

    public enum StrategyType {
        BABY_SCORE,
        KID_SCORE,
        TEEN_SCORE
    }

    /**
     * Creates the correct strategy according to the type given
     * @param strategyType the type of strategy
     * @return the concrete strategy
     */
    public static AverageScoreStrategy createStrategy(final StrategyType strategyType) {
        return switch (strategyType) {
            case BABY_SCORE -> new BabyAverageScore();
            case KID_SCORE -> new KidAverageScore();
            case TEEN_SCORE -> new TeenAverageScore();
        };
    }
}
