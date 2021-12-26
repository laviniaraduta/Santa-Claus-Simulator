package strategies;

public class StrategyFactory {
    private static StrategyFactory strategyFactory = null;
    private StrategyFactory() {
    }

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

    public static AverageScoreStrategy createStrategy(StrategyType strategyType) {
        switch (strategyType) {
            case BABY_SCORE:
                return new BabyAverageScore();
            case KID_SCORE:
                return new KidAverageScore();
            case TEEN_SCORE:
                return new TeenAverageScore();
        }
        throw new IllegalArgumentException("Type not recognized.");
    }
}
