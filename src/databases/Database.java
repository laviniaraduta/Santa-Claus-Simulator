package databases;

import entities.*;
import fileio.AnnualChangeInput;
import fileio.ChildInput;
import fileio.GameDataInput;
import fileio.GiftInput;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database database = null;
    private Database() {
    }

    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }
    // lista cu copii care initial ii contine pe cei din initial data
    public static List<Child> getChildrenList(GameDataInput game) {
        List<Child> childrenList = new ArrayList<Child>();
        for (int i = 0; i < game.getInitialData().getChildren().size(); i++) {
            ChildrenFactory factory = ChildrenFactory.getFactory();
            Child newChild = factory.createChild(game.getInitialData().getChildren().get(i));
            childrenList.add(newChild);
        }
        return childrenList;
    }
//    // lista de cadouri care initial le contine pe cele din initial data
    public static List<Gift> getGiftsList(GameDataInput game) {
        List<Gift> giftsList = new ArrayList<Gift>();
        for (int i = 0; i < game.getInitialData().getSantaGiftsList().size(); i++) {
            GiftsFactory factory = GiftsFactory.getFactory();
            Gift newGift = factory.createGift(game.getInitialData().getSantaGiftsList().get(i));
            giftsList.add(newGift);
        }
        return giftsList;
    }
//    // lista care contine schimbarile anuale
    public static List<AnnualChange> getAnnualChangeList(GameDataInput game) {
        List<AnnualChange> annualChangeList = new ArrayList<AnnualChange>();
        for (int i = 0; i < game.getAnnualChanges().size(); i++) {
            AnnualChangeInput annualChangeInput = game.getAnnualChanges().get(i);
            AnnualChange newChange = new AnnualChange(annualChangeInput.getNewSantaBudget(), annualChangeInput.getNewGifts(),
                    annualChangeInput.getNewChildren(), annualChangeInput.getChildrenUpdates());
            annualChangeList.add(newChange);
        }
        return annualChangeList;
    }

    public void clearDatabase() {
        database = null;
    }


}
