package cz.kozenky.moispayments.controller;

import cz.kozenky.moispayments.model.Payment;
import cz.kozenky.moispayments.model.codelist.Category;
import cz.kozenky.moispayments.model.codelist.CategoryList;
import cz.kozenky.moispayments.model.web_model.CategoryDto;
import cz.kozenky.moispayments.model.web_model.PaymentDto;
import cz.kozenky.moispayments.model.web_model.PieChartItem;
import cz.kozenky.moispayments.service.CategoryService;
import cz.kozenky.moispayments.service.PaymentsService;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private PaymentsService paymentsService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private CategoryList categoryList;

    @RequestMapping("/findPayments")
    public List<Payment> getPayments() {
        DateTime from = DateTime.now().minusYears(7);
        DateTime to = DateTime.now().plusYears(1);
        BigDecimal accountId = new BigDecimal("123");

        List<Payment> payments = paymentsService.findPayments(from, to, accountId);
        return payments;
    }

    @RequestMapping("/findPaymentsDetail/{from}/{to}/{accountId}")
    public List<Payment> getPaymentsDetail(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal accountId) {
        return getPaymentsFromApi(from, to, accountId);
    }


    @RequestMapping("/findPaymentsSummary/{from}/{to}/{accountId}")
    public List<PieChartItem> getPaymentsSummary(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal accountId) {
        List<Payment> payments = getPaymentsFromApi(from, to, accountId);
        
        //TODO: pro všechny payments s neznámou kategorií prohnat metodou "resolveByRule"
        
        List<PieChartItem> modelUnGroupped = new ArrayList<PieChartItem>() {};
        for (Payment p: payments) {
            BigDecimal amount = p.getValue().getAmount();
            Category category = categoryList.getById(p.getCategoryId());
            modelUnGroupped.add(new PieChartItem(amount, category.getName() ,category.getId()));
        }
        //MAP<Key,Value>
        Map<String, BigDecimal> sum = modelUnGroupped.stream().collect(
                Collectors.groupingBy(PieChartItem::getName,
                Collectors.mapping(PieChartItem::getValue, Collectors.reducing(BigDecimal.ZERO,BigDecimal::add))));

        List<PieChartItem> model = new ArrayList<PieChartItem>() {};
        for (Map.Entry<String, BigDecimal> s:sum.entrySet() ) {
            model.add(new PieChartItem(s.getValue(),s.getKey(),categoryList.getByName(s.getKey()).getId()));
        }
        return model;
    }

    @PostMapping("/add_payment")
    public Payment addPayment(@RequestBody PaymentDto paymentDto){
        Payment payment = paymentsService.savePayment(paymentDto);
        categoryService.saveRule(paymentDto);
        return payment;
    }

    @PostMapping("/add_category")
    public String addCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.saveCategory(categoryDto);
    }
    
    @RequestMapping("/categories")
    public List<Category> getCategories() {
        return categoryList.allValues();
    }
    
    private List<Payment> getPaymentsFromApi(String from, String to, BigDecimal accountId) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        DateTime fromParsed = null;
        DateTime toParsed = null;
        try {
            fromParsed = new DateTime(format.parse(from).getTime());
            toParsed = new DateTime(format.parse(to).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return paymentsService.findPayments(fromParsed, toParsed, accountId);
    }
}
