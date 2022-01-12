package databases;

import com.fasterxml.jackson.annotation.JsonIgnore;
import commands.Command;
import commands.AssignBudgetCommand;
import commands.DeleteYoungAdultsCommand;
import commands.IncreaseAgeCommand;
import commands.ReceiveGiftsCommand;
import entities.AnnualChange;
import entities.Child;
import entities.ChildrenFactory;
import enums.ChildCategory;
import enums.CityStrategyEnum;
import fileio.ChildInput;
import fileio.ChildUpdateInput;
import sorting.SortStrategy;
import sorting.SortStrategyFactory;
import scorestrategies.StrategyFactory;
import scorestrategies.AverageScoreStrategy;

import java.util.List;

public final class ChildrenDatabase {
    private List<Child> children;
    @JsonIgnore
    private Double averageScoresSum;

    public ChildrenDatabase(final List<Child> childrenList) {
        this.children = childrenList;
    }
    public List<Child> getChildren() {
        return children;
    }
    public void setChildren(final List<Child> childrenList) {
        this.children = childrenList;
    }

    /**
     * Add a child to the list
     * @param childInput the input data of the new child
     */
    public void addChild(final ChildInput childInput) {
        Child newChild = ChildrenFactory.createChild(childInput);
        this.children.add(newChild);
    }

    public Double getAverageScoresSum() {
        return averageScoresSum;
    }

    /**
     * Calculate the sum of all the nice scores of the children in list
     */
    public void setAverageScoresSum() {
        Double sum = 0d;
        for (Child child : this.children) {
            if (!child.getCategory().equals(ChildCategory.YOUNG_ADULT)) {
                sum += child.getAverageScore();
            }
        }
        this.averageScoresSum = sum;
    }


    /**
     * Calculate the average nice score for each child, according to their category
     */
    public void applyScoreStrategy() {
        for (Child child : this.children) {
            if (child.getCategory().equals(ChildCategory.KID)) {
                AverageScoreStrategy kidAverageScore = StrategyFactory
                        .createStrategy(StrategyFactory.StrategyType.KID_SCORE);
                kidAverageScore.computeAverageScore(child);
            } else if (child.getCategory().equals(ChildCategory.BABY)) {
                AverageScoreStrategy babyAverageScore = StrategyFactory
                        .createStrategy(StrategyFactory.StrategyType.BABY_SCORE);
                babyAverageScore.computeAverageScore(child);
            } else if (child.getCategory().equals(ChildCategory.TEEN)) {
                AverageScoreStrategy teenAverageScore = StrategyFactory
                        .createStrategy(StrategyFactory.StrategyType.TEEN_SCORE);
                teenAverageScore.computeAverageScore(child);
            }
        }
    }

    /**
     * Apply the command (budget/age/gifts)
     * @param command command that needs to be executed
     */
    public void applyCommand(final Command command) {
        command.execute();
    }

    /**
     * Find the child that has the id given in the database
     * @param id the id given
     * @return the child that has that id
     */
    public Child findChildById(final Integer id) {
        for (Child child : this.children) {
            if (child.getId().equals(id)) {
                return child;
            }
        }
        return null;
    }

    /**
     * Update the children database every year according to the year's changes
     * add new children (if there ar new ones), update children attributes,
     * @param change the changes made in the current year
     * @param gifts the gifts database for the current year
     */
    public void update(final AnnualChange change, final GiftsDatabase gifts) {
        this.applyCommand(new IncreaseAgeCommand(this.children));
        for (ChildInput child : change.getNewChildren()) {
            this.addChild(child);
        }
        this.applyCommand(new DeleteYoungAdultsCommand(this.children));
        // update every child's attributes
        for (ChildUpdateInput childUpdate : change.getChildrenUpdates()) {
            Child foundChild = findChildById(childUpdate.getId());
            if (foundChild != null) {
                if (childUpdate.getNiceScore() != null) {
                    foundChild.addScore(childUpdate.getNiceScore());
                }
                if (!childUpdate.getGiftsPreferences().isEmpty()) {
                    foundChild.applyGiftPreferencesCommand(childUpdate.getGiftsPreferences());
                }
                foundChild.setElf(childUpdate.getElf());
            }
        }
        this.applyScoreStrategy();
        this.setAverageScoresSum();
        this.applyCommand(new AssignBudgetCommand(this.children,
                change.getNewSantaBudget(), this.getAverageScoresSum()));
        // sort the children according to the strategy
        SortStrategy strategy = SortStrategyFactory.createSortStrategy(change.getStrategy());
        strategy.sort(this.children);
        this.applyCommand(new ReceiveGiftsCommand(this.children, gifts));
        // sort them back by id to print
        strategy = SortStrategyFactory.createSortStrategy(CityStrategyEnum.ID);
        strategy.sort(this.children);
    }
}
