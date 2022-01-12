package fileio;

import enums.Category;
import enums.ElvesType;

import java.util.List;

public final  class ChildUpdateInput {
    private Integer id;
    private Double niceScore;
    private List<Category> giftsPreferences;
    private ElvesType elf;

    public ChildUpdateInput() {

    }
    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public ElvesType getElf() {
        return elf;
    }

    public void setElf(final ElvesType elf) {
        this.elf = elf;
    }
}
