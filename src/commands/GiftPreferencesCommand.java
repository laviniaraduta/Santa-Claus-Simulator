package commands;

import entities.Child;
import enums.Category;

import java.util.List;

public class GiftPreferencesCommand implements Command {
    private Child child;
    private List<Category> preferences;

    public GiftPreferencesCommand(Child child, List<Category> preferences) {
        this.child = child;
        this.preferences = preferences;
    }

    @Override
    public void execute() {
        for (int i = preferences.size() - 1; i >= 0; i--) {
            Integer pos = position(child.getGiftsPreferences(), preferences.get(i));
            if (pos != null) {
                child.getGiftsPreferences().remove(pos.intValue());
            }
            child.getGiftsPreferences().add(0, preferences.get(i));
        }
    }
    public static Integer position(List<Category> preferences, Category category) {
        for (Integer i = 0; i < preferences.size(); i++) {
            if (preferences.get(i).equals(category)) {
                return i;
            }
        }
        return null;
    }

}
