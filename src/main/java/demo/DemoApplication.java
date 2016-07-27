package demo;

//import config.PersonConfig;
//import config.PersonConfig;
import controller.PersonController;
import controller.TransController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import service.PersonService;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackageClasses = {PersonController.class, PersonService.class, TransController.class})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		/*
		ApplicationContext context = new AnnotationConfigApplicationContext(DemoApplication.class);
		PersonController contr = context.getBean(PersonController.class);
		contr.setPs(context.getBean(PersonService.class));
		System.out.println(contr.index());
		*/
	}
}
