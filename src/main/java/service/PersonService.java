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

    public PersonService() {
        Person p = new Person();
        p.setId("1");
        p.setTransaction(21);
        p.setName("ezra");
        transactions.put("1", p);
        pending_transactions.put("1", p);

        p = new Person();
        p.setId("2");
        p.setTransaction(18);
        p.setName("maxine");
        transactions.put("2", p);
        pending_transactions.put("2", p);

        p = new Person();
        p.setId("3");
        p.setTransaction(50);
        p.setName("john");
        transactions.put("3", p);
        pending_transactions.put("3", p);
    }

    public Person getPerson(String id) {
        if (transactions.containsKey(id))
            return transactions.get(id);
        else
            return null;
    }

    public Hashtable<String, Person> getAll() {
        return transactions;
    }

    public Hashtable<String, Person> getPendingTransactions() {
        return pending_transactions;
    }

    public Hashtable<String, Person> processTrans(String id) {
        pending_transactions.remove(id);
        return pending_transactions;
    }

    public void addTrans(Person person) {
        transactions.put(person.getId(), person);
        pending_transactions.put(person.getId(), person);
    }
}
