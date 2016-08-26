package controller;

/**
 * Created by elieblich on 7/20/16.
 */

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PersonService;
import model.Person;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PersonController {

    private PersonService ps;


    @Autowired
    public void setPs(PersonService ps) {
        this.ps = ps;
    }

    @RequestMapping("/")
    public ResponseEntity<Hashtable<String, Person>> index() {
        Hashtable<String, Person> answer = new Hashtable<String, Person>(ps.getPendingTransactions());
        return ResponseEntity.status(HttpStatus.OK).body(answer);

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

    @RequestMapping(value = "/trans")
    @ResponseBody
    public ResponseEntity<ArrayList<String>> transEndpoint() {
        if (ps.getPendingTransactions().size() != 0) {  //check if there are pending transactions
            return ResponseEntity.status(HttpStatus.OK).body(transactionOutput());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<String>());

    }

    public ArrayList<String> transactionOutput() {
        ArrayList<String> output = new ArrayList<String>();
        output.add("Pending Transactions Left:");
        for (String name : ps.getPendingTransactions().keySet()) {
            output.add("{name: " + name + ", transaction: " + ps.getPerson(name).getTransaction() + "}");
        }
        return output;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    void createTrans(@RequestBody Person person) {
        ps.addTrans(person);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/")
    void removeAll() {
        ps.processAll();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable("name") String name) {
        ps.processTrans(name);
    }

    @RequestMapping("/{name}")
    public Person getPerson(@PathVariable("name") String name) {
        return ps.getPerson(name);
    }

    @RequestMapping(value = "/app")
    @ResponseBody
    public String appName() {
        return System.getenv("VCAP_APPLICATION");
    }
}
