package controllers.api;

import org.springframework.stereotype.Controller;
import play.mvc.Result;

import static play.mvc.Results.ok;

/**
 * Created by mint on 30-10-14.
 */

@Controller
public class ConnectionTestController {

    public Result canConnect(){
        return ok();
    }

}
