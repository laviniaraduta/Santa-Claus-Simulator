package commands;

import entities.Child;
import enums.ChildCategory;

import java.util.Iterator;
import java.util.List;

public class DeleteYoungAdultsCommand implements Command{
    private List<Child> children;

    public DeleteYoungAdultsCommand(List<Child> children) {
        this.children = children;
    }

    @Override
    public void execute() {
        Iterator<Child> child = children.iterator();
        while (child.hasNext()) {
            Child next = child.next();
            if (next.getCategory().equals(ChildCategory.YOUNG_ADULT)){
                child.remove();
            }
        }
    }
}
