package helpers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import tests.EventsTests;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DatesHelper {

    private static final Logger logger = LogManager.getLogger(EventsTests.class);
    public Date convertToDate(String textDate) throws ParseException {
        DateFormat format = new SimpleDateFormat("d MMMM yyyy", new Locale("en"));
        Date date = format.parse(textDate);
        System.out.println(date);
        return date;
    }

    public Date getTodayDate() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        Date todayWithZeroTime = formatter.parse(formatter.format(today));
        logger.info("Today value is " + todayWithZeroTime);
        return todayWithZeroTime;
    }

    public boolean checkDate(String textDate) throws ParseException {
        Date today = getTodayDate();
        DateFormat format = new SimpleDateFormat("d MMMM yyyy", new Locale("en"));
        Date date = format.parse(textDate);
        logger.info("Date " + date + "from Event card received");
        Calendar cal = Calendar.getInstance();
        List<Date> listOfThisWeekDates = new ArrayList<>();
        for (int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
            cal.set(Calendar.DAY_OF_WEEK, i);
            listOfThisWeekDates.add(cal.getTime());
            logger.info("Date belong to this week  " + cal.getTime());
        }
        for (Date dates : listOfThisWeekDates) {
            System.out.println(date);
            System.out.println(dates);
            if ((date.before(dates) || (date.equals(today)))&&(!date.before(today))) {
                logger.info("The date is inside of the week");
               return true;

            } else logger.error("The date is out of this week or before today");
            return false;}
        return false;
    }

}



