package com.company.model.User;

import com.company.model.Matrix.impl.IntegerMatrix;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "matrix_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "age")
    private int age;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<IntegerMatrix> matrixList;

    public User() {

    }

    public User (int age, String name, String password, String role){
        setAge(age);
        setName(name);
        setPassword(password);
        setRole(role);
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public String getPassword(){
        return this.password;
    }

    public String getRole(){
        return this.role;
    }

    public void setMatrixList(List<IntegerMatrix> matrixList){
        this.matrixList = matrixList;
    }

    public List<IntegerMatrix> getMatrixList(){
        return this.matrixList;
    }
}
