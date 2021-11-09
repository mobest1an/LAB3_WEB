package com.mobest1an.labs.models;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ManagedBean(name = "dataBaseManagerBean", eager = true)
@ApplicationScoped
public class DataBaseManagerBean {

    private Connection connection;
    private final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orbis";
    private final String USER = "s312989";
    private final String PASS = "wnc702";

    public DataBaseManagerBean() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void addValue(double x, double y, double r, String hit) {
        try {
            String query = "INSERT INTO results(x, y, r, hit) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, x);
            preparedStatement.setDouble(2, y);
            preparedStatement.setDouble(3, r);
            preparedStatement.setString(4, hit);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
