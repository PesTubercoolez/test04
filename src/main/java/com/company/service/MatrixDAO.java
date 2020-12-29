package com.company.service;

import com.company.model.Matrix;
import com.company.repository.MatrixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatrixDAO {

    @Autowired
    private MatrixRepository repository;

    public void saveMatrix(Matrix matrix){
        repository.save(matrix);
    }

    public Matrix getMatrixById(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Matrix> getAllMatrix(){
        return repository.findAll();
    }

    public void deleteMatrixById(Long id){
        repository.deleteById(id);
    }

    public void truncateMatrix(){
        repository.deleteAll();
    }

    public void modifyMatrixById(Long id){
        
    }
}
