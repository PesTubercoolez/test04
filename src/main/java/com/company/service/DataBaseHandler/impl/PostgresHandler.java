package com.company.service.DataBaseHandler.impl;
import com.company.service.DataBaseHandler.RDBCRUD;
import com.google.gson.Gson;

import java.sql.*;

public class PostgresHandler <T> implements RDBCRUD<T> {

    private final String URL;
    private final String user;
    private final String password;

    public PostgresHandler(String URL, String user, String password) {
        this.URL = URL;
        this.user = user;
        this.password = password;
    }

    @Override
    public void insertJSON(String insertStatement, T... firstObject) throws SQLException {
        Connection connection = connect();
        PreparedStatement inStatement = connection.prepareStatement(insertStatement);
        String insertObject;

        for (int x = 0; x < firstObject.length; x++ ) {
            insertObject = new Gson().toJson(firstObject[x]);
            inStatement.setString((x + 1), insertObject);
        }

        inStatement.executeUpdate();
        connection.close();
        inStatement.close();
    }

    @Override
    public String selectJSON(String outStatement, int neededColumn) throws SQLException {
        Connection connection = connect();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(outStatement);
        resultSet.relative(neededColumn);
        String result = resultSet.getString(1);
        resultSet.close();
        statement.close();
        connection.close();

        return result;
    }

    private boolean contentChecker(String insertedObject) throws SQLException {
        boolean firstColumnContentCheck = false;
        String checkStatement = "SELECT first_matrix,second_matrix FROM matrix_storage";
        Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(checkStatement);

        while (!resultSet.isLast()){
        resultSet.next();
        if (insertedObject.equals(resultSet.getString(1))){
            firstColumnContentCheck = false;
        }else {
            firstColumnContentCheck = true;
        }
        }
        connection.close();
        statement.close();
        resultSet.close();
        return firstColumnContentCheck;
    }

    private Connection connect() throws SQLException {

        Connection connection = DriverManager.getConnection(this.URL, this.user, this.password);

        return connection;
    }
}
