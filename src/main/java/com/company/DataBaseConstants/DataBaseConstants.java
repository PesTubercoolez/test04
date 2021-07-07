package com.company.DataBaseConstants;

public class DataBaseConstants {

    public static final String URL = "jdbc:postgresql://localhost:5432/matrix_db";
    public static final String USER = "root";
    public static final String PASSWORD = "zetaprime1";
    public static final String INSERT_IN_MATRIX_STORAGE = "INSERT INTO matrix_storage (first_matrix, second_matrix) VALUES (to_json(?::json), to_json(?::json))";
    public static final String INSERT_IN_RESULT_STORAGE = "INSERT INTO result_storage(matrix_storage_id, result_matrix) VALUES (?, to_json(?::json))";
    public static final String UPDATE_RESULT_STORAGE = "UPDATE result_storage SET matrix_storage_id = ?, result_matrix = to_json(?::json)";
    public static final String SELECT_FIRST_MATRIX_FROM_MATRIX_STORAGE = "SELECT first_matrix FROM matrix_storage WHERE id = 1";
    public static final String SELECT_SECOND_MATRIX_FROM_MATRIX_STORAGE = "SELECT second_matrix FROM matrix_storage WHERE id = 1";
    public static final String SELECT_FROM_RESULT_STORAGE = "SELECT result_matrix FROM result_storage";

}
