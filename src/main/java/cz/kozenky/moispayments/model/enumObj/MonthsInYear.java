package cz.kozenky.moispayments.model.enumObj;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MonthsInYear {
    JANUARY("January", 1),
    FEBRUARY("February", 2),
    MARCH("March", 3),
    APRIL("April", 4),
    MAY("May", 5),
    JUNE("June", 6),
    JULY("July", 7),
    AUGUST("August", 8),
    SEPTEMBER("September", 9),
    OCTOBER("October", 10),
    NOVEMBER("November", 11),
    DECEMBER("December", 12),
    UNKNOWN("Unknown", -1);

    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private int val;

    public String getName() {
        return name;
    }

    public int getVal() {
        return val;
    }

    MonthsInYear(String name, int val) {
        this.name = name;
        this.val = val;
    }
}
