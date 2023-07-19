package classes;

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

    public User(String userDetailsString) {
        String[] userDetails = userDetailsString.split("~");

        if (userDetails.length >= 8) {
            this.email = userDetails[0];
            this.firstName = userDetails[1];
            this.lastName = userDetails[2];
            this.gender = userDetails[3];
            this.dateOfBirth = LocalDate.parse(userDetails[4], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.accountMade = LocalDateTime.parse(userDetails[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            this.password = userDetails[6];
            this.password_salt = userDetails[7];
        } else {
            // Handle the case when the input string is not properly formatted
            System.out.println("userDetailsString: " + userDetailsString);;
        }

    }

}
