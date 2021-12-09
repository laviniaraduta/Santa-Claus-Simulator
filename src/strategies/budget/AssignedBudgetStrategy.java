package strategies.budget;

import entities.Child;

public class AssignedBudgetStrategy {
    public void computeAssignedBudget(Child child, Double totalBudget, Double sum) {
        Double budgetUnit = (Double) totalBudget / sum;
        child.setAssignedBudget(budgetUnit * child.getAverageScore());
    }
}
