package com.company.service.DataBaseHandler.impl;
import com.company.constants.DataBaseConstants.DataBaseConstants;
import com.company.service.DataBaseHandler.RDBCRUD;
import com.google.gson.Gson;

import java.sql.*;

public class PostgresHandler <T> implements RDBCRUD<T> {

    @Override
    public void insertJSON(String insertStatement, Connection connection ,T... firstObject) throws SQLException {
        PreparedStatement inStatement = connection.prepareStatement(insertStatement);
        Gson jsonFormatter = new Gson();

        for (int x = 0; x < firstObject.length; x++ ) {
            inStatement.setString((x + 1), jsonFormatter.toJson(firstObject[x]));
        }
        inStatement.executeUpdate();
        inStatement.close();
    }

    public void insertJSONInResult (T object, Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
        String insertedObject = new Gson().toJson(object);

        if (contentChecker(insertedObject, connection)) {
             preparedStatement = connection.prepareStatement(DataBaseConstants.INSERT_IN_RESULT_STORAGE);
        }else{
            preparedStatement = connection.prepareStatement(DataBaseConstants.UPDATE_RESULT_STORAGE);
        }
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, insertedObject);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public String selectJSON(String outStatement,Connection connection ,int neededRow) throws SQLException {
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(outStatement);
        resultSet.relative(neededRow);
        String result = resultSet.getString(1);
        resultSet.close();
        statement.close();

        return result;
    }

    private boolean contentChecker(String insertedObject, Connection connection) throws SQLException {
        boolean firstColumnContentCheck = false;
        int contentCounter = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(DataBaseConstants.SELECT_FROM_RESULT_STORAGE);

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
        statement.close();
        resultSet.close();

        return firstColumnContentCheck;
    }
}
