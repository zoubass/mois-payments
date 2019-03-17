package cz.kozenky.moispayments.service;

import cz.kozenky.moispayments.api.PaymentApi;
import cz.kozenky.moispayments.model.Payment;
import java.math.BigDecimal;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
public class PaymentsService {

    public void test() {
        PaymentApi paymentApi = new PaymentApi();
        DateTime from = DateTime.now().minusDays(50);
        DateTime to = DateTime.now();
        BigDecimal accountId = BigDecimal.TEN;

        try {
            List<Payment> payments = paymentApi.findPaymentByDate(from, to, accountId);
        } catch (RestClientException e) {
            //Only for breakpoint
            System.out.println(e.getMessage());
        }
    }

}
