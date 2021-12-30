package commands;

import entities.Child;
import enums.ChildCategory;
import java.util.Iterator;
import java.util.List;

public final class DeleteYoungAdultsCommand implements Command {
    private List<Child> children;

    public DeleteYoungAdultsCommand(final List<Child> children) {
        this.children = children;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(final List<Child> children) {
        this.children = children;
    }

    /**
     * Removes the children that are now young adults (have > 18 yrs old)
     */
    @Override
    public void execute() {
        Iterator<Child> child = children.iterator();
        while (child.hasNext()) {
            Child next = child.next();
            if (next.getCategory().equals(ChildCategory.YOUNG_ADULT)) {
                child.remove();
            }
        }
    }
}
