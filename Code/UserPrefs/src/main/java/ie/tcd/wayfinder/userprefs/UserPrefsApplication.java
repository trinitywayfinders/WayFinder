package ie.tcd.wayfinder.userprefs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"ie.tcd.wayfinders.entities"})
@EnableJpaRepositories(basePackages={"ie.tcd.wayfinders.repositories"})
public class UserPrefsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserPrefsApplication.class, args);
	}

}
