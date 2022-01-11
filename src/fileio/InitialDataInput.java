package fileio;

import java.util.List;

public final class InitialDataInput {
    private List<ChildInput> children;
    private List<GiftInput> santaGiftsList;

    public InitialDataInput() {

    }
    public List<ChildInput> getChildren() {
        return children;
    }

    public void setChildren(final List<ChildInput> children) {
        this.children = children;
    }

    public List<GiftInput> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(final List<GiftInput> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }
}
