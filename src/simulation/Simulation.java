package simulation;

import databases.ChildrenDatabase;
import databases.Database;
import databases.GiftsDatabase;
import entities.AnnualChange;
import entities.Child;
import entities.Gift;
import fileio.*;
import commands.AsignBudgetCommand;
import commands.DeleteYoungAdultsCommand;
import commands.ReceiveGiftsCommand;

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

        childrenDatabase.applyCommand(new DeleteYoungAdultsCommand(childrenList));
//        childrenDatabase.applyDeleteYoungAdultsStrategy();
        childrenDatabase.applyScoreStrategy();
        childrenDatabase.setAverageScoresSum();
        childrenDatabase.applyCommand(new AsignBudgetCommand(childrenList, game.getSantaBudget(), childrenDatabase.getAverageScoresSum()));
        childrenDatabase.applyCommand(new ReceiveGiftsCommand(childrenList, giftsDatabase));
//        childrenDatabase.applyAssignedBudgetStrategy(game.getSantaBudget());
//        childrenDatabase.applyReceiveGiftsStrategy(giftsDatabase);

        Output output = new Output();
        output.addDatabase(childrenDatabase);

        while (round < game.getNumberOfYears()) {
            game.setSantaBudget(annualChangeList.get(round).getNewSantaBudget());
            for (GiftInput gift : annualChangeList.get(round).getNewGifts()) {
                giftsDatabase.addGift(gift);
            }
            childrenDatabase.update(annualChangeList.get(round), giftsDatabase);
            round++;
            output.addDatabase(childrenDatabase);
        }
        return output;
    }
}
