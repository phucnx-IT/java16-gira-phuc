package cybersoft.javabackend.java16giraphuc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Java16GiraPhucApplication {

	public static void main(String[] args) {
		SpringApplication.run(Java16GiraPhucApplication.class, args);
	}

}
