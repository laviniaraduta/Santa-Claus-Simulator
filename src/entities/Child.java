package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.Category;
import enums.ChildCategory;
import enums.Cities;
import strategies.gift.GiftPreferencesStrategy;

import java.util.ArrayList;
import java.util.List;

public class Child {
    private Integer id;
    private String lastName;
    private String firstName;
    private Cities city;
    private Integer age;
    private List<Category> giftsPreferences;
    private Double averageScore;
    private List<Double> niceScoreHistory = new ArrayList<Double>();
    private Double assignedBudget;
    private List<Gift> receivedGifts = new ArrayList<Gift>();
    @JsonIgnore
    private ChildCategory category;

    public Child() {

    }
    public Child(Integer id, String lastName, String firstName, Double niceScore,
                 Integer age, Cities city, List<Category> giftsPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.giftsPreferences = giftsPreferences;
        this.niceScoreHistory.add(niceScore);
        this.setCategory();
    }

    public Child(Child c) {
        this.id = c.getId();
        this.lastName = c.getLastName();
        this.firstName = c.getFirstName();
        this.age = c.getAge();
        this.city = c.getCity();
        this.giftsPreferences = new ArrayList<Category>();
        this.giftsPreferences.addAll(c.getGiftsPreferences());
        this.niceScoreHistory.addAll(c.getNiceScoreHistory());
        this.category = c.getCategory();
        this.receivedGifts = new ArrayList<Gift>();
        this.receivedGifts.addAll(c.getReceivedGifts());
        this.assignedBudget = c.getAssignedBudget();
        this.averageScore = c.getAverageScore();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void setNiceScoreHistory(List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(List<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    public ChildCategory getCategory() {
        return category;
    }

    public void setCategory() {
        if (Integer.compare(this.age, 5) < 0) {
            this.category = ChildCategory.BABY;
        } else if (Integer.compare(this.age, 5) >= 0 && Integer.compare(this.age, 12) < 0) {
            this.category = ChildCategory.KID;
        } else if (Integer.compare(this.age, 12) >= 0 && Integer.compare(this.age, 18) <= 0) {
            this.category = ChildCategory.TEEN;
        } else if (Integer.compare(this.age, 18) > 0) {
            this.category = ChildCategory.YOUNG_ADULT;
        }
    }

    public void addScore(Double score) {
        this.niceScoreHistory.add(score);
    }

    public void applyGiftPreferencesStrategy(List<Category> preferences) {
        GiftPreferencesStrategy strategy = new GiftPreferencesStrategy();
        strategy.addPreferences(this, preferences);
    }
}
