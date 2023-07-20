/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmicalc.services.model;

import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

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
    public String toString() {
        return email + "~" + firstName + "~" + lastName + "~" + gender + "~" + dateOfBirth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + "~" + accountMade.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "~" + password
                + "~" + password_salt + "~";
    }

    /**
     * Checks if an email is used by another user account in the database.
     *
     * @param email
     * @return true if email is used, false if email is not used
     */
    public boolean checkEmailUsed(String email) {
        User user = (User) viewData(email);
        if (user.getEmail().equals(email)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertData() {
        try {
            //Checks if the email is used by another user account
            if (checkEmailUsed(email) == true) {
                return false;
            }

            //If connection is NOT closed
            if (!ConnModel.connection.isClosed()) {
                String hashedPassAndSalt = hashPassword(this.password);
                String[] hashedParts = hashedPassAndSalt.split("~");

                String hashedPassword = hashedParts[0];
                String salt = hashedParts[1];

                System.out.println("HashedPass: " + hashedPassword);

                PreparedStatement sql = (PreparedStatement) ConnModel.connection.prepareStatement("INSERT INTO tuser(email, first_name, last_name, gender, date_of_birth, account_made, password, password_salt) "
                        + "VALUES (?,?,?,?,?,?,?,?)");

                sql.setString(1, this.email);
                sql.setString(2, this.firstName);
                sql.setString(3, this.lastName);
                sql.setString(4, this.gender);
                sql.setString(5, this.dateOfBirth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                sql.setString(6, this.accountMade.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                sql.setString(7, hashedPassword);
                sql.setString(8, salt);
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
                        this.result.getDate("date_of_birth").toLocalDate(),
                        LocalDateTime.parse(this.result.getString("account_made"),
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

    private static String toHex(byte[] byteArray) {
        BigInteger bi = new BigInteger(1, byteArray);
        String hex = bi.toString(16);

        int paddingLength = (byteArray.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    public byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    public String hashPassword(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        int iterations = 100;

        char[] charPass = password.toCharArray();
        byte[] salt = generateSalt();

        PBEKeySpec spec = new PBEKeySpec(charPass, salt, iterations, 64 * 8); //64 bytes long
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hashedPass = keyFactory.generateSecret(spec).getEncoded();

        return toHex(hashedPass) + "~" + toHex(salt);
    }

    public String hashPassword(String password, String password_salt) {
        // Insert Hashing Algorithm Here
        //PBKDF2 Hashing with Salt.
        return password;
    }

    private boolean checkPassword(String enteredPassword, String storedPassword, String storedSalt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 100;

        byte[] salt = fromHex(storedSalt);
        byte[] hash = fromHex(storedPassword);

        PBEKeySpec spec = new PBEKeySpec(enteredPassword.toCharArray(),
                salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    public User checkLogin(String email, String password) {
        try {
            User userLogin = (User) viewData(email);
            String userPass = userLogin.getPassword();
            String salt = userLogin.getPassword_salt();

            boolean passwordIsCorrect = checkPassword(password, userPass, salt);

            if (userLogin != null) {
                System.out.println("User Exists");
                if (passwordIsCorrect) {
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

    public boolean updateData() {
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

    public boolean deleteData(String email) {
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

    public boolean insertBlock(String userEmail, String targetEmail) {
        try {
            //If connection is NOT closed
            if (!ConnModel.connection.isClosed()) {
                PreparedStatement sql = (PreparedStatement) ConnModel.connection.prepareStatement("INSERT INTO tblocked(tUser_email, target_email) "
                        + "VALUES (?,?)");

                sql.setString(1, userEmail);
                sql.setString(2, targetEmail);
                sql.executeUpdate();
                sql.close();

                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in User.insertBlock: " + e);
            return false;
        }
        return false;
    }

    public List<String> viewBlock(String userEmail){
        try {
            if (!ConnModel.connection.isClosed()) {
                PreparedStatement sql = (PreparedStatement) ConnModel.connection.prepareStatement("SELECT target_email FROM tblocked WHERE tUser_email=?");
                sql.setString(1, userEmail);

                this.result = sql.executeQuery();
                System.out.println("SQL CREATED!");
            }

            List<String> blockList = new ArrayList<String>();
            while (this.result.next()) {
                String blockedEmail = this.result.getString("target_email");
                blockList.add(blockedEmail);
            }
            return blockList;
        } catch (Exception e) {
            System.out.println("Error in User.viewBlock: " + e);
            return null;
        }
    }

    public boolean deleteBlock(String userEmail, String targetEmail) {
        try {
            //If connection is NOT closed
            if (!ConnModel.connection.isClosed()) {
                PreparedStatement sql = (PreparedStatement) ConnModel.connection.prepareStatement(
                        "DELETE FROM tblocked WHERE tUser_email = ? AND target_email = ?");
                sql.setString(1, userEmail);
                sql.setString(2, targetEmail);
                sql.executeUpdate();
                sql.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in User.deleteBlock: " + e);
            return false;
        }
        return false;
    }
}
