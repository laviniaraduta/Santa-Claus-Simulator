package simulation;

import databases.ChildrenDatabase;
import databases.Database;
import databases.GiftsDatabase;
import entities.AnnualChange;
import entities.Child;
import entities.Gift;
import commands.AssignBudgetCommand;
import commands.DeleteYoungAdultsCommand;
import commands.ReceiveGiftsCommand;
import fileio.GameDataInput;
import fileio.GiftInput;
import fileio.Output;

import java.util.List;

public final class Simulation {
    private static Simulation simulation = null;
    private Simulation() {
    }

    /**
     * @return an instance of the Simulation class
     */
    public static Simulation getSimulation() {
        if (simulation == null) {
            simulation = new Simulation();
        }
        return simulation;
    }

    /**
     * Controls the flow of the simulation
     * Applies the changes of each year of simulation
     * @param game the input data for all the simulation
     * @return the output object that will be written in the output file
     */
    public static Output applyRound(final GameDataInput game) {
        Integer round = 0;
        List<Child> childrenList = Database.getChildrenList(game);
        List<Gift> giftList = Database.getGiftsList(game);
        List<AnnualChange> annualChangeList = Database.getAnnualChangeList(game);
        ChildrenDatabase childrenDatabase = new ChildrenDatabase(childrenList);
        GiftsDatabase giftsDatabase = new GiftsDatabase(giftList);

        childrenDatabase.applyCommand(new DeleteYoungAdultsCommand(childrenList));
        childrenDatabase.applyScoreStrategy();
        childrenDatabase.setAverageScoresSum();
        childrenDatabase.applyCommand(new AssignBudgetCommand(childrenList,
                game.getSantaBudget(), childrenDatabase.getAverageScoresSum()));
        childrenDatabase.applyCommand(new ReceiveGiftsCommand(childrenList, giftsDatabase));

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
