package databases;

import entities.AnnualChange;
import entities.Child;
import entities.GameData;
import entities.Gift;

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
    public static List<Child> getChildrenList(GameData game) {
        List<Child> childrenList = new ArrayList<Child>();
        for (int i = 0; i < game.getInitialData().getChildren().size(); i++) {
            Child oldChild = game.getInitialData().getChildren().get(i);
            Child newChild = new Child(oldChild.getId(), oldChild.getLastName(),
                    oldChild.getFirstName(), oldChild.getAge(), oldChild.getCity(),
                    oldChild.getNiceScore(), oldChild.getGiftsPreferences());
            childrenList.add(newChild);
        }
        return childrenList;
    }
    // lista de cadouri care initial le contine pe cele din initial data
    public static List<Gift> getGiftsList(GameData game) {
        List<Gift> giftsList = new ArrayList<Gift>();
        for (int i = 0; i < game.getInitialData().getSantaGiftsList().size(); i++) {
            Gift oldGift = game.getInitialData().getSantaGiftsList().get(i);
            Gift newGift = new Gift(oldGift.getProductName(), oldGift.getPrice(),
                    oldGift.getCategory());
            giftsList.add(newGift);
        }
        return giftsList;
    }
    // lista care contine schimbarile anuale
    public static List<AnnualChange> getAnnualChangeList(GameData game) {
        List<AnnualChange> annualChangeList = new ArrayList<AnnualChange>();
        for (int i = 0; i < game.getAnnualChanges().size(); i++) {
            AnnualChange old = game.getAnnualChanges().get(i);
            AnnualChange newChange = new AnnualChange(old.getNewSantaBudget(), old.getNewGifts(),
                    old.getNewChildren(), old.getChildrenUpdates());
            annualChangeList.add(newChange);
        }
        return annualChangeList;
    }


}
