/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmicalc.services.model;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class BmiResult extends ConnModel {

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

    @Override
    public String toString() {
        return userEmail + "~" + Double.toString(bmi) + "~" + date_added.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) 
                + "~" + Double.toString(height) + "~" + Double.toString(weight);
    }

    public boolean insertData() {
        try {
            //If connection is NOT closed
            if (!ConnModel.connection.isClosed()) {
                PreparedStatement sql = (PreparedStatement) ConnModel.connection.prepareStatement("INSERT INTO tBMIResult (tUser_email, bmi, date_added, height, weight) VALUES (?,?,?,?,?)");
                sql.setString(1, this.userEmail);
                sql.setDouble(2, this.bmi);
                sql.setString(3, this.date_added.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                sql.setDouble(4, this.height);
                sql.setDouble(5, this.weight);

                sql.executeUpdate();
                sql.close();

                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in BmiResult.insertData: " + e);
            return false;
        }
        return false;
    }

    public List<BmiResult> viewData(String email) {
        try {
            if (!ConnModel.connection.isClosed()) {
                PreparedStatement sql = (PreparedStatement) ConnModel.connection.prepareStatement("SELECT tUser_email, bmi, date_added, height, weight FROM tBMIResult "
                        + "INNER JOIN tUser on tBMIResult.tUser_email = tUser.email "
                        + "WHERE tUser.email=?;");
                sql.setString(1, email);

                this.result = sql.executeQuery();
            }

            List<BmiResult> listResult = new ArrayList<BmiResult>();
            BmiResult result = new BmiResult();
            while (this.result.next()) {
                result = new BmiResult(this.result.getString("tUser_email"),
                        this.result.getDouble("bmi"),
                        LocalDateTime.parse(this.result.getString("date_added"),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        this.result.getDouble("height"),
                        this.result.getDouble("weight"));

                listResult.add(result);
            }
            return listResult;

        } catch (Exception e) {
            System.out.println("Error in BmiResult.viewData: " + e);
        }
        return null;
    }

    public List<String> viewDataAsString(String email) {
        try {
            List<String> stringResults = new ArrayList<>();

            List<BmiResult> bmiResults = viewData(email);
            for (BmiResult bmiResult : bmiResults) {
                String stringResult = bmiResult.toString();
                stringResults.add(stringResult);
            }

            return stringResults;

        } catch (Exception e) {
            System.out.println("Error in BmiResult.viewDataAsString: " + e);
        }
        return null;
    }

    public double calculateBMI(double height, double weight) {
        double bmi = weight / (Math.pow(height, 2));
        return bmi;
    }

    public String classifyBMI(double bmi) {
        if (bmi < 16) {
            return "Severely underweight";
        } else if (bmi >= 16 && bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 25) {
            return "Normal (healthy) weight";
        } else if (bmi >= 25 && bmi < 30) {
            return "Overweight";
        } else if (bmi >= 30 && bmi < 35) {
            return "Obese Class I (Moderate)";
        } else if (bmi >= 35 && bmi < 40) {
            return "Obese Class II (Severe)";
        } else {
            return "Obese Class III (Very severe or morbidly obese)";
        }
    }
}
