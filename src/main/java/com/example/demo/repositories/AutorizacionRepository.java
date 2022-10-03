package com.example.demo.repositories;

import com.example.demo.entities.AutorizacionEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AutorizacionRepository extends JpaRepository<AutorizacionEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "update autorizacion a set a.autorizado = :aut where a.id=:id",nativeQuery = true)
    void actualizarAutorizacion(@Param("aut") int aut, @Param("id") Long id);

    @Query("select a from AutorizacionEntity a where a.id=:id")
    AutorizacionEntity getById(@Param("id") Long id);

}