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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/trans")
public class TransController {
    private PersonService ps;

    @Autowired
    public void setPs(PersonService ps) {
        this.ps = ps;
    }

    public void getSession(HttpServletRequest request){
        request.getSession(true);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ArrayList<String>> transEndpoint(HttpServletRequest request) {
        getSession(request);
        String[] array = ps.getPendingTransactions().keySet().toArray(new String[ps.getAll().size()]);
        if (ps.getPendingTransactions().size() != 0) {  //check if there are pending transactions
            return ResponseEntity.status(HttpStatus.OK).body(transactionOutput(request));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<String>());

    }


    public ArrayList<String> transactionOutput(HttpServletRequest request) {
        ArrayList<String> output = new ArrayList<String>();
        output.add("Pending Transactions Left:");
        String index = System.getenv("CF_INSTANCE_INDEX");
        for (String name : ps.getPendingTransactions().keySet()) {
            output.add("{name: " + name + ", transaction: " + ps.getPerson(name).getTransaction() + "}");
        }
        output.add(index);
        return output;
    }

    @RequestMapping(value = "/cookie", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ArrayList<String>> cookie(HttpServletRequest request) {
        getSession(request);
        ArrayList<String> output = new ArrayList<String>();
        for (int i = 0; i<request.getCookies().length; i++){
            //if (request.getCookies()[i].getName() == "__VCAP_ID__" || request.getCookies()[i].getName() == "JSESSIONID")
            output.add(request.getCookies()[i].getName()+"="+ request.getCookies()[i].getValue());
        }
        return ResponseEntity.status(HttpStatus.OK).body(output);
    }

    @RequestMapping(method = RequestMethod.POST)
    void createTrans(@RequestBody Person person, HttpServletRequest request) {
        getSession(request);
        ps.addTrans(person);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable("name") String name, HttpServletRequest request) {
        getSession(request);
        ps.processTrans(name);
    }


}
