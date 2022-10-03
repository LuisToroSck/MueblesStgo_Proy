package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name= "autorizacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorizacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = false, nullable = false)
    private Long id;

    private String rutEmpleado;

    private Date fecha;

    @Column(unique = false, nullable = true)
    private int cantidadHorasExtras;

    @Column(unique = false, nullable = true)
    private int autorizado;
}
