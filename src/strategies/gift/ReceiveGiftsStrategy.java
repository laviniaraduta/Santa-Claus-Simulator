package strategies.gift;

import databases.GiftsDatabase;
import entities.Child;
import entities.Gift;
import enums.Category;

import java.util.TreeSet;

public class ReceiveGiftsStrategy {
    public void receiveGifts(Child child, GiftsDatabase gifts) {
        Double remainingBudget = child.getAssignedBudget();
        child.getReceivedGifts().clear();
        for (Category category : child.getGiftsPreferences()) {
            TreeSet<Gift> giftsOfCategory = gifts.getGiftsMap().get(category);
            if (giftsOfCategory != null) {
                if (Double.compare(remainingBudget, giftsOfCategory.first().getPrice()) > 0) {
                    child.getReceivedGifts().add(giftsOfCategory.first());
                    remainingBudget -= giftsOfCategory.first().getPrice();
                }
            }
        }

    }
}
