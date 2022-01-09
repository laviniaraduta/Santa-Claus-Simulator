package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import commands.GiftPreferencesCommand;
import enums.Category;
import enums.ChildCategory;
import enums.Cities;
import enums.ElvesType;

import java.util.ArrayList;
import java.util.List;

import static common.Constants.MAX_BABY;
import static common.Constants.MAX_KID;
import static common.Constants.MAX_TEEN;

public final class Child {
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
    @JsonIgnore
    private Double niceScoreBonus;
    @JsonIgnore
    private ElvesType elf;

    public Child() {

    }
    public Child(final Integer id, final String lastName, final String firstName,
                 final Double niceScore, final Integer age, final Cities city,
                 final List<Category> giftsPreferences, final Double niceScoreBonus, final ElvesType elf) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.giftsPreferences = giftsPreferences;
        this.niceScoreHistory.add(niceScore);
        this.setCategory();
        this.niceScoreBonus = niceScoreBonus;
        this.elf = elf;
    }

    // Used for deep copy
    public Child(final Child c) {
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
        this.elf = c.getElf();
        this.niceScoreBonus = c.getNiceScoreBonus();
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(final Cities city) {
        this.city = city;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void setNiceScoreHistory(final List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(final List<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    public ChildCategory getCategory() {
        return category;
    }

    public Double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public void setNiceScoreBonus(Double niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    public ElvesType getElf() {
        return elf;
    }

    public void setElf(ElvesType elf) {
        this.elf = elf;
    }

    /**
     * Decides which age category every child fits in
     */
    public void setCategory() {
        if (Integer.compare(this.age, MAX_BABY) < 0) {
            this.category = ChildCategory.BABY;
        } else if (Integer.compare(this.age, MAX_BABY) >= 0
                && Integer.compare(this.age, MAX_KID) < 0) {
            this.category = ChildCategory.KID;
        } else if (Integer.compare(this.age, MAX_KID) >= 0
                && Integer.compare(this.age, MAX_TEEN) <= 0) {
            this.category = ChildCategory.TEEN;
        } else if (Integer.compare(this.age, MAX_TEEN) > 0) {
            this.category = ChildCategory.YOUNG_ADULT;
        }
    }

    /**
     * Add a score to the history of nice scores of the child
     * @param score the added score
     */
    public void addScore(final Double score) {
        this.niceScoreHistory.add(score);
    }

    /**
     * Updates the list of preferences of a child
     * @param preferences the new preferences added to the list
     */
    public void applyGiftPreferencesCommand(final List<Category> preferences) {
        GiftPreferencesCommand command = new GiftPreferencesCommand(this, preferences);
        command.execute();
    }
}
