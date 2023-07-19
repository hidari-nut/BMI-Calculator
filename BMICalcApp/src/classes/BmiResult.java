/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author User
 */
public class BmiResult {

    private String userEmail;
    private double bmi;
    private LocalDateTime date_added;
    private double height;
    private double weight;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public LocalDateTime getDate_added() {
        return date_added;
    }

    public void setDate_added(LocalDateTime date_added) {
        this.date_added = date_added;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public BmiResult() {
        this.userEmail = "";
        this.bmi = 0;
        this.date_added = LocalDateTime.now();
        this.height = 0;
        this.weight = 0;
    }

    public BmiResult(String userEmail, double bmi, LocalDateTime date_added, double height, double weight) {
        this.userEmail = userEmail;
        this.bmi = bmi;
        this.date_added = date_added;
        this.height = height;
        this.weight = weight;
    }

    public BmiResult(String bmiResultString) {
        String[] bmiResultDetails = bmiResultString.split("~");
        this.userEmail = bmiResultDetails[0];
        this.bmi = Double.parseDouble(bmiResultDetails[1]);
        this.date_added = LocalDateTime.parse(bmiResultDetails[2],DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.height = Double.parseDouble(bmiResultDetails[3]);
        this.weight = Double.parseDouble(bmiResultDetails[4]);
    }
}
