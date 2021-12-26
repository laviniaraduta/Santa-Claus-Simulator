package entities;

import fileio.ChildInput;

public class ChildrenFactory {
    private static ChildrenFactory factory = null;
    private ChildrenFactory() {
    }

    public static ChildrenFactory getFactory() {
        if (factory == null) {
            factory = new ChildrenFactory();
        }
        return factory;
    }

    public static Child createChild(ChildInput childInput) {
        return new Child(childInput.getId(), childInput.getLastName(),
                childInput.getFirstName(), childInput.getNiceScore(),
                childInput.getAge(), childInput.getCity(),
                childInput.getGiftsPreferences());
    }
}
