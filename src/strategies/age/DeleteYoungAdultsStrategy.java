package strategies.age;

import entities.Child;
import enums.ChildCategory;

import java.util.Iterator;
import java.util.List;

public class DeleteYoungAdultsStrategy {
    public void deleteYoungAdults(List<Child> children) {
        Iterator<Child> child = children.iterator();
        while (child.hasNext()) {
            Child next = child.next();
            if (next.getCategory().equals(ChildCategory.YOUNG_ADULT)){
                child.remove();
            }
        }
    }
}
