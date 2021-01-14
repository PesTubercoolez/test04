package com.company.service.EntitiesService;

import com.company.model.Matrix.impl.IntegerMatrix;
import com.company.repository.MatrixRepository;
import com.company.service.MatrixConverterToJSON.MatrixConverterToJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatrixEntityService {

    @Autowired
    private final MatrixRepository repository;
    @Autowired
    private final MatrixConverterToJSON converter;

    public MatrixEntityService(MatrixRepository repository, MatrixConverterToJSON converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public void saveMatrix(IntegerMatrix matrix){
        repository.save(matrix);
    }

    public void saveMatrixList(List<IntegerMatrix> matrixList){
        for (int x = 0; x < matrixList.size(); x++){
            saveMatrix(matrixList.get(x));
        }
    }

    public IntegerMatrix getMatrixById(Long id){
        IntegerMatrix matrix = repository.findById(id).orElse(null);
            matrix.setAllValues(converter.convertJSONToMatrixArray(matrix.getArrayRepresentation()));

            return matrix;
    }

    public List<IntegerMatrix> getMatrixByUser(String userName) {
        List <IntegerMatrix> matrixList = repository.getMatrixByUser(userName);
        matrixList.forEach(matrix -> matrix.setAllValues(converter.convertJSONToMatrixArray(matrix.getArrayRepresentation())));

        return matrixList;
    }

    public List<IntegerMatrix> getAllMatrix(){
        List <IntegerMatrix> matrixList = repository.findAll();
        matrixList.forEach(matrix -> matrix.setAllValues(converter.convertJSONToMatrixArray(matrix.getArrayRepresentation())));

        return matrixList;
    }

    public void deleteMatrixById(Long id){
        repository.deleteById(id);
    }

    public void truncateMatrix(){
        repository.deleteAll();
    }

    public void updateMatrixById(Long id, int rows, int columns, String arrayRepresentation){
        repository.updateMatrix(id, rows, columns, arrayRepresentation);
    }
}
