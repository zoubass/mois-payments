package cz.kozenky.moispayments.model.codelist;

import java.math.BigDecimal;

/**
    Serves mainly for newly user defined categories and their rules and as DTO for FE.
 */
public class Category {

    private String name;
    private BigDecimal id;
    private CategoryRule rule;

    public Category(String name, BigDecimal id) {
        this.name = name;
        this.id = id;
    }

    public Category(String name, BigDecimal id, CategoryRule rule) {
        this.name = name;
        this.id = id;
        this.rule = rule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public CategoryRule getRule() {
        return rule;
    }

    public void setRule(CategoryRule rule) {
        this.rule = rule;
    }
}
