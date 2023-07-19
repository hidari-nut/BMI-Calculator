/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class ConnModel {

    protected static Connection connection;
    protected Statement statement;
    protected ResultSet result;

    public ConnModel() {
        ConnModel.connection = this._getConnection();
        this.statement = null;
        this.result = null;
    }

    private Connection _getConnection() {
        if (ConnModel.connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                return DriverManager.getConnection("jdbc:mysql://localhost/bmidb", "yuvee", "sqlserver03");
            } catch (Exception e) {
                System.out.println("Error in _getConnection: " + e);
            }
        } else {
            return ConnModel.connection;
        }
        return ConnModel.connection;
    }
}
