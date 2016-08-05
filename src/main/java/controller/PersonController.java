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

import javax.servlet.http.HttpServletRequest;

@RestController
public class PersonController {

    private PersonService ps;

    private TransController tc;

    @Autowired
    public void setPs(PersonService ps) {
        this.ps = ps;
    }

    @Autowired
    public void setTc(TransController tc) {
        this.tc = tc;
    }

    @RequestMapping("/")
    public ResponseEntity<Hashtable<String, Person>> index(HttpServletRequest request) {
        tc.getSession(request);
        Hashtable<String, Person> answer = new Hashtable<String, Person>(ps.getAll());
        return ResponseEntity.status(HttpStatus.OK).body(answer);

    }

    @RequestMapping("{name}")
    public Person getPerson(@PathVariable("name") String name) {
        return ps.getPerson(name);
    }

    @RequestMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        //Have some health test here. Just assume it's healthy in this app.
        boolean healthy = true;
        if (healthy) {
            return ResponseEntity.status(HttpStatus.OK).body("Your app is healthy");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Your app is unhealthy \n");
    }

}
