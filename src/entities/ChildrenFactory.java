package entities;

import fileio.ChildInput;

public final class ChildrenFactory {
    private static ChildrenFactory factory = null;
    private ChildrenFactory() {
    }

    /**
     * @return the instance of the singleton class
     */
    public static ChildrenFactory getFactory() {
        if (factory == null) {
            factory = new ChildrenFactory();
        }
        return factory;
    }

    /**
     * Creates a Child object using the data given in the input
     * @param childInput the input data for the child
     * @return a new Child object
     */
    public static Child createChild(final ChildInput childInput) {
        return new Child(childInput.getId(), childInput.getLastName(),
                childInput.getFirstName(), childInput.getNiceScore(),
                childInput.getAge(), childInput.getCity(),
                childInput.getGiftsPreferences(), childInput.getNiceScoreBonus(), childInput.getElf());
    }
}
