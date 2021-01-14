package com.company.controller;

import com.company.factory.MatrixCreator;
import com.company.model.Matrix.impl.IntegerMatrix;
import com.company.service.EntitiesService.MatrixEntityService;
import com.company.service.EntitiesService.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/profile/matrix")
public class MatrixController {

    @Autowired
    MatrixEntityService matrixEntityService;
    @Autowired
    UserEntityService userEntityService;
    @Autowired
    MatrixCreator creator;

    @GetMapping
    @ResponseBody
    public List<IntegerMatrix> getAllMatrixInfo(){
        return matrixEntityService.getAllMatrix();
    }

    @GetMapping("/byId")
    @ResponseBody
    public IntegerMatrix getMatrixById(@RequestParam(name = "id") String id) {
        return matrixEntityService.getMatrixById(Long.parseLong(id));
    }

    @GetMapping("/byUserName")
    @ResponseBody
    public List<IntegerMatrix> getMatrixByUserName(@RequestParam(name = "name") String name) {
        return matrixEntityService.getMatrixByUser(name);
    }

    @PostMapping("/addNewMatrix")
    public String addMatrix (@RequestBody ResponseEntity<IntegerMatrix> matrix, Principal principal) {
        matrix.getBody().setUser(userEntityService.getUserByName(principal.getName()));
        matrixEntityService.saveMatrix(matrix.getBody());

        return "matrix was successfully added";
    }

    //@DeleteMapping("/deleteById")
    //public String deleteMatrixById (RequestParam("id") String id)
}
