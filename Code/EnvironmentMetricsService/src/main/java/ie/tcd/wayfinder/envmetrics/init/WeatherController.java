package ie.tcd.wayfinder.envmetrics.init;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"*"}, allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
@RestController
@RequestMapping(value="/")
public class WeatherController {

    @RequestMapping(value="/env", method=RequestMethod.GET)
    public String index()
    {
        return "Wayfinders Springboot Env Service!";
    }

}