package databases;

import entities.Gift;
import entities.GiftsFactory;
import enums.Category;
import fileio.GiftInput;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;

public class GiftsDatabase {
    private LinkedHashMap<Category, TreeSet<Gift>> giftsMap = new LinkedHashMap<Category, TreeSet<Gift>>();

    public GiftsDatabase(List<Gift> giftList) {
        for (Gift gift : giftList) {
            TreeSet<Gift> set = this.giftsMap.get(gift.getCategory());
            if (set == null) {
                set = new TreeSet<>(new Comparator<Gift>() {
                    @Override
                    public int compare(Gift o1, Gift o2) {
                        return Double.compare(o1.getPrice(), o2.getPrice());
                    }
                });
                this.giftsMap.put(gift.getCategory(), set);
            }
            set.add(gift);
        }
    }

    public void addGift(GiftInput giftInput) {
        GiftsFactory factory = GiftsFactory.getFactory();
        Gift newGift = factory.createGift(giftInput);
        TreeSet<Gift> set = this.giftsMap.get(newGift.getCategory());
        if (set == null) {
            set = new TreeSet<>(new Comparator<Gift>() {
                @Override
                public int compare(Gift o1, Gift o2) {
                    return Double.compare(o1.getPrice(), o2.getPrice());
                }
            });
            this.giftsMap.put(newGift.getCategory(), set);
        }
        set.add(newGift);
    }

    public LinkedHashMap<Category, TreeSet<Gift>> getGiftsMap() {
        return giftsMap;
    }

    public void setGiftsMap(LinkedHashMap<Category, TreeSet<Gift>> giftsMap) {
        this.giftsMap = giftsMap;
    }
}
