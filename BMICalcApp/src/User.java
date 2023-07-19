/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author User
 */
public class User extends ConnModel {

    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private LocalDateTime accountMade;
    private String password;
    private String password_salt;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getAccountMade() {
        return accountMade;
    }

    public void setAccountMade(LocalDateTime accountMade) {
        this.accountMade = accountMade;
    }

    private String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_salt() {
        return password_salt;
    }

    public void setPassword_salt(String password_salt) {
        this.password_salt = password_salt;
    }

    public User() {
        this.email = "";
        this.firstName = "";
        this.lastName = "";
        this.gender = "";
        this.dateOfBirth = LocalDate.now();
        this.accountMade = LocalDateTime.now();
        this.password = "";
        this.password_salt = "";
    }

    public User(String email, String firstName, String lastName, String gender, LocalDate dateOfBirth, LocalDateTime accountMade, String password, String password_salt) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.accountMade = accountMade;
        this.password = password;
        this.password_salt = password_salt;
    }
    
 
}
