package cz.kozenky.moispayments.service;

import cz.kozenky.moispayments.api.PaymentApi;
import cz.kozenky.moispayments.model.Payment;
import cz.kozenky.moispayments.model.codelist.Category;
import cz.kozenky.moispayments.model.codelist.CategoryList;
import cz.kozenky.moispayments.model.web_model.PaymentDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import ma.glasnost.orika.MapperFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    private PaymentApi paymentApi;

    @Autowired
    private MapperFactory mapperFactory;

    @Autowired
    private CategoryList categoryList;

    @Autowired
    public PaymentsServiceImpl(PaymentApi paymentApi) {
        this.paymentApi = paymentApi;
    }

    @Override
    public List<Payment> findPayments(DateTime from, DateTime to, BigDecimal accountId) {
        List<Payment> payments = new ArrayList<>();

        try {
            payments = paymentApi.findPaymentByDate(from, to, accountId);
        } catch (RestClientException e) {
            //Only for breakpoint
            e.printStackTrace();
        }
        return payments;
    }

    public Payment savePayment(PaymentDto paymentDto) {
        Payment payment = mapperFactory.getMapperFacade().map(paymentDto, Payment.class);
        return savePayment(payment);
    }

    @Override
    public Category resolveCategory(Payment payment) {
        return categoryList
                .resolveByRule(payment.getPartyAccount().getAccountNumber(), payment.getPartyAccount().getBankCode(), payment.getAccountId());
    }

    @Override
    public Payment savePayment(Payment payment) {
        Payment responsePayment = null;

        try {
            responsePayment = paymentApi.addPayment(payment);
        } catch (Exception e) {
            //Only for debug
            e.printStackTrace();
        }
        return responsePayment;
    }
}
