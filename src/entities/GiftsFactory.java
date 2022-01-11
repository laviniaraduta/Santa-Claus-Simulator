package entities;

import fileio.GiftInput;

public final class GiftsFactory {
    private static GiftsFactory factory = null;
    private GiftsFactory() {
    }

    /**
     * @return the instance of the singleton class
     */
    public static GiftsFactory getFactory() {
        if (factory == null) {
            factory = new GiftsFactory();
        }
        return factory;
    }

    /**
     * Returns the Gift formed from the input data received
     * @param giftInput the input data read
     * @return a Gift object
     */
    public Gift createGift(final GiftInput giftInput) {
        return new Gift(giftInput.getProductName(), giftInput.getPrice(),
                giftInput.getCategory(), giftInput.getQuantity());
    }
}
