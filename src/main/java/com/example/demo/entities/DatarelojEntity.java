package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name= "datareloj")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DatarelojEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = false, nullable = false)
    private Long idDataReloj;

    private String rutEmpleadoReloj;

    private Date fecha;

    private Time hora;

}
