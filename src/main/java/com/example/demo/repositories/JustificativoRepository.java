package com.example.demo.repositories;

import com.example.demo.entities.JustificativoEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JustificativoRepository extends JpaRepository<JustificativoEntity, Long> {




}