package config;

import controller.PersonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import service.PersonService;

import java.util.Hashtable;

/**
 * Created by elieblich on 7/25/16.
 */

@Component
@ComponentScan(basePackageClasses = {PersonService.class, PersonController.class})
public class PersonConfig {
    PersonService ps;
    @Autowired
    public void setPs(PersonService ps) {
        this.ps = ps;
    }
    @Scheduled(fixedRate = 7000, initialDelay = 1000*60)
    public void processTrans() {
        if (ps.getAll().size() == 0){
            return;
        }
        for (int i=0; i < Math.random()*3; i++) {
            ps.processTrans();
        }
        return;
    }
}
