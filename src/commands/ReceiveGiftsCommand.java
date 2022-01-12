package commands;

import databases.GiftsDatabase;
import entities.Child;
import entities.Gift;
import enums.Category;

import java.util.List;
import java.util.TreeSet;

import static enums.ElvesType.YELLOW;

public final class ReceiveGiftsCommand implements Command {
    private List<Child> children;
    private GiftsDatabase gifts;
    public ReceiveGiftsCommand(final List<Child> children, final GiftsDatabase gifts) {
        this.children = children;
        this.gifts = gifts;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(final List<Child> children) {
        this.children = children;
    }

    public GiftsDatabase getGifts() {
        return gifts;
    }

    public void setGifts(final GiftsDatabase gifts) {
        this.gifts = gifts;
    }

    /**
     * Assign the gifts for every child according to their preferences
     */
    @Override
    public void execute() {
        for (Child child : children) {
            Double remainingBudget = child.getAssignedBudget();
            child.getReceivedGifts().clear();
            for (Category category : child.getGiftsPreferences()) {
                // Keep a sort set of santa's gifts in ascending order by their prices
                TreeSet<Gift> giftsOfCategory = gifts.getGiftsMap().get(category);
                if (giftsOfCategory != null) {
                    for (Gift gift : giftsOfCategory) {
                        if (Double.compare(remainingBudget, gift.getPrice()) > 0
                                && gift.getQuantity() > 0) {
                            Integer quantity = gift.getQuantity();
                            gift.setQuantity(quantity - 1);
                            child.getReceivedGifts().add(gift);
                            remainingBudget -= gift.getPrice();
                            break;
                        }
                    }
                }
            }
        }
        for (Child child : children) {
            if (child.getElf().equals(YELLOW) && child.getReceivedGifts().isEmpty()) {
                TreeSet<Gift> giftsOfCategory = gifts.getGiftsMap()
                        .get(child.getGiftsPreferences().get(0));
                if (giftsOfCategory != null) {
                    Gift gift = giftsOfCategory.first();
                    if (gift.getQuantity() > 0) {
                        Integer quantity = gift.getQuantity();
                        gift.setQuantity(quantity - 1);
                        child.getReceivedGifts().add(gift);
                    }
                }
            }
        }
    }
}
