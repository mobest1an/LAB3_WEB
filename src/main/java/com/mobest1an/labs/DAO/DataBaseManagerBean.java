package com.mobest1an.labs.DAO;

import com.mobest1an.labs.services.Result;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public boolean addResult(Result result) {
        try {
            String query = "INSERT INTO results(X, Y, R, HIT, TIME) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, result.getX());
            preparedStatement.setDouble(2, result.getY());
            preparedStatement.setDouble(3, result.getR());
            if (result.getHit().equals("Да")) {
                preparedStatement.setString(4, "Y");
            } else {
                preparedStatement.setString(4, "N");
            }
            preparedStatement.setLong(5, result.getCreationDate().getTime());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Result> getResults() {
        try {
            String query = "SELECT * FROM RESULTS";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Result> results = new ArrayList<>();
            while (resultSet.next()) {
                double x = resultSet.getDouble("X");
                double y = resultSet.getDouble("Y");
                double r = resultSet.getDouble("R");
                String hit = resultSet.getString("HIT");
                Date creationDate = new Date(resultSet.getLong("TIME"));
                Result result = new Result(x, y, r, creationDate);
                if (hit.equals("Y")) {
                    result.setHit("Да");
                } else if (hit.equals("N")) {
                    result.setHit("Нет");
                }
                results.add(result);
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isConnected() {
        try {
            if (connection != null && connection.isValid(0)) {
                return true;
            } else {
                if (connection != null) {
                    connection.close();
                }
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                return connection.isValid(0);
            }
        } catch (SQLException e) {
            return false;
        }
    }
}
