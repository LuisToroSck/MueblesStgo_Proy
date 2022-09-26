package com.example.demo.repositories;

import com.example.demo.entities.SueldoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SueldoRepository extends JpaRepository<SueldoEntity, Long> {




}
