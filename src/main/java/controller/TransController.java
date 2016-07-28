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
    public ResponseEntity<ArrayList<String>> transEndpoint() {
        String[] array = ps.getPendingTransactions().keySet().toArray(new String[ps.getAll().size()]);
        if (ps.getPendingTransactions().size() != 0) {
            return ResponseEntity.status(HttpStatus.OK).body(transactionOutput());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<String>());
    }

    public ArrayList<String> transactionOutput() {
        ArrayList<String> output = new ArrayList<String>();
        output.add("Pending Transactions Left:");
        for (String id : ps.getPendingTransactions().keySet()) {
            output.add("{id: " + ps.getPerson(id).getId() + " name: " + ps.getPerson(id).getFirstName() +"}");
        }
        return output;
    }

    @RequestMapping(method = RequestMethod.POST)
    void createTrans(@RequestBody Person person){
        ps.addTrans(person);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void remove(@PathVariable("id") String id){
        ps.processTrans(id);
    }


}
