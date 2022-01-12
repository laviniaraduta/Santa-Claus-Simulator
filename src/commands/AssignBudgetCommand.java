package commands;

import entities.Child;

import java.util.List;

import static common.Constants.FULL_PERCENT;
import static common.Constants.PERCENT;
import static enums.ElvesType.BLACK;
import static enums.ElvesType.PINK;

public final class AssignBudgetCommand implements Command {
    private List<Child> children;
    private Double totalBudget;
    private Double sum;

    public AssignBudgetCommand(final List<Child> children,
                               final Double totalBudget,
                               final Double sum) {
        this.children = children;
        this.totalBudget = totalBudget;
        this.sum = sum;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(final List<Child> children) {
        this.children = children;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(final Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(final Double sum) {
        this.sum = sum;
    }

    /**
     * Assign to each child their budget
     */
    @Override
    public void execute() {
        Double budgetUnit = (Double) totalBudget / sum;
        for (Child child : children) {
            Double budget = budgetUnit * child.getAverageScore();
            if (child.getElf().equals(BLACK)) {
                budget -= budget * PERCENT / FULL_PERCENT;
                child.setAssignedBudget(budget);
            } else if (child.getElf().equals(PINK)) {
                budget += budget * PERCENT / FULL_PERCENT;
                child.setAssignedBudget(budget);
            } else {
                child.setAssignedBudget(budget);
            }

        }
    }
}
