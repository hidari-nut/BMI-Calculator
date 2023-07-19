/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.bmicalc.services;

import com.bmicalc.services.model.BmiResult;
import com.bmicalc.services.model.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mputu
 */
@WebService(serviceName = "BMICalcWebService")
public class BMICalcWebService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertUser")
    public boolean insertUser(@WebParam(name = "email") String email, @WebParam(name = "firstName") String firstName, @WebParam(name = "lastName") String lastName, @WebParam(name = "gender") String gender, @WebParam(name = "dateOfBirth") LocalDate dateOfBirth, @WebParam(name = "accountMade") LocalDateTime accountMade, @WebParam(name = "password") String password) {
        User newUser = new User(email, firstName, lastName, gender, dateOfBirth, accountMade, password, "");
        boolean result = newUser.insertData();
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "viewUser")
    public String viewUser(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        User userLogin = new User();
        userLogin = userLogin.checkLogin(email, password);
        String userString = userLogin.toString();
        return userString;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateUser")
    public boolean updateUser(@WebParam(name = "email") String email, @WebParam(name = "firstName") String firstName, @WebParam(name = "lastName") String lastName, @WebParam(name = "gender") String gender, @WebParam(name = "dateOfBirth") LocalDate dateOfBirth, @WebParam(name = "accountMade") LocalDateTime accountMade) {
        User userUpd = new User(email, firstName, lastName, gender, dateOfBirth, accountMade, "", "");
        boolean result = userUpd.updateData();
        return result;
    }
    
    @WebMethod(operationName = "deleteUser")
    public boolean deleteUser(@WebParam(name = "email") String email){
        User user = new User();
        boolean result = user.deleteData(email);
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertBMIResult")
    public boolean insertBMIResult(@WebParam(name = "userEmail") String userEmail, @WebParam(name = "bmi") double bmi, @WebParam(name = "date_added") LocalDateTime date_added, @WebParam(name = "height") double height, @WebParam(name = "weight") double weight) {
        BmiResult bmiResult = new BmiResult(userEmail, bmi, date_added, height, weight);
        boolean result = bmiResult.insertData();
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "viewBmiResult")
    public List<String> viewBmiResult(@WebParam(name = "email") String email) {
        BmiResult bmiResult = new BmiResult();
        List<String> listResults = bmiResult.viewDataAsString(email);
        return listResults;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "calculateBMI")
    public Double calculateBMI(@WebParam(name = "height") double height, @WebParam(name = "weight") double weight) {
        //TODO write your implementation code here:
        return null;
    }
}
