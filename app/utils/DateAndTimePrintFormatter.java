package utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.text.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mint on 9-9-14.
 */
public final class DateAndTimePrintFormatter {

    private DateAndTimePrintFormatter(){}
    private static java.text.DateFormat df = new SimpleDateFormat("HH:mm");

    public static String dateTimePrintFormatter(long dt){
        String datePattern = "dd-MM-yyyy";
        DateTimeFormatter dtf = DateTimeFormat.forPattern(datePattern);
        return dtf.print(dt);
    }

    public static String timePrintFormatter(long time){
        Date date = new Date(time);
        return df.format(date);
    }

    public static long convertTimeToLong(String time){
        try {
            Date date =  df.parse(time);
            return date.getTime();
            //TODO: implement with function for the exception
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static long convertStringToTimeStamp(String dateTime){
        String datePattern = "dd-MM-yyyy";
        DateFormat dateFormat = new SimpleDateFormat(datePattern);
        try {
            Date date = dateFormat.parse(dateTime);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 1;
    }




}
