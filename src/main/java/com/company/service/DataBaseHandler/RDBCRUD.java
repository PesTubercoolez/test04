package com.company.service.DataBaseHandler;

import java.sql.Connection;
import java.sql.SQLException;

public interface RDBCRUD<T> {
    String selectJSON(String outStatement,Connection connection ,int neededRow) throws SQLException;
}