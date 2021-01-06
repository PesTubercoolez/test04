package com.company.repository;

import com.company.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE User user SET user.age = :age, user.name = :name, user.password = :password, user.role = :role WHERE user.id = :id")
    void updateUser(@Param("age") int age, @Param("name") String name, @Param("password") String password, @Param("role") String role, @Param("id") Long id);
}
