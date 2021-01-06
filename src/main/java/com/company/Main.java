package com.company;

import com.company.exception.ZeroInputException;
import com.company.factory.MatrixCreator;
import com.company.model.Matrix.Matrix;
import com.company.model.Matrix.impl.IntegerMatrix;
import com.company.model.User.User;
import com.company.service.DAO.MatrixDAO;
import com.company.service.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Main {

    @Autowired
    MatrixDAO matrixDAO;
    @Autowired
    MatrixCreator creator;
    @Autowired
    UserDAO userDAO;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() throws ZeroInputException {

        /*User vasyan = new User(13, "Vasyan", "Dolbyk", "User");
        List<IntegerMatrix> matrixList = creator.createMatrixList(2)
                .stream()
                .map(matrix -> (IntegerMatrix) matrix)
                .collect(Collectors.toList());

        userDAO.saveUser(vasyan);
        matrixList.forEach(matrix -> matrix.setUser(vasyan));
        matrixDAO.saveMatrixList(matrixList);*/
        userDAO.deleteUserById(1L);
    }
}
