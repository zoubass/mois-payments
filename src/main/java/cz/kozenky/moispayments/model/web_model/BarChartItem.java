package cz.kozenky.moispayments.model.web_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.kozenky.moispayments.model.enumObj.MonthsInYear;

import java.math.BigDecimal;

public class BarChartItem {
    @JsonProperty("monthInYear")
    private MonthsInYear monthsInYear;

    @JsonProperty("value")
    private BigDecimal value = null;

    @JsonProperty("categoryId")
    private BigDecimal categoryId = null;

    public MonthsInYear getMonthsInYear() {
        return monthsInYear;
    }

    public void setMonthsInYear(MonthsInYear monthsInYear) {
        this.monthsInYear = monthsInYear;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(BigDecimal categoryId) {
        this.categoryId = categoryId;
    }

    public BarChartItem(MonthsInYear monthsInYear, BigDecimal value, BigDecimal categoryId) {
        this.monthsInYear = monthsInYear;
        this.value = value;
        this.categoryId = categoryId;
    }

    public BarChartItem() {
    }
}
