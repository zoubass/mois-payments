package cz.kozenky.moispayments.converter;

import cz.kozenky.moispayments.model.Payment;
import cz.kozenky.moispayments.model.Payment.RealizationStatusEnum;
import cz.kozenky.moispayments.model.PaymentAdditionalInfo;
import cz.kozenky.moispayments.model.PaymentValue;
import cz.kozenky.moispayments.model.TransactionPartyAccount;
import cz.kozenky.moispayments.model.codelist.CategoryList;
import cz.kozenky.moispayments.model.web_model.PaymentDto;
import java.math.BigDecimal;
import java.math.MathContext;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;

public class PaymentDtoIntoPaymentMapper extends CustomConverter<PaymentDto, Payment> {

    private CategoryList categoryList;

    public PaymentDtoIntoPaymentMapper(CategoryList categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public Payment convert(PaymentDto source, Type<? extends Payment> destinationType) {
        Payment payment = new Payment();

        PaymentValue paymentValue = new PaymentValue();
        paymentValue.setAmount(source.getAmount());
        paymentValue.setCurrency("CZK");
        payment.setValue(paymentValue);

        payment.setAccountId(source.getAccountId());
        payment.setId(source.getId() != null ? source.getId() : generateId());
        payment.setCategoryId(categoryList.getByName(source.getCategory()).getId());

        TransactionPartyAccount partyAccount = new TransactionPartyAccount();
        partyAccount.setBankCode(source.getBankCode());
        partyAccount.setAccountNumber(source.getAccountNumber());
        payment.setPartyAccount(partyAccount);

        payment.setPayerMessage(source.getPayerMessage());
        payment.setRealizationStatus(RealizationStatusEnum.READY_TO_SEND);

        PaymentAdditionalInfo additionalInfo = new PaymentAdditionalInfo();
        additionalInfo.setConstantSymbol("123");
        additionalInfo.setSpecificSymbol("098");
        additionalInfo.setVariableSymbol("1245");
        payment.setAdditionalInfo(additionalInfo);

        return payment;
    }

    private BigDecimal generateId() {
        return new BigDecimal(Math.random() * 1000 + Math.random(), MathContext.DECIMAL32);
    }
}
