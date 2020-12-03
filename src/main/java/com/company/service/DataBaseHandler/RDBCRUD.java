package com.company.service.DataBaseHandler;

import com.company.model.Matrix;

import java.sql.SQLException;

public interface RDBCRUD<T> {

    void insertJSON(String insertStatement, T... object) throws SQLException;

    String selectJSON(String outStatement, int neededColumn) throws SQLException;
}
