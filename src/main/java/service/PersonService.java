package service;

/**
 * Created by elieblich on 7/20/16.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import model.Person;

import javax.sql.DataSource;

@Repository
public class PersonService {
    private Hashtable<String, Person> transactions = new Hashtable<String, Person>();
    @Autowired
    private DataSource dataSource;

    public PersonService() {
    }

    public Person getPerson(String name) {
        /*
        if (transactions.containsKey(name))
            return transactions.get(name);
        else
            return null;
        */
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM trans WHERE name = ?");
            ps.setString(1, name);
            Person person = new Person();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                person.setName(name);
                person.setTransaction(rs.getInt("TRAN"));
            }
            rs.close();
            ps.close();
            return person;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public Hashtable<String, Person> getAll() {
        //return transactions;
        Hashtable<String, Person> pending_transactions = new Hashtable<String, Person>();
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM trans");
            //ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Person person = new Person();
                person.setName(rs.getString("NAME").trim());
                person.setTransaction(rs.getInt("TRAN"));
                pending_transactions.put(person.getName(), person);
            }
            rs.close();
            ps.close();
            return pending_transactions;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public Hashtable<String, Person> getPendingTransactions() {
        //return pending_transactions;
        Hashtable<String, Person> pending_transactions = new Hashtable<String, Person>();
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM trans");
            //ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Person person = new Person();
                person.setName(rs.getString("NAME").trim());
                person.setTransaction(rs.getInt("TRAN"));
                pending_transactions.put(person.getName(), person);
            }
            rs.close();
            ps.close();
            return pending_transactions;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public void processTrans(String name) {
        //pending_transactions.remove(name);
        //return pending_transactions;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM trans WHERE name=?");
            ps.setString(1, name);
            ps.execute();
            ps.close();
            //return person;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public void addTrans(Person person) {
        //transactions.put(person.getName(), person);
        //pending_transactions.put(person.getName(), person);
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO trans VALUES (?, ?)");
            ps.setString(1, person.getName());
            //Person person = new Person();
            ps.setInt(2, person.getTransaction());
            ps.executeUpdate();
            ps.close();
            //return person;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }
}
