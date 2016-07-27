package controller;

/**
 * Created by elieblich on 7/27/16.
 */
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PersonService;
import model.Person;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Hashtable;

@RestController
@RequestMapping(value = "/transactions")
public class TransController {
    private PersonService ps;
    @Autowired
    public void setPs(PersonService ps) {
        this.ps = ps;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Hashtable<String, Person> transEndpoint() {
        return ps.getPendingTransactions();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void remove(@PathVariable("id") String id){
        ps.processTrans(id);
    }


}
