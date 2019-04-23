package cz.kozenky.moispayments.service;

import cz.kozenky.moispayments.model.Payment;
import cz.kozenky.moispayments.model.enumObj.MonthsInYear;
import cz.kozenky.moispayments.model.web_model.DateDto;
import cz.kozenky.moispayments.model.web_model.MonthItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SupportiveService {

    /**
     * Method get interval of actual dates, before period (DAY, MONTH, YEAR..)
     * going to past
     *
     * @param calPeriodConst java Calendar constant (Calendar.YEAR)
     * @param amount         count of periods
     * @return start and end period time in two formats - Date and String
     */
    public DateDto getActualOnePerDate(int calPeriodConst, int amount) {
        DateDto dateDto = new DateDto();
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(calPeriodConst, -amount);
        Date beforePeriod = calendar.getTime();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateDto.setFromD(beforePeriod);
        dateDto.setFromS(dateFormat.format(beforePeriod));
        dateDto.setToD(now);
        dateDto.setToS(dateFormat.format(now));
        return dateDto;
    }

    public MonthsInYear getMonthByInt(int month) {
        for (MonthsInYear item : MonthsInYear.values()){
            if (month == (item.getVal() - 1)){
                return item;
            }
        }
        return MonthsInYear.UNKNOWN;
    }

    public MonthsInYear getMonthsByString(String month){
        for (MonthsInYear item : MonthsInYear.values()){
            if (month.equals(item.getName().toUpperCase())){
                return item;
            }
        }
        return MonthsInYear.UNKNOWN;
    }

    public BigDecimal countPayments(List<Payment> paymentList){
        BigDecimal count = new BigDecimal(0);
        for(Payment payment : paymentList){
            count = count.add(payment.getValue().getAmount());
        }
        return count;
    }

    public MonthItem getMonthItem(String month){
        MonthItem item = new MonthItem();
        MonthsInYear monthsInYear = getMonthsByString(month);
        item.setMonth(monthsInYear.getVal());
        item.setMonthS(monthsInYear.getName());

        /*
         * Calculating if is picked month is in this or last year
         */
        Calendar cal = Calendar.getInstance();
        if (monthsInYear.getVal() <= (cal.get(Calendar.MONTH) + 1)){
            item.setYear(cal.get(Calendar.YEAR));
        }else {
            item.setYear((cal.get(Calendar.YEAR)) - 1);
        }

        return item;
    }
}
