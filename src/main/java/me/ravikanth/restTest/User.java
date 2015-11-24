package me.ravikanth.restTest;

import java.util.List;
import java.util.Map;

/**
 * Created by ragudipati on 11/22/15.
 */
public class User {

    private String name;
    private String lastName;
    private String dob;
    private int age;
    private double uid;

    List<Pojo> pojos;
    Map<String, String> aliases;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getUid() {
        return uid;
    }

    public void setUid(double uid) {
        this.uid = uid;
    }

    public Map<String, String> getAliases() {
        return aliases;
    }

    public void setAliases(Map<String, String> aliases) {
        this.aliases = aliases;
    }

    public List<Pojo> getPojos() {
        return pojos;
    }

    public void setPojos(List<Pojo> pojos) {
        this.pojos = pojos;
    }
}
