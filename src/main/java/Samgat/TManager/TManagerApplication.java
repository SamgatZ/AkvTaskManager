package Samgat.TManager;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TManagerApplication.class, args);
	}
	@Bean
	public OpenAPI openAPIConfig(){
		return new OpenAPI().info(apiInfo());
	}

	public Info apiInfo() {
		Info info = new Info();
		info.
				title("Task Manager API").description("Simple REST Spring boot project with CRUD logic");
		return info;
	}
}
