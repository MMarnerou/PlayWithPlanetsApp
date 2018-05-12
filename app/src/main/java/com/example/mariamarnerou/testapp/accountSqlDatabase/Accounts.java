package com.example.mariamarnerou.testapp.accountSqlDatabase;

import java.io.Serializable;

/**
 * Created by Maria Marnerou on 10-Mar-18.
 */

public class Accounts implements Serializable {
    private String name, age, username, gender;

    public Accounts(String name, String age, String username, String gender)
    {
        this.name = name;
        this.age = age;
        this.username = username;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                " name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", username='" + username + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
