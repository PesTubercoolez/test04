package com.company.controller;

import com.company.model.Matrix.impl.IntegerMatrix;
import com.company.service.DAO.MatrixDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matrix")
public class MatrixController {

    @Autowired
    MatrixDAO matrixDAO;

    @GetMapping
    @ResponseBody
    public List<IntegerMatrix> getAllMatrixInfo(){
        return matrixDAO.getAllMatrix();
    }

    @GetMapping("/byId")
    @ResponseBody
    public IntegerMatrix getMatrixById(@RequestParam("id") String id) {
        return matrixDAO.getMatrixById(Long.parseLong(id));
    }
}
