package com.example.demo.services;

import com.example.demo.entities.AutorizacionEntity;
import com.example.demo.entities.EmpleadoEntity;
import com.example.demo.entities.JustificativoEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OficinaRRHH {

    public double calcularSueldoFijoMensual(EmpleadoEntity empleado){

        double sueldoFijoMensual = 0;

        if(empleado.getCategoria().equals("A")){
            sueldoFijoMensual = 1700000;
        }
        else if(empleado.getCategoria().equals("B")){
            sueldoFijoMensual = 1200000;
        }
        else{
            sueldoFijoMensual = 800000;
        }

        return sueldoFijoMensual;
    }

    public double calcularBonificacionPorAniosServicio(EmpleadoEntity empleado, double sueldoFijoMensual){

        double bonificacionPorServicio = 0;

        LocalDate localDate = empleado.getFechaIngreso().toLocalDate();

        int anioIngreso = localDate.getYear();
        System.out.println("Anio ingreso: "+anioIngreso);
        int anioActual  = LocalDateTime.now().getYear();
        System.out.println("Anio actual: "+anioActual);
        int aniosServicio = anioActual - anioIngreso;
        System.out.println("Anios de servicio: "+aniosServicio);

        if(aniosServicio >= 25){bonificacionPorServicio = sueldoFijoMensual * 0.17;}
        else if(aniosServicio >= 20){bonificacionPorServicio = sueldoFijoMensual * 0.14;}
        else if(aniosServicio >= 15){bonificacionPorServicio = sueldoFijoMensual * 0.11;}
        else if(aniosServicio >= 10){bonificacionPorServicio = sueldoFijoMensual * 0.08;}
        else if(aniosServicio >= 5){bonificacionPorServicio = sueldoFijoMensual * 0.05;}
        else{bonificacionPorServicio = 0;}

        return bonificacionPorServicio;
    }

    public double calcularPagoHorasExtras(EmpleadoEntity empleado, List<AutorizacionEntity> autorizaciones){

        AutorizacionService autorizacionService = null;

        double pagoHorasExtras = 0;

        int i=0;
        while(i<autorizaciones.size()){
            if(autorizaciones.get(i).getRutEmpleado().equals(empleado.getRutEmpleado())) {
                if (autorizaciones.get(i).getAutorizado() == 1) {
                    if (empleado.getCategoria().equals("A")) {
                        pagoHorasExtras = autorizaciones.get(i).getCantidadHorasExtras() * 25000;
                    } else if (empleado.getCategoria().equals("B")) {
                        pagoHorasExtras = autorizaciones.get(i).getCantidadHorasExtras() * 20000;
                    } else {
                        pagoHorasExtras = autorizaciones.get(i).getCantidadHorasExtras() * 10000;
                    }
                }
            }
            i=i+1;
        }


        return pagoHorasExtras;
    }

    public double calcularDescuentoPorAtraso(double sueldoFijoMensual, List<Integer> atrasos){

        double descuentoPorAtraso = 0;

        descuentoPorAtraso = atrasos.get(0)*sueldoFijoMensual*0.01 + atrasos.get(1)*sueldoFijoMensual*0.03 + atrasos.get(2)*sueldoFijoMensual*0.06;

        return descuentoPorAtraso;
    }

    public double calcularDescuentoPorInasistencia(double sueldoFijoMensual, List<JustificativoEntity> justificativos, EmpleadoEntity empleado){

        double descuentoPorInasistencia = 0;

        int cont=0;
        int i=0;
        while(i< justificativos.size()){
            if(justificativos.get(i).getRutEmpleado().equals(empleado.getRutEmpleado())){
                if(justificativos.get(i).getJustificada()==0){
                    cont = cont + 1 ;
                }
            }
            i = i + 1;
        }
        descuentoPorInasistencia = cont*sueldoFijoMensual*0.15;

        return descuentoPorInasistencia;
    }

}
