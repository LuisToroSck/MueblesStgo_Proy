package com.example.demo.repositories;

import com.example.demo.entities.DatarelojEntity;
import com.example.demo.entities.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRelojRepository extends JpaRepository<DatarelojEntity, Long> {
}
