package com.company.controller;

import com.company.factory.MatrixCreator;
import com.company.model.Matrix.impl.IntegerMatrix;
import com.company.model.User.User;
import com.company.service.DAO.MatrixDAO;
import com.company.service.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Matrix")
public class MatrixController {

    @Autowired
    MatrixDAO matrixDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    MatrixCreator creator;

    @GetMapping
    @ResponseBody
    public List<IntegerMatrix> getAllMatrixInfo(){
        return matrixDAO.getAllMatrix();
    }

    @GetMapping("/ById")
    @ResponseBody
    public IntegerMatrix getMatrixById(@RequestParam(name = "id") String id) {
        return matrixDAO.getMatrixById(Long.parseLong(id));
    }

    @GetMapping("/ByUserName")
    @ResponseBody
    public List<IntegerMatrix> getMatrixByUserName(@RequestParam(name = "name") String name) {
        return matrixDAO.getMatrixByUser(name);
    }

    @PostMapping("/AddNewUser")
    public String addUser (@RequestBody User user) {
        userDAO.saveUser(user);

        return "user was successfully added";
    }

    @PostMapping("/AddNewMatrix")
    public String addMatrix (@RequestBody(required = true) ResponseEntity<IntegerMatrix> matrix, @RequestParam(name = "userLink") String userLink) {
        matrix.getBody().setUser(userDAO.getUserById(Long.valueOf(userLink)));
        matrixDAO.saveMatrix(matrix.getBody());

        return "matrix was successfully added";
    }
}
