package entities;

import enums.CityStrategyEnum;
import fileio.ChildInput;
import fileio.ChildUpdateInput;
import fileio.GiftInput;

import java.util.List;

public final class AnnualChange {
    private Double newSantaBudget;
    private List<GiftInput> newGifts;
    private List<ChildInput> newChildren;
    private List<ChildUpdateInput> childrenUpdates;
    private CityStrategyEnum strategy;

    public AnnualChange(final Double newSantaBudget, final List<GiftInput> newGifts,
                        final List<ChildInput> newChildren,
                        final List<ChildUpdateInput> childrenUpdates,
                        final CityStrategyEnum strategy) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
        this.strategy = strategy;
    }

    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(final Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public List<GiftInput> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(final List<GiftInput> newGifts) {
        this.newGifts = newGifts;
    }

    public List<ChildInput> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(final List<ChildInput> newChildren) {
        this.newChildren = newChildren;
    }

    public List<ChildUpdateInput> getChildrenUpdates() {
        return childrenUpdates;
    }

    public void setChildrenUpdates(final List<ChildUpdateInput> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    public CityStrategyEnum getStrategy() {
        return strategy;
    }

    public void setStrategy(final CityStrategyEnum strategy) {
        this.strategy = strategy;
    }
}
