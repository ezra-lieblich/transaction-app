package service;

/**
 * Created by elieblich on 7/20/16.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import model.Person;

import javax.sql.DataSource;

@Repository
public class PersonService {
    private Hashtable<String, Person> transactions = new Hashtable<String, Person>();
    @Autowired
    private DriverManagerDataSource dataSource;

    public PersonService() {
    }

    public Person getPerson(String name) {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public Hashtable<String, Person> getPendingTransactions() {
        Hashtable<String, Person> pending_transactions = new Hashtable<String, Person>();
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM trans");
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public void processTrans(String name) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM trans WHERE name=?");
            ps.setString(1, name);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public void processAll() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM trans");
            ps.execute();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public void addTrans(Person person) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO trans VALUES (?, ?)");
            ps.setString(1, person.getName());
            ps.setInt(2, person.getTransaction());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
            }
        }
    }
}
