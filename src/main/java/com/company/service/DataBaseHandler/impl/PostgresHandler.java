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

    public void insertJSONInResult (T object) throws SQLException {
        String insertStatement = "INSERT INTO result_storage(matrix_storage_id, result_matrix) VALUES (?, to_json(?::json))";
        String updateStatement = "UPDATE result_storage SET matrix_storage_id = ?, result_matrix = to_json(?::json)";
        PreparedStatement preparedStatement;
        String insertedObject;
        Connection connection = connect();
        insertedObject = new Gson().toJson(object);

        if (contentChecker(insertedObject)) {
             preparedStatement = connection.prepareStatement(insertStatement);
        }else{
            preparedStatement = connection.prepareStatement(updateStatement);
        }

        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, insertedObject);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
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
        int contentCounter = 0;
        String checkStatement = "SELECT result_matrix FROM result_storage";
        Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(checkStatement);

        while (!resultSet.isLast()) {
            resultSet.next();
            contentCounter = resultSet.getRow();
        }

            if (contentCounter != 0) {
                while (!resultSet.isLast()) {
                    resultSet.next();
                    if (insertedObject.equals(resultSet.getString(1))) {
                        firstColumnContentCheck = false;
                    } else {
                        firstColumnContentCheck = true;
                    }
                }
            }else {
                firstColumnContentCheck = true;
                resultSet.close();
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
