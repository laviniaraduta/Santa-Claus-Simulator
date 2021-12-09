package fileio;

import java.util.List;

public class InitialDataInput {
    private List<ChildInput> children;
    private List<GiftInput> santaGiftsList;

    public InitialDataInput() {

    }
    public List<ChildInput> getChildren() {
        return children;
    }

    public void setChildren(List<ChildInput> children) {
        this.children = children;
    }

    public List<GiftInput> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(List<GiftInput> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }
}
