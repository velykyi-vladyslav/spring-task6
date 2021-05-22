package velykyi.vladyslav.task4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories("velykyi.vladyslav.task4.repository")
public class Task4Application {

	public static void main(String[] args) {

		SpringApplication.run(Task4Application.class, args);

	}

}
