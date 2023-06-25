package com.example.spring_lab.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.transaction.annotation.Propagation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static jakarta.transaction.Transactional.TxType.REQUIRED;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column
    private int age;

    @OneToMany(cascade=CascadeType.PERSIST,fetch= FetchType.EAGER)
    //cascade type -> because spring boot can not register user;
    //This happens because you have a collection in your entity, and that collection has one or more items which are not present in the database.
    // By specifying the above options you tell hibernate to save them to the database when saving their parent.

    //don't do that -> fetchType.EAGER -> performance will get down -> take accounts with query is better
    private Set<Account> accounts;

    public User() {}

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Set<Account> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
