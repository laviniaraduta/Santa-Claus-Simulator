package entities;

import java.util.List;

public class InitialData {
    private List<Child> children;
    private List<Gift> santaGiftsList;

    public InitialData() {

    }
    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public List<Gift> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(List<Gift> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }
}
