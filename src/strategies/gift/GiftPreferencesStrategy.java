package strategies.gift;

import entities.Child;
import enums.Category;

import java.util.List;

public class GiftPreferencesStrategy {
    public void addPreferences(Child child, List<Category> preferences) {
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
