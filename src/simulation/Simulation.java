package simulation;

import databases.Database;
import entities.AnnualChange;
import entities.Child;
import entities.GameData;
import entities.Gift;

import java.util.List;

public class Simulation {

    public static void applyRound(GameData game) {
        Integer round = 0;
        List<Child> childrenList = Database.getChildrenList(game);
        List<Gift> giftList = Database.getGiftsList(game);
        List<AnnualChange> annualChangeList = Database.getAnnualChangeList(game);

    }
}
