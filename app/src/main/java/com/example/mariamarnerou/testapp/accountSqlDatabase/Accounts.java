package com.example.mariamarnerou.testapp.accountSqlDatabase;

import java.io.Serializable;

/**
 * Created by Maria Marnerou on 10-Mar-18.
 */

public class Accounts implements Serializable {
    private String  name, surname, username, gender;
    public Accounts(String name, String surname, String username, String gender)
    {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
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
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username=" + username +
                ", gender=" + gender +
                '}';
    }
}
