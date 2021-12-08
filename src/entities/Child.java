package entities;

import enums.Category;

import java.util.List;

public class Child {
    private Integer id;
    private String lastname;
    private String firstname;
    private Integer age;
    private Double niceScore;
    private List<Double> niceScores;
    private List<Category> giftsPreference;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(Double niceScore) {
        this.niceScore = niceScore;
    }

    public List<Double> getNiceScores() {
        return niceScores;
    }

    public void setNiceScores(List<Double> niceScores) {
        this.niceScores = niceScores;
    }

    public List<Category> getGiftsPreference() {
        return giftsPreference;
    }

    public void setGiftsPreference(List<Category> giftsPreference) {
        this.giftsPreference = giftsPreference;
    }
}
