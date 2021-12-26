package databases;

import com.fasterxml.jackson.annotation.JsonIgnore;
import commands.*;
import entities.AnnualChange;
import entities.Child;
import entities.ChildrenFactory;
import enums.ChildCategory;
import fileio.ChildInput;
import fileio.ChildUpdateInput;
import strategies.StrategyFactory;
import strategies.AverageScoreStrategy;

import java.util.List;

public class ChildrenDatabase {
    private List<Child> children;
    @JsonIgnore
    private Double averageScoresSum;

    public ChildrenDatabase(List<Child> childrenList) {
        this.children = childrenList;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> childrenList) {
        this.children = childrenList;
    }
    public void addChild(ChildInput childInput) {
        Child newChild = ChildrenFactory.createChild(childInput);
        this.children.add(newChild);
    }

    public Double getAverageScoresSum() {
        return averageScoresSum;
    }

    public void setAverageScoresSum() {
        Double sum = 0d;
        for (Child child : this.children) {
            if (!child.getCategory().equals(ChildCategory.YOUNG_ADULT)) {
                sum += child.getAverageScore();
            }
        }
        this.averageScoresSum = sum;
    }

    public void applyScoreStrategy() {
        for (Child child : this.children) {
            if (child.getCategory().equals(ChildCategory.KID)) {
                AverageScoreStrategy kidAverageScore = StrategyFactory.createStrategy(StrategyFactory.StrategyType.KID_SCORE);
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

    public void applyCommand(Command command) {
        command.execute();
    }

    public Child findChildById(Integer id) {
        for (Child child : this.children) {
            if (child.getId().equals(id)) {
                return child;
            }
        }
        return null;
    }

    public void update(AnnualChange change, GiftsDatabase gifts) {
        this.applyCommand(new IncreaseAgeCommand(this.children));
        for (ChildInput child : change.getNewChildren()) {
            this.addChild(child);
        }
        this.applyCommand(new DeleteYoungAdultsCommand(this.children));
        for (ChildUpdateInput childUpdate : change.getChildrenUpdates()) {
            Child foundChild = findChildById(childUpdate.getId());
            if (foundChild != null) {
                if (childUpdate.getNiceScore() != null) {
                    foundChild.addScore(childUpdate.getNiceScore());
                }
                if (!childUpdate.getGiftsPreferences().isEmpty()) {
                    foundChild.applyGiftPreferencesStrategy(childUpdate.getGiftsPreferences());
                }
            }
        }
        this.applyScoreStrategy();
        this.setAverageScoresSum();
        this.applyCommand(new AsignBudgetCommand(this.children, change.getNewSantaBudget(), this.getAverageScoresSum()));
        this.applyCommand(new ReceiveGiftsCommand(this.children, gifts));
    }
}
