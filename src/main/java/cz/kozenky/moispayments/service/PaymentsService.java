package cz.kozenky.moispayments.service;

import cz.kozenky.moispayments.model.Payment;
import cz.kozenky.moispayments.model.codelist.Category;
import cz.kozenky.moispayments.model.web_model.PaymentDto;
import java.math.BigDecimal;
import java.util.List;
import org.joda.time.DateTime;

public interface PaymentsService {

    List<Payment> findPayments(DateTime from, DateTime to, BigDecimal accountId);

    Payment savePayment(Payment payment);

    Payment savePayment(PaymentDto paymentDto);

    Category resolveCategory(Payment payment);

}
