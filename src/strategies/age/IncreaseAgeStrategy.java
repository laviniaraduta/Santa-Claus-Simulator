package strategies.age;

import entities.Child;

public class IncreaseAgeStrategy {
    public void computeNewAge(Child child) {
        child.setAge(child.getAge() + 1);
        child.setCategory();
    }
}
