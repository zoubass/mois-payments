package cz.kozenky.moispayments.model.codelist;

import java.math.BigDecimal;

public class CategoryRule {

    private BigDecimal accountId;
    private String bankCode;
    private String bankAccountNumber;
    
    public BigDecimal getAccountId() {
        return accountId;
    }

    public void setAccountId(BigDecimal accountId) {
        this.accountId = accountId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
}
