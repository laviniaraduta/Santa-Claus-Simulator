package commands;

import entities.Child;
import enums.Category;

import java.util.List;

public final class GiftPreferencesCommand implements Command {
    private Child child;
    private List<Category> preferences;

    public GiftPreferencesCommand(final Child child, final List<Category> preferences) {
        this.child = child;
        this.preferences = preferences;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(final Child child) {
        this.child = child;
    }

    public List<Category> getPreferences() {
        return preferences;
    }

    public void setPreferences(final List<Category> preferences) {
        this.preferences = preferences;
    }

    /**
     * Add the new preferences from the current year
     */
    @Override
    public void execute() {
        // iterate from the last item to put the preferences in the correct order in the front
        for (int i = preferences.size() - 1; i >= 0; i--) {
            Integer pos = position(child.getGiftsPreferences(), preferences.get(i));
            // Remove preference from the old pos and put it in the front of the list
            if (pos != null) {
                child.getGiftsPreferences().remove(pos.intValue());
            }
            child.getGiftsPreferences().add(0, preferences.get(i));
        }
    }

    /**
     * Returns the position of a category in the list of preferences of a child
     * @param preferences a list of preferences of a child
     * @param category the category looked up
     * @return the position of the category
     */
    public static Integer position(final List<Category> preferences, final Category category) {
        for (Integer i = 0; i < preferences.size(); i++) {
            if (preferences.get(i).equals(category)) {
                return i;
            }
        }
        return null;
    }

}
