package by.ladyka.pass;

import by.ladyka.pass.config.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@SpringBootApplication
public class PasswordManagerApplication {

	@RequestMapping("/hello")
	@ResponseBody
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		//SpringApplication.run(PasswordManagerApplication.class, args);
        SpringApplication.run(new Class<?>[] {PasswordManagerApplication.class, JpaConfig.class}, args);
	}
}
