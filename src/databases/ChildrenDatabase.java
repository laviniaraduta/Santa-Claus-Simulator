package databases;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entities.AnnualChange;
import entities.Child;
import entities.ChildrenFactory;
import enums.Category;
import enums.ChildCategory;
import fileio.ChildInput;
import fileio.ChildUpdateInput;
import strategies.age.DeleteYoungAdultsStrategy;
import strategies.age.IncreaseAgeStrategy;
import strategies.budget.AssignedBudgetStrategy;
import strategies.gift.GiftPreferencesStrategy;
import strategies.score.BabyAverageScore;
import strategies.score.KidAverageScore;
import strategies.score.TeenAverageScore;

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
        ChildrenFactory factory = ChildrenFactory.getFactory();
        Child newChild = factory.createChild(childInput);
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
                KidAverageScore kidAverageScore = new KidAverageScore();
                kidAverageScore.computeAverageScore(child);
            } else if (child.getCategory().equals(ChildCategory.BABY)) {
                BabyAverageScore babyAverageScore = new BabyAverageScore();
                babyAverageScore.computeAverageScore(child);
            } else if (child.getCategory().equals(ChildCategory.TEEN)) {
                TeenAverageScore teenAverageScore = new TeenAverageScore();
                teenAverageScore.computeAverageScore(child);
            }
        }
    }

    public void applyAssignedBudgetStrategy(Double totalBudget) {
        AssignedBudgetStrategy strategy = new AssignedBudgetStrategy();
        for (Child child : this.children) {
            strategy.computeAssignedBudget(child, totalBudget, this.averageScoresSum);
        }
    }

    public void applyDeleteYoungAdultsStrategy() {
        DeleteYoungAdultsStrategy strategy = new DeleteYoungAdultsStrategy();
        strategy.deleteYoungAdults(this.children);
    }

    public void applyIncreaseAgeStrategy() {
        IncreaseAgeStrategy strategy = new IncreaseAgeStrategy();
        for (Child child : this.children) {
            strategy.computeNewAge(child);
        }
    }

    public Child findChildById(Integer id) {
        for (Child child : this.children) {
            if (child.getId().equals(id)) {
                return child;
            }
        }
        return null;
    }

    public void update(AnnualChange change) {
        this.applyIncreaseAgeStrategy();
        for (ChildInput child : change.getNewChildren()) {
            this.addChild(child);
        }
        this.applyDeleteYoungAdultsStrategy();
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
        this.applyAssignedBudgetStrategy(change.getNewSantaBudget());
    }
}
