package cz.kozenky.moispayments.controller;

import cz.kozenky.moispayments.model.Payment;
import cz.kozenky.moispayments.service.PaymentsService;
import java.math.BigDecimal;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private PaymentsService paymentsService;

    @RequestMapping("/findPayments")
    public List<Payment> getPayments() {
        DateTime from = DateTime.now().minusYears(7);
        DateTime to = DateTime.now().plusYears(1);
        BigDecimal accountId = new BigDecimal("123");

        return paymentsService.findPayments(from, to, accountId);
    }
}
