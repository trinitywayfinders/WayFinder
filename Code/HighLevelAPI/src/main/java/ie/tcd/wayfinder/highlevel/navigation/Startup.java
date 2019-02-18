package ie.tcd.wayfinder.highlevel.navigation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={
"ie.tcd.wayfinder.highlevel.navigation", "config"})
public class Startup {

    public static void main(String[] args) {

        SpringApplication.run(NavigationController.class, args);
    }
}