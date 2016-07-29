package controller;

/**
 * Created by elieblich on 7/20/16.
 */

import java.util.*;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import service.PersonService;
import model.Person;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private PersonService ps;

    @Autowired
    public void setPs(PersonService ps) {
        this.ps = ps;
    }

    @RequestMapping("/")
    public ResponseEntity<Hashtable<String, Person>> index() {
        Hashtable<String, Person> answer = new Hashtable<String, Person>(ps.getAll());
        return ResponseEntity.status(HttpStatus.OK).body(answer);

    }

    @RequestMapping("{id}")
    public Person getPerson(@PathVariable("id") String id) {
        return ps.getPerson(id);
    }

    @RequestMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        boolean healthy = true;
        if (healthy) {
            return ResponseEntity.status(HttpStatus.OK).body("Your app is healthy");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Your app is unhealthy \n");
    }


}
