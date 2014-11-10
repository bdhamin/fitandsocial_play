import controllers.routes;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import play.GlobalSettings;
import play.Application;
import play.data.format.Formatters;
import play.libs.F;
import play.mvc.Call;
import play.mvc.Http;
import play.mvc.SimpleResult;
import utils.AnnotationDateFormatter;
import utils.AnnotationTimeFormatter;

import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.PlayAuthenticate.Resolver;
import com.feth.play.module.pa.exceptions.AccessDeniedException;
import com.feth.play.module.pa.exceptions.AuthException;
import views.html.error404;

import static play.mvc.Results.notFound;

public class Global extends GlobalSettings {

    private ApplicationContext ctx;

    @Override
    public void onStart(Application app) {
        ctx = new ClassPathXmlApplicationContext("components.xml");
        Formatters.register(Long.class, new AnnotationDateFormatter());
        Formatters.register(Long.class, new AnnotationTimeFormatter());


        PlayAuthenticate.setResolver(new Resolver() {

            @Override
            public Call login() {
                // Your login page
                return routes.Application.index();
            }

            @Override
            public Call afterAuth() {
                // The user will be redirected to this page after authentication
                // if no original URL was saved
                return routes.Application.index();
            }

            @Override
            public Call afterLogout() {
                return routes.Application.index();
            }

            @Override
            public Call auth(final String provider) {
                // You can provide your own authentication implementation,
                // however the default should be sufficient for most cases
                return com.feth.play.module.pa.controllers.routes.Authenticate
                        .authenticate(provider);
            }

            @Override
            public Call onException(final AuthException e) {
                if (e instanceof AccessDeniedException) {
                    return routes.Application
                            .oAuthDenied(((AccessDeniedException) e)
                                    .getProviderKey());
                }

                // more custom problem handling here...

                return super.onException(e);
            }

            @Override
            public Call askLink() {
                // We don't support moderated account linking in this sample.
                // See the play-authenticate-usage project for an example
                return null;
            }

            @Override
            public Call askMerge() {
                // We don't support moderated account merging in this sample.
                // See the play-authenticate-usage project for an example
                return null;
            }
        });
    }

    @Override
    public <A> A getControllerInstance(Class<A> clazz) {
        return ctx.getBean(clazz);
    }

    @Override
    public F.Promise<SimpleResult> onHandlerNotFound(Http.RequestHeader requestHeader) {
        return F.Promise.<SimpleResult>pure(notFound(
                error404.render(requestHeader.path())
        ));
    }

}