package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name= "planilla")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanillaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = false, nullable = false)
    private Long id;

    private String rutEmpleado;

    private int sueldoFijoMensual;

    private int bonificacionAniosServicio;

    private int pagoHorasExtras;

    private int descuentos;

    private int sueldoBruto;

    private int cotizacionPrevisional;

    private int cotizacionSalud;

    private int sueldoFinal;
}
