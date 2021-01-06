package com.company.repository;

import com.company.model.Matrix.impl.IntegerMatrix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MatrixRepository extends JpaRepository<IntegerMatrix, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE IntegerMatrix matrix SET matrix.rows = :rows, matrix.columns = :columns, matrix.arrayRepresentation = :arrayRepresentation WHERE matrix.id = :id")
    public void updateMatrix(@Param("id") Long id, @Param("rows") int rows, @Param("columns") int columns, @Param("arrayRepresentation") String arrayRepresentation);
}
