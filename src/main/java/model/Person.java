package model;

/**
 * Created by elieblich on 7/20/16.
 */
public class Person {
    String id;
    String name;
    int transaction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTransaction() {
        return transaction;
    }

    public void setTransaction(int age) {
        this.transaction = age;
    }
}
