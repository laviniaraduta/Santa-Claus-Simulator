package commands;

import entities.Child;

import java.util.List;

public class IncreaseAgeCommand implements Command{
    private List<Child> children;

    public IncreaseAgeCommand(List<Child> children) {
        this.children = children;
    }

    @Override
    public void execute() {
        for (Child child : children) {
            child.setAge(child.getAge() + 1);
            child.setCategory();
        }
    }
}
