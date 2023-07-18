/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmicalc.services.model;

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
    
    @Override
    public String toString(){
        return email + "~" + firstName + "~" + lastName + "~" + gender + "~" + dateOfBirth.toString() + "~" + accountMade.toString() + "~" + password
                 + "~" + password_salt;
    }
    
    public boolean insertData() {
        try {
            //If connection is NOT closed
            if (!ConnModel.connection.isClosed()) {
                PreparedStatement sql = (PreparedStatement) ConnModel.connection.prepareStatement
                ("INSERT INTO tuser(email, first_name, last_name, gender, date_of_birth, account_made, password, password_salt) "
                        + "VALUES (?,?,?,?,?,?,?,?)");
                
                this.setPassword_salt(this.generateSalt());
                
                sql.setString(1, this.email);
                sql.setString(2, this.firstName);
                sql.setString(3, this.lastName);
                sql.setString(4, this.gender);
                sql.setString(5, this.dateOfBirth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                sql.setString(6, this.accountMade.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                sql.setString(7, this.getPassword());
                sql.setString(8, this.password_salt);
                sql.executeUpdate();
                sql.close();

                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in User.insertData: " + e);
            return false;
        }
        return false;
    }

    public Object viewData(String email) {
        try {
            if (!ConnModel.connection.isClosed()) {
                PreparedStatement sql = (PreparedStatement) ConnModel.connection.prepareStatement("SELECT * FROM tuser WHERE email=?");
                sql.setString(1, email);

                this.result = sql.executeQuery();
            }

            User user = new User();
            while (this.result.next()) {
                user = new User(this.result.getString("email"),
                        this.result.getString("first_name"),
                        this.result.getString("last_name"),
                        this.result.getString("gender"),
                        this.result.getDate("dateOfBirth").toLocalDate(),
                        LocalDateTime.parse(this.result.getString("accountMade"),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        this.result.getString("password"),
                        this.result.getString("password_salt"));
            }
            return user;

        } catch (Exception e) {
            System.out.println("Error in User.viewData: " + e);
        }
        return null;
    }
    
    public String generateSalt(){
        //Will Return Salt
        return "";
    }
    
    public String hashPassword(String password, String password_salt){
        // Insert Hashing Algorithm Here
        //PBKDF2 Hashing with Salt.
        return password;
    }

    public User checkLogin(String email, String password) {
        try {
            User userLogin = (User) viewData(email);
            String userPass = userLogin.getPassword();
            
            String passwordLogin = userLogin.hashPassword(password, userLogin.getPassword_salt());

            if (userLogin != null) {
                if (userPass.equals(passwordLogin)) {
                    return userLogin;
                } else {
                    return new User();
                }
            }

        } catch (Exception e) {
            System.out.println("Error in User.checkLogin: " + e);
        }
        return new User();
    }
    
    public boolean updateData(){
        try {
            //If connection is NOT closed
            if (!ConnModel.connection.isClosed()) {
                PreparedStatement sql2 = (PreparedStatement) ConnModel.connection.prepareStatement(
                        "UPDATE vehicle SET plate = ?, brand = ?, vehicle_class = ?, color = ? WHERE id = ?");
                
                PreparedStatement sql = (PreparedStatement) ConnModel.connection.prepareStatement(
                        "UPDATE tuser SET first_name = ?, last_name = ?, gender = ?, date_of_birth = ?, account_made = ? WHERE email = ?");
                
                sql.setString(1, this.firstName);
                sql.setString(2, this.lastName);
                sql.setString(3, this.gender);
                sql.setString(4, this.dateOfBirth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                sql.setString(5, this.accountMade.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//                sql.setString(6, this.getPassword());
//                sql.setString(7, this.password_salt);
                sql.setString(6, this.email);
                
                sql.executeUpdate();
                sql.close();
                
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in User.updateData: " + e);
            return false;
        }
        return false;
    }
    
        public boolean deleteData(String email){
        try {
            //If connection is NOT closed
            if (!ConnModel.connection.isClosed()) {
                PreparedStatement sql = (PreparedStatement) ConnModel.connection.prepareStatement(
                        "DELETE FROM tuser WHERE email = ?");
                sql.setString(1, email);
                sql.executeUpdate();
                sql.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in User.deleteData: " + e);
            return false;
        }
        return false;
    }
}
