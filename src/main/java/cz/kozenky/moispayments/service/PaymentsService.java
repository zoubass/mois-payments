package cz.kozenky.moispayments.service;

import cz.kozenky.moispayments.model.Payment;
import java.math.BigDecimal;
import java.util.List;
import org.joda.time.DateTime;

public interface PaymentsService {

    List<Payment> findPayments(DateTime from, DateTime to, BigDecimal accountId);
    
    Payment savePayment(Payment payment);

}
