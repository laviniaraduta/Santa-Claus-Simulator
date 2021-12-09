package simulation;

import databases.ChildrenDatabase;
import databases.Database;
import databases.GiftsDatabase;
import entities.AnnualChange;
import entities.Child;
import entities.Gift;
import fileio.*;

import java.util.List;

public class Simulation {
    private Simulation() {

    }
    public static Output applyRound(GameDataInput game) {
        Integer round = 0;
        List<Child> childrenList = Database.getChildrenList(game);
        List<Gift> giftList = Database.getGiftsList(game);
        List<AnnualChange> annualChangeList = Database.getAnnualChangeList(game);
        ChildrenDatabase childrenDatabase = new ChildrenDatabase(childrenList);
        GiftsDatabase giftsDatabase = new GiftsDatabase(giftList);
        childrenDatabase.applyDeleteYoungAdultsStrategy();
        childrenDatabase.applyScoreStrategy();
        childrenDatabase.setAverageScoresSum();
        childrenDatabase.applyAssignedBudgetStrategy(game.getSantaBudget());
        Output output = new Output();
        output.addDatabase(childrenDatabase);

        while (round < game.getNumberOfYears()) {
            for (ChildInput child : annualChangeList.get(round).getNewChildren()) {
                childrenDatabase.addChild(child);
            }
            for (GiftInput gift : annualChangeList.get(round).getNewGifts()) {
                giftsDatabase.addGift(gift);
            }
            round++;
        }
        Database.getDatabase().clearDatabase();
        return output;
    }
}
