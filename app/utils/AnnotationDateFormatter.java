package utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import play.data.format.Formatters;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by mint on 9-9-14.
 */
public class AnnotationDateFormatter extends Formatters.AnnotationFormatter<DateFormat, DateTime>{


    @Override
    public DateTime parse(DateFormat annotation, String text, Locale locale) throws ParseException {
        if(text == null || text.trim().isEmpty()){
            return null;
        }
        return DateTimeFormat.forPattern(annotation.value()).withLocale(locale).parseDateTime(text);
    }

    @Override
    public String print(DateFormat annotation, DateTime value, Locale locale) {

        if(value == null){
            return "";
        }
        return DateTimeFormat.forPattern(annotation.value()).withLocale(locale).print(value);

    }
}
