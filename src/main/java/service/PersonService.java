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
        p.setTransaction(21);
        p.setName("ezra");
        transactions.put("ezra", p);
        pending_transactions.put("ezra", p);

        p = new Person();
        p.setTransaction(18);
        p.setName("maxine");
        transactions.put("maxine", p);
        pending_transactions.put("maxine", p);

        p = new Person();
        p.setTransaction(50);
        p.setName("john");
        transactions.put("john", p);
        pending_transactions.put("john", p);
    }

    public Person getPerson(String name) {
        if (transactions.containsKey(name))
            return transactions.get(name);
        else
            return null;
    }

    public Hashtable<String, Person> getAll() {
        return transactions;
    }

    public Hashtable<String, Person> getPendingTransactions() {
        return pending_transactions;
    }

    public Hashtable<String, Person> processTrans(String name) {
        pending_transactions.remove(name);
        return pending_transactions;
    }

    public void addTrans(Person person) {
        transactions.put(person.getName(), person);
        pending_transactions.put(person.getName(), person);
    }
}
