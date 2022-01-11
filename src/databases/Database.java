package databases;


import entities.Child;
import entities.ChildrenFactory;
import entities.Gift;
import entities.GiftsFactory;
import entities.AnnualChange;
import fileio.AnnualChangeInput;
import fileio.GameDataInput;

import java.util.ArrayList;
import java.util.List;

public final class Database {

    // Make the database singleton
    private static Database database = null;
    private Database() {
    }

    /**
     * @return the instance of the database
     */
    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    /**
     * Returns the list containing all the children that are initially in the game
     * it transfers the children from the input class to the game's database
     * @param game the input data about the simulation
     * @return a list of children that will be used in the simulation
     */
    public static List<Child> getChildrenList(final GameDataInput game) {
        List<Child> childrenList = new ArrayList<Child>();
        for (int i = 0; i < game.getInitialData().getChildren().size(); i++) {
            ChildrenFactory factory = ChildrenFactory.getFactory();
            Child newChild = factory.createChild(game.getInitialData().getChildren().get(i));
            childrenList.add(newChild);
        }
        return childrenList;
    }
    /**
     * Returns the list containing all the gifts that are initially in the game
     * it transfers the gifts from the input class to the game's database
     * @param game the input data about the simulation
     * @return a list of gifts that will be used in the simulation
     */

    public static List<Gift> getGiftsList(final GameDataInput game) {
        List<Gift> giftsList = new ArrayList<Gift>();
        for (int i = 0; i < game.getInitialData().getSantaGiftsList().size(); i++) {
            GiftsFactory factory = GiftsFactory.getFactory();
            Gift newGift = factory.createGift(game.getInitialData().getSantaGiftsList().get(i));
            giftsList.add(newGift);
        }
        return giftsList;
    }

    /**
     * Returns a list containing the annual changes of each year o the simulation
     * @param game the input data of the simulation
     * @return a list of the annual changes
     */
    public static List<AnnualChange> getAnnualChangeList(final GameDataInput game) {
        List<AnnualChange> annualChangeList = new ArrayList<AnnualChange>();
        for (int i = 0; i < game.getAnnualChanges().size(); i++) {
            AnnualChangeInput annualChangeInput = game.getAnnualChanges().get(i);
            AnnualChange newChange = new AnnualChange(annualChangeInput.getNewSantaBudget(),
                    annualChangeInput.getNewGifts(), annualChangeInput.getNewChildren(),
                    annualChangeInput.getChildrenUpdates(), annualChangeInput.getStrategy());
            annualChangeList.add(newChange);
        }
        return annualChangeList;
    }
}
