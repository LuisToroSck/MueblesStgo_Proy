package com.example.demo.repositories;

import com.example.demo.entities.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {
    public EmpleadoEntity findByRutEmpleado(String rutEmpleado);

    @Query("select e from EmpleadoEntity e where e.nombres = :nombres")
    EmpleadoEntity findByNameCustomQuery(@Param("nombres") String nombres);

    @Query(value = "select * from empleados as e where e.nombres = :nombres",
            nativeQuery = true)
    EmpleadoEntity findByNameNativeQuery(@Param("nombres") String nombres);

}