package fileio;

import databases.ChildrenDatabase;
import entities.Child;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Output {
    private List<ChildrenDatabase> annualChildren = new ArrayList<ChildrenDatabase>();

    public List<ChildrenDatabase> getAnnualChildren() {
        return annualChildren;
    }

    public void setAnnualChildren(final List<ChildrenDatabase> annualChildren) {
        this.annualChildren = annualChildren;
    }

    /**
     * Add the database of children from the current year to the list
     * @param database the database that needs to be written in the output file
     */
    public void addDatabase(final ChildrenDatabase database) {
        List<Child> copy = new ArrayList<Child>();
        for (Child child : database.getChildren()) {
            copy.add(new Child(child));
        }
        ChildrenDatabase childrenDatabase =
                new ChildrenDatabase(Collections.unmodifiableList(copy));
        this.annualChildren.add(childrenDatabase);
    }
}
