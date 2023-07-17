/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.bmicalc.services;

import com.bmicalc.services.model.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

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
}
