package cz.kozenky.moispayments.service;

import cz.kozenky.moispayments.model.Payment;
import cz.kozenky.moispayments.model.enumObj.MonthsInYear;
import cz.kozenky.moispayments.model.web_model.DateDto;
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
     * Method get interval of actual dates, after period (DAY, MONTH, YEAR..)
     *
     * @param calPeriodConst java Calendar constant (Calendar.YEAR)
     * @param amount         count of periods
     * @return start and end period time in two formats - Date and String
     */
    public DateDto getActualOnePerDate(int calPeriodConst, int amount) {
        DateDto dateDto = new DateDto();
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(calPeriodConst, amount);
        Date afterPeriod = calendar.getTime();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateDto.setFromD(now);
        dateDto.setFromS(dateFormat.format(now));
        dateDto.setToD(afterPeriod);
        dateDto.setToS(dateFormat.format(afterPeriod));
        return dateDto;
    }

    public MonthsInYear getMonthByInt(int month) {
        switch (month) {
            case 0:
                return MonthsInYear.JANUARY;
            case 1:
                return MonthsInYear.FEBRUARY;
            case 2:
                return MonthsInYear.MARCH;
            case 3:
                return MonthsInYear.APRIL;
            case 4:
                return MonthsInYear.MAY;
            case 5:
                return MonthsInYear.JUNE;
            case 6:
                return MonthsInYear.JULY;
            case 7:
                return MonthsInYear.AUGUST;
            case 8:
                return MonthsInYear.SEPTEMBER;
            case 9:
                return MonthsInYear.OCTOBER;
            case 10:
                return MonthsInYear.NOVEMBER;
            case 11:
                return MonthsInYear.DECEMBER;
            default:
                return null;
        }
    }

    public BigDecimal countPayments(List<Payment> paymentList){
        BigDecimal count = new BigDecimal(0);
        for(Payment payment : paymentList){
            count = count.add(payment.getValue().getAmount());
        }
        return count;
    }
}
