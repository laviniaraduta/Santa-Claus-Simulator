package fileio;

import databases.ChildrenDatabase;
import entities.Child;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Output {
    private List<ChildrenDatabase> annualChildren = new ArrayList<ChildrenDatabase>();

    public List<ChildrenDatabase> getAnnualChildren() {
        return annualChildren;
    }

    public void setAnnualChildren(List<ChildrenDatabase> annualChildren) {
        this.annualChildren = annualChildren;
    }

    public void addDatabase(ChildrenDatabase database) {
        List<Child> copy = new ArrayList<Child>();
        for (Child child : database.getChildren()) {
            copy.add(new Child(child));
        }
        ChildrenDatabase cd = new ChildrenDatabase(Collections.unmodifiableList(copy));
        this.annualChildren.add(cd);
    }
}
