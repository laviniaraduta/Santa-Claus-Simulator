package entities;

import fileio.ChildInput;
import fileio.GiftInput;

public class GiftsFactory {
    private static GiftsFactory factory = null;
    private GiftsFactory() {
    }

    public static GiftsFactory getFactory() {
        if (factory == null) {
            factory = new GiftsFactory();
        }
        return factory;
    }

    public Gift createGift(GiftInput giftInput) {
        return new Gift(giftInput.getProductName(), giftInput.getPrice(),
                giftInput.getCategory());
    }
}
