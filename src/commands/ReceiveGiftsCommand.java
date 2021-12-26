package commands;

import databases.GiftsDatabase;
import entities.Child;
import entities.Gift;
import enums.Category;

import java.util.List;
import java.util.TreeSet;

public class ReceiveGiftsCommand implements Command {
    private List<Child> children;
    private GiftsDatabase gifts;
    public ReceiveGiftsCommand(List<Child> children, GiftsDatabase gifts) {
        this.children = children;
        this.gifts = gifts;
    }

    @Override
    public void execute() {
        for (Child child : children) {
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
}
