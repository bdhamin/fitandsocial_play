package controllers;


import models.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import play.data.Form;
import play.libs.Json;
import play.mvc.Result;
import services.service.RegistrationService;
import views.html.index;

import static play.mvc.Results.ok;

@org.springframework.stereotype.Controller
public class Application {


//    @Autowired
//    private HelloService helloService;
//
//    public Result index() {
//        return ok(index.render(helloService.hello()));
//    }


//    @Autowired
//    private BarService barService;
//
//    public Result index() {
//        return play.mvc.Controller.ok(index.render(Form.form(Bar.class)));
//    }
//
//    public Result addBar() {
//        Form<Bar> form = Form.form(Bar.class).bindFromRequest();
//        Bar bar = form.get();
//        barService.addBar(bar);
//        return play.mvc.Controller.redirect(controllers.routes.Application.index());
//    }
//
//    public Result listBars() {
//        return play.mvc.Controller.ok(Json.toJson(barService.getAllBars()));
//    }



    @Autowired
    private RegistrationService registrationService;

    public Result index() {
        return play.mvc.Controller.ok(index.render(Form.form(Registration.class)));
    }

    public Result addRegistration() {
        Form<Registration> form = Form.form(Registration.class).bindFromRequest();
        Registration registration = form.get();
        registrationService.persist(registration);
        return play.mvc.Controller.redirect(controllers.routes.Application.index());
    }

    public Result listRegistration() {
        return play.mvc.Controller.ok(Json.toJson(registrationService.getAll()));
    }
    
}