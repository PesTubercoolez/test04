package com.company.service.MatrixConverterToJSON;

import com.company.model.Matrix;
import com.google.gson.Gson;

public class MatrixConverterToJSON {

    public String convertMatrixArrayToJSON(Matrix matrix){
        return new Gson().toJson(matrix.getArray());
    }

    public int [][] convertJSONToMatrixArray(String json){
        return new Gson().fromJson(json, int[][].class);
    }
}
