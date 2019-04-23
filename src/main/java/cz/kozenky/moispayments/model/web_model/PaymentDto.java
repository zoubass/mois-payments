package cz.kozenky.moispayments.model.web_model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import cz.kozenky.moispayments.model.deserializers.CustomJsonDateDeserializer;
import java.math.BigDecimal;
import org.joda.time.DateTime;

public class PaymentDto {

    private String id;
    private BigDecimal amount;
    private BigDecimal accountId;
    private String payerMessage;
    private String accountNumber;
    private String bankCode;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private DateTime dueDate;
    private String category;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAccountId() {
        return accountId;
    }

    public void setAccountId(BigDecimal accountId) {
        this.accountId = accountId;
    }

    public String getPayerMessage() {
        return payerMessage;
    }

    public void setPayerMessage(String payerMessage) {
        this.payerMessage = payerMessage;
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

    public DateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(DateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
