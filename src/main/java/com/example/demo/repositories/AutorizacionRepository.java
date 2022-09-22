package com.example.demo.repositories;

import com.example.demo.entities.AutorizacionEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorizacionRepository extends JpaRepository<AutorizacionEntity, Long> {

}