package by.ladyka.pass;

import by.ladyka.pass.config.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;


@Controller
@SpringBootApplication
public class PasswordManagerApplication {

	public static void main(String[] args) {
        SpringApplication.run(new Class<?>[] {PasswordManagerApplication.class, JpaConfig.class}, args);
	}
}
