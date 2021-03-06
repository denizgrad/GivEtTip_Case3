package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Matic-ProBook on 20. 09. 2017.
 */

public class User extends BaseModel implements Serializable {
    private String name;
    private String surname;
    private String email;
    private String password;
    private BigDecimal score;
    private boolean active;
    private List<Record> records;

    /* public methods */
    public User() {}

    public User(int userID) {
        this.setId(userID);
        this.active = true;
        this.setDeleted(false);
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean getActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public List<Record> getRecords() {
        return records;
    }
    public void setRecords(List<Record> records) {
        this.records = records;
    }
    public BigDecimal getScore() {
        return score;
    }
    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
