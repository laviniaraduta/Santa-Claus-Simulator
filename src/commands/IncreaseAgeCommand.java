package commands;

import entities.Child;

import java.util.List;

public final class IncreaseAgeCommand implements Command {
    private List<Child> children;

    public IncreaseAgeCommand(final List<Child> children) {
        this.children = children;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(final List<Child> children) {
        this.children = children;
    }

    /**
     * Increase the age of the children after each year
     */
    @Override
    public void execute() {
        for (Child child : children) {
            child.setAge(child.getAge() + 1);
            child.setCategory();
        }
    }
}
