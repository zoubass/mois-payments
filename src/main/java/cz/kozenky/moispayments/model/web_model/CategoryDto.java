package cz.kozenky.moispayments.model.web_model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CategoryDto {

    private BigDecimal id;

    @JsonProperty("name")
    private String name;
    private BigDecimal accountId;
    private String accountNumber;
    private String bankCode;

    @JsonProperty("value")
    private BigDecimal summValue;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAccountId() {
        return accountId;
    }

    public void setAccountId(BigDecimal accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public BigDecimal getSummValue() {
        return summValue;
    }

    public void setSummValue(BigDecimal summValue) {
        this.summValue = summValue;
    }

    public boolean isEmpty() {
        return bankCode == null && accountId == null && accountNumber == null && id == null && name == null;
    }
}
