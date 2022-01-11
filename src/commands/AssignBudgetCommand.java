package commands;

import entities.Child;

import java.util.List;

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
            child.setAssignedBudget(budgetUnit * child.getAverageScore());
        }
    }
}
