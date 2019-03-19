package cz.kozenky.moispayments.controller;

import cz.kozenky.moispayments.model.Payment;
import cz.kozenky.moispayments.model.PaymentValue;
import cz.kozenky.moispayments.service.PaymentsService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private PaymentsService paymentsService;

    @RequestMapping("/findPayments")
    public String getPayments() {
        DateTime from = DateTime.now().minusYears(7);
        DateTime to = DateTime.now().plusYears(1);
        BigDecimal accountId = new BigDecimal("123");

        List<Payment> payments = paymentsService.findPayments(from, to, accountId);
        return Optional.ofNullable(payments.get(0))
                .map(Payment::getValue)
                .map(PaymentValue::getAmount)
                .map(BigDecimal::toString)
                .orElse("Result is empty.");
    }
}
