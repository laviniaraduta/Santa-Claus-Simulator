package enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ChildCategory {
    @JsonProperty("Baby")
    BABY("Baby"),

    @JsonProperty("Kid")
    KID("Kid"),

    @JsonProperty("Teen")
    TEEN("Teen"),

    @JsonProperty("Young Adult")
    YOUNG_ADULT("Young Adult");

    private String value;

    ChildCategory(final String value) {
        this.value = value;
    }
}
