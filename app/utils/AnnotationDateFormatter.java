package utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import play.data.format.Formatters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mint on 9-9-14.
 */

public class AnnotationDateFormatter extends Formatters.AnnotationFormatter<DateFormat, Long>{


    @Override
    public Long parse(DateFormat annotation, String text, Locale locale) throws ParseException {

        if(text == null || text.trim().isEmpty()){
            return null;
        }
        java.text.DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(text);
        return date.getTime();
    }

    @Override
    public String print(DateFormat annotation, Long value, Locale locale) {
        return null;
    }
}




//public class AnnotationDateFormatter extends Formatters.AnnotationFormatter<DateFormat, DateTime>{
//
//
//    @Override
//    public DateTime parse(DateFormat annotation, String text, Locale locale) throws ParseException {
//        if(text == null || text.trim().isEmpty()){
//            return null;
//        }
//        return DateTimeFormat.forPattern(annotation.value()).withLocale(locale).parseDateTime(text);
//    }
//
//    @Override
//    public String print(DateFormat annotation, DateTime value, Locale locale) {
//
//        if(value == null){
//            return "";
//        }
//        return DateTimeFormat.forPattern(annotation.value()).withLocale(locale).print(value);
//
//    }
//}
