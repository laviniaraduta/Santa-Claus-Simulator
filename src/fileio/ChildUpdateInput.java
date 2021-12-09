package fileio;

import enums.Category;

import java.util.List;

public class ChildUpdateInput {
    private Integer id;
    private Double niceScore;
    private List<Category> giftsPreferences;

    public ChildUpdateInput() {

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(Double niceScore) {
        this.niceScore = niceScore;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }
}
