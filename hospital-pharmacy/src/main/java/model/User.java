package model;

import javax.persistence.Entity;


public abstract class User {
    protected Integer id;
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.id = null;
        this.username = username;
        this.password = password;
    }

    public User(){};
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
