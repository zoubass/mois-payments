package cz.kozenky.moispayments.model.web_model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonthItem {
    @JsonProperty("month")
    private int month = -1;

    @JsonProperty("monthS")
    private String monthS = null;

    @JsonProperty("year")
    private int year = -1;

    public MonthItem(int month, String monthS, int year) {
        this.month = month;
        this.monthS = monthS;
        this.year = year;
    }

    public MonthItem() {
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonthS() {
        return monthS;
    }

    public void setMonthS(String monthS) {
        this.monthS = monthS;
    }
}
