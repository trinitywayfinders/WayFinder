package ie.tcd.wayfinder.controller;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Wayfinders Springboot PoC!";
    }

}