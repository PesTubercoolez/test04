package com.company.controller;

import com.company.factory.MatrixCreator;
import com.company.model.Matrix.impl.IntegerMatrix;
import com.company.service.EntitiesService.MatrixEntityService;
import com.company.service.EntitiesService.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile/matrix")
public class MatrixController {

    @Autowired
    MatrixEntityService matrixEntityService;
    @Autowired
    UserEntityService userEntityService;
    @Autowired
    MatrixCreator creator;

    @GetMapping("/matrix_view")
    public String getMatrixByUserName(Model model, Principal principal) {
        model.addAttribute("matrix", matrixEntityService.getMatrixByUser(principal.getName()));
        return "matrix_view";
    }

    @GetMapping("/new_matrix")
    public String getNewMatrixPage(Model model){
        model.addAttribute("matrix", new IntegerMatrix());
        return "new_matrix";
    }

    @PostMapping("/new_matrix")
    public String addMatrix (@ModelAttribute("matrix") IntegerMatrix matrix, Principal principal, Model model) {
        matrix.setUser(userEntityService.getUserByName(principal.getName()));
        model.addAttribute("matrix", matrix);
        matrixEntityService.saveMatrix(matrix);

        return "new_matrix";
    }

    //@DeleteMapping("/deleteById")
    //public String deleteMatrixById (RequestParam("id") String id)
}
