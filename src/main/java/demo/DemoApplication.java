package demo;


import config.DatabaseConfig;
import controller.PersonController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import service.PersonService;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackageClasses = {PersonController.class, PersonService.class, DatabaseConfig.class})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }
}
