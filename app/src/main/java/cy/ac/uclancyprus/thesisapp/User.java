package cy.ac.uclancyprus.thesisapp;

/**
 * Created by maria_m on 11/11/2017.
 */

public class User {

    private String username;
    private String name;
    private String surname;
    private String yearfBirth;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getYearfBirth() {
        return yearfBirth;
    }

    public void setYearfBirth(String yearfBirth) {
        this.yearfBirth = yearfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname=" + surname +
                ", yearfBirth=" + yearfBirth +
                '}';
    }
}
