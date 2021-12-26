package commands;

import entities.Child;

import java.util.List;

public class AsignBudgetCommand implements Command {
    private List<Child> children;
    private Double totalBudget;
    private Double sum;

    public AsignBudgetCommand(List<Child> children, Double totalBudget, Double sum) {
        this.children = children;
        this.totalBudget = totalBudget;
        this.sum = sum;
    }

    @Override
    public void execute() {
        Double budgetUnit = (Double) totalBudget / sum;
        for (Child child : children) {
            child.setAssignedBudget(budgetUnit * child.getAverageScore());
        }
    }
}
