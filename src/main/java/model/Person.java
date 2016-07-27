package model;

/**
 * Created by elieblich on 7/20/16.
 */
public class Person {
    String id;
    String firstName;
    int transaction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getTransaction() {
        return transaction;
    }

    public void setTransaction(int age) {
        this.transaction = age;
    }
}
