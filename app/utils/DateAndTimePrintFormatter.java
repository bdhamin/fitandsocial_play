package utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.*;
import java.util.Date;

/**
 * Created by mint on 9-9-14.
 */
public final class DateAndTimePrintFormatter {

    private DateAndTimePrintFormatter(){}

    public static String dateTimePrintFormatter(DateTime dt){
        String datePattern = "dd-MM-yyyy";
        DateTimeFormatter dtf = DateTimeFormat.forPattern(datePattern);
        return dtf.print(dt);
    }

    public static String timePrintFormatter(long time){
        Date date = new Date(time);
        java.text.DateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(date);
    }



}
