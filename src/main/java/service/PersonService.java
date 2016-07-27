package service;

/**
 * Created by elieblich on 7/20/16.
 */
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import model.Person;

@Service
public class PersonService {
    private Hashtable<String, Person> transactions = new Hashtable<String, Person>();
    private Hashtable<String, Person> pending_transactions = new Hashtable<String, Person>();
    private int count;
    public PersonService() {
        Person p = new Person();
        p.setId("1");
        p.setTransaction(21);
        p.setFirstName("ezra");
        transactions.put("1", p);
        pending_transactions.put("1", p);

        p = new Person();
        p.setId("2");
        p.setTransaction(18);
        p.setFirstName("maxine");
        transactions.put("2", p);
        pending_transactions.put("2", p);

        p = new Person();
        p.setId("3");
        p.setTransaction(50);
        p.setFirstName("john");
        transactions.put("3", p);
        pending_transactions.put("3", p);

        p = new Person();
        p.setId("4");
        p.setTransaction(35);
        p.setFirstName("Al");
        transactions.put("4", p);
        pending_transactions.put("4", p);

        p = new Person();
        p.setId("5");
        p.setTransaction(120);
        p.setFirstName("Johnathon");
        transactions.put("5", p);
        pending_transactions.put("5", p);


        p = new Person();
        p.setId("6");
        p.setTransaction(93);
        p.setFirstName("Craig");
        transactions.put("6", p);
        pending_transactions.put("6", p);

        p = new Person();
        p.setId("7");
        p.setTransaction(111);
        p.setFirstName("Bill");
        transactions.put("7", p);
        pending_transactions.put("7", p);

        p = new Person();
        p.setId("8");
        p.setTransaction(67);
        p.setFirstName("Matt");
        transactions.put("8", p);
        pending_transactions.put("8", p);
        count = pending_transactions.size();
    }
    public Person getPerson(String id) {
        if (transactions.containsKey(id))
            return transactions.get(id);
        else
            return null;
    }
    public Hashtable<String, Person> getAll(){
        return transactions;
    }

    public Hashtable<String, Person> getPendingTransactions(){
        return pending_transactions;
    }

    public Hashtable<String, Person> processTrans() {
        pending_transactions.remove(Integer.toString(count));
        count--;
        return pending_transactions;
    }
}
