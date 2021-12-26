package databases;

import entities.Gift;
import entities.GiftsFactory;
import enums.Category;
import fileio.GiftInput;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;

public final class GiftsDatabase {
    // used LinkedHashMap to store the gifts of each category sorted
    private LinkedHashMap<Category, TreeSet<Gift>> giftsMap =
            new LinkedHashMap<Category, TreeSet<Gift>>();

    public GiftsDatabase(final List<Gift> giftList) {
        for (Gift gift : giftList) {
            TreeSet<Gift> set = this.giftsMap.get(gift.getCategory());
            // if the category is not already in the map, add it and create the set for the value
            if (set == null) {
                set = new TreeSet<Gift>(Comparator.comparingDouble(Gift::getPrice));
                this.giftsMap.put(gift.getCategory(), set);
            }
            set.add(gift);
        }
    }

    /**
     * Add a gift in the database, keeping the order
     * @param giftInput the input data of the gift
     */
    public void addGift(final GiftInput giftInput) {
        GiftsFactory factory = GiftsFactory.getFactory();
        Gift newGift = factory.createGift(giftInput);
        TreeSet<Gift> set = this.giftsMap.get(newGift.getCategory());
        if (set == null) {
            set = new TreeSet<Gift>(Comparator.comparingDouble(Gift::getPrice));
            this.giftsMap.put(newGift.getCategory(), set);
        }
        set.add(newGift);
    }

    public LinkedHashMap<Category, TreeSet<Gift>> getGiftsMap() {
        return giftsMap;
    }

    public void setGiftsMap(final LinkedHashMap<Category, TreeSet<Gift>> giftsMap) {
        this.giftsMap = giftsMap;
    }
}
