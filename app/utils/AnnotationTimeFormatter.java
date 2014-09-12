package utils;

import org.joda.time.format.DateTimeFormat;
import play.data.format.Formatters;

import java.text.*;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mint on 9-9-14.
 */
public class AnnotationTimeFormatter extends Formatters.AnnotationFormatter<TimeFormat, Long>{


    @Override
    public Long parse(TimeFormat annotation, String text, Locale locale) throws ParseException {
        if(text == null){
            return null;
        }
        java.text.DateFormat df = new SimpleDateFormat(annotation.value());
        Date date;
        long activityTime = 1L;
        try {
            date = df.parse(text);
            activityTime = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return activityTime;
    }

    @Override
    public String print(TimeFormat annotation, Long value, Locale locale) {
        if(value == null){
            return  "";
        }
        java.text.DateFormat df = new SimpleDateFormat(annotation.value());
        Date d = new Date(value);
        return df.format(d);
    }
}
