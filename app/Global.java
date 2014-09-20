import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import play.GlobalSettings;
import play.Application;
import play.data.format.Formatters;
import utils.AnnotationDateFormatter;
import utils.AnnotationTimeFormatter;

public class Global extends GlobalSettings {

    private ApplicationContext ctx;

    @Override
    public void onStart(Application app) {
        ctx = new ClassPathXmlApplicationContext("components.xml");
        Formatters.register(Long.class, new AnnotationDateFormatter());
        Formatters.register(Long.class, new AnnotationTimeFormatter());
    }

    @Override
    public <A> A getControllerInstance(Class<A> clazz) {
        return ctx.getBean(clazz);
    }

}