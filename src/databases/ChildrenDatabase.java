package databases;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entities.Child;
import entities.ChildrenFactory;
import enums.ChildCategory;
import fileio.ChildInput;
import strategies.age.DeleteYoungAdultsStrategy;
import strategies.budget.AssignedBudgetStrategy;
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
        for (Child child : this.children) {
            AssignedBudgetStrategy strategy = new AssignedBudgetStrategy();
            strategy.computeAssignedBudget(child, totalBudget, this.averageScoresSum);
        }
    }

    public void applyDeleteYoungAdultsStrategy() {
        DeleteYoungAdultsStrategy strategy = new DeleteYoungAdultsStrategy();
        strategy.deleteYoungAdults(this.children);
    }
}
