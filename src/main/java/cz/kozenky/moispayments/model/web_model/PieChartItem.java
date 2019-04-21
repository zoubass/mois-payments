package cz.kozenky.moispayments.model.web_model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class PieChartItem {
    @JsonProperty("value")
    private BigDecimal value = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("categoryId")
    private BigDecimal categoryId = null;

    public PieChartItem(BigDecimal value, String name, BigDecimal categoryId) {
        this.value = value;
        this.name = name;
        this.categoryId = categoryId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(BigDecimal categoryId) {
        this.categoryId = categoryId;
    }
}
