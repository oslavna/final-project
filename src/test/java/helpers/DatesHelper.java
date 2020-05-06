package helpers;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DatesHelper {


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
        return todayWithZeroTime;
    }

    public boolean checkDate(String textDate) throws ParseException {
        Date today = getTodayDate();
        DateFormat format = new SimpleDateFormat("d MMMM yyyy", new Locale("en"));
        Date date = format.parse(textDate);
        System.out.println(date);
        System.out.println();
        Calendar cal = Calendar.getInstance();
        List<Date> listOfThisWeekDates = new ArrayList<>();
        for (int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
            cal.set(Calendar.DAY_OF_WEEK, i);
            listOfThisWeekDates.add(cal.getTime());
            // System.out.println(cal.getTime());// Date
        }
        for (Date dates : listOfThisWeekDates) {
            System.out.println(date);

            System.out.println(dates);
            if ((date.before(dates) || (date.equals(today)))&&(!date.before(today))) {
               System.out.println("The date is inside of the week");
               return true;

            } else System.out.println("The date is out of this week or before today");
            return false;}
        return false;
    }

    @Test
    public void checkDate1() throws ParseException {
            checkDate("21 June 2020");
        }

       // System.out.println(day);

       // Date dateFrom = convertToDate(textDate);
    }



