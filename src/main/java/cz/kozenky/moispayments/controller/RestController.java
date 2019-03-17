package cz.kozenky.moispayments.controller;

import cz.kozenky.moispayments.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private PaymentsService paymentsService;

    @RequestMapping("/test")
    public String getPayments() {
        paymentsService.test();
        return "I did it.";
    }
}
