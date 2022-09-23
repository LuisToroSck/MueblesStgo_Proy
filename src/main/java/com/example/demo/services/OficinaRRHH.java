package com.example.demo.services;

import com.example.demo.entities.EmpleadoEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OficinaRRHH {

    public double calcularSueldoFijoMensual(EmpleadoEntity empleado){

        double sueldoFijoMensual = 0;

        if(empleado.getCategoria().equals("A")){sueldoFijoMensual = 1700000;}
        if(empleado.getCategoria().equals("B")){sueldoFijoMensual = 1200000;}
        else{sueldoFijoMensual = 800000;}

        return sueldoFijoMensual;
    }

    public double calcularBonificacionPorServicio(EmpleadoEntity empleado, double sueldoFijoMensual){

        double bonificacionPorServicio = 0;

        int anioIngreso = empleado.getFechaIngreso().getYear();
        int anioActual  = LocalDateTime.now().getYear();
        int aniosServicio = anioActual - anioIngreso;

        if(aniosServicio >= 25){bonificacionPorServicio = sueldoFijoMensual * 0.17;}
        else if(aniosServicio >= 20){bonificacionPorServicio = sueldoFijoMensual * 0.14;}
        else if(aniosServicio >= 15){bonificacionPorServicio = sueldoFijoMensual * 0.11;}
        else if(aniosServicio >= 10){bonificacionPorServicio = sueldoFijoMensual * 0.08;}
        else if(aniosServicio >= 5){bonificacionPorServicio = sueldoFijoMensual * 0.05;}
        else{bonificacionPorServicio = 0;}

        return bonificacionPorServicio;
    }



}
