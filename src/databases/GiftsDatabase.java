package databases;

import entities.Gift;
import entities.GiftsFactory;
import fileio.GiftInput;

import java.util.List;

public class GiftsDatabase {
    private List<Gift> giftList;

    public GiftsDatabase(List<Gift> giftList) {
        this.giftList = giftList;
    }

    public void addGift(GiftInput giftInput) {
        GiftsFactory factory = GiftsFactory.getFactory();
        Gift newGift = factory.createGift(giftInput);
        this.giftList.add(newGift);
    }

    public List<Gift> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<Gift> giftList) {
        this.giftList = giftList;
    }
}
