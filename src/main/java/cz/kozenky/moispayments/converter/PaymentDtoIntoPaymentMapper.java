package cz.kozenky.moispayments.converter;

import cz.kozenky.moispayments.model.Payment;
import cz.kozenky.moispayments.model.Payment.RealizationStatusEnum;
import cz.kozenky.moispayments.model.PaymentAdditionalInfo;
import cz.kozenky.moispayments.model.PaymentRecuringPayment;
import cz.kozenky.moispayments.model.PaymentRecuringPayment.IntervalEnum;
import cz.kozenky.moispayments.model.PaymentValue;
import cz.kozenky.moispayments.model.TransactionPartyAccount;
import cz.kozenky.moispayments.model.codelist.CategoryList;
import cz.kozenky.moispayments.model.web_model.PaymentDto;
import java.math.BigDecimal;
import java.util.Random;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

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

        TransactionPartyAccount partyAccount = new TransactionPartyAccount();

        partyAccount.setPrefix("48");
        partyAccount.setAccountNumber(source.getAccountNumber());
        partyAccount.setBankCode(source.getBankCode());
        payment.setPartyAccount(partyAccount);
        
        payment.setDueDate(source.getDueDate());

        PaymentRecuringPayment recuringPayment = new PaymentRecuringPayment();
        recuringPayment.firstPayment(DateTime.now());
        recuringPayment.lastPayment(DateTime.now());
        recuringPayment.interval(IntervalEnum.WEEK);
        payment.setRecuringPayment(recuringPayment);


        payment.setPayerMessage(StringUtils.isEmpty(source.getPayerMessage())? "" : source.getPayerMessage());
        payment.setPayeeMessage("");
        payment.setCategoryId(categoryList.getByName(source.getCategory()).getId());
        PaymentAdditionalInfo additionalInfo = new PaymentAdditionalInfo();
        additionalInfo.setConstantSymbol("8080");
        additionalInfo.setVariableSymbol("861225");
        additionalInfo.setSpecificSymbol("8");
        payment.setAdditionalInfo(additionalInfo);

        payment.setId(source.getId() != null? source.getId() : generateId());
        payment.setAccountId(new BigDecimal(123));
        payment.setEditableByUser(true);
        payment.setRealizationStatus(RealizationStatusEnum.EDITED);
        
        return payment;
    }

    private String generateId() {
        Random rn = new Random();
        return String.valueOf(rn.nextInt(1000 - 20 + 1) + 20);
    }
}
