package com.example.demo;

import com.example.demo.entities.AutorizacionEntity;
import com.example.demo.entities.DatarelojEntity;
import com.example.demo.entities.EmpleadoEntity;
import com.example.demo.entities.JustificativoEntity;
import com.example.demo.services.DataRelojService;
import com.example.demo.services.OficinaRRHH;
import com.example.demo.services.SueldoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OficinaRRHHTest {

    OficinaRRHH oficinaRRHH = new OficinaRRHH();

    @Test
    public void calcularSueldoFijoMensual(){
        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado.setRutEmpleado("20.391.194-7");
        empleado.setApellidos("Toro Flores");
        empleado.setNombres("Luis Javier");
        String fechaIngresoStr = "2022-06-02";
        Date fechaIngresoDate  = Date.valueOf(fechaIngresoStr);
        empleado.setFechaIngreso(fechaIngresoDate);
        empleado.setCategoria("A");
        String fechaNacimientoStr = "2000-06-02";
        Date fechaNacimientoDate  = Date.valueOf(fechaNacimientoStr);
        empleado.setFechaNacimiento(fechaNacimientoDate);

        double sueldoFijoMensual = oficinaRRHH.calcularSueldoFijoMensual(empleado);
        assertEquals(1700000,sueldoFijoMensual,0.0);

        empleado.setCategoria("B");
        sueldoFijoMensual = oficinaRRHH.calcularSueldoFijoMensual(empleado);
        assertEquals(1200000,sueldoFijoMensual,0.0);

        empleado.setCategoria("C");
        sueldoFijoMensual = oficinaRRHH.calcularSueldoFijoMensual(empleado);
        assertEquals(800000,sueldoFijoMensual,0.0);
    }

    @Test
    public void calcularBonificacionPorAniosServicio(){
        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado.setRutEmpleado("20.391.194-7");
        empleado.setApellidos("Toro Flores");
        empleado.setNombres("Luis Javier");
        // Años de servicio >= 25
        String fechaIngresoStr = "1995-06-02";
        Date fechaIngresoDate  = Date.valueOf(fechaIngresoStr);
        empleado.setFechaIngreso(fechaIngresoDate);
        empleado.setCategoria("A");
        String fechaNacimientoStr = "1960-06-02";
        Date fechaNacimientoDate  = Date.valueOf(fechaNacimientoStr);
        empleado.setFechaNacimiento(fechaNacimientoDate);

        double sueldoFijoMensual = 1700000;

        double bonificacionPorAniosServicio = oficinaRRHH.calcularBonificacionPorAniosServicio(empleado,sueldoFijoMensual);
        assertEquals(289000,bonificacionPorAniosServicio,0.0);

        // Años de servicio >= 20 Y < 25
        fechaIngresoStr = "2000-06-02";
        fechaIngresoDate  = Date.valueOf(fechaIngresoStr);
        empleado.setFechaIngreso(fechaIngresoDate);
        bonificacionPorAniosServicio = oficinaRRHH.calcularBonificacionPorAniosServicio(empleado,sueldoFijoMensual);
        assertEquals(238000,bonificacionPorAniosServicio,0.00001);

        // Años de servicio >= 15 Y < 20
        fechaIngresoStr = "2005-06-02";
        fechaIngresoDate  = Date.valueOf(fechaIngresoStr);
        empleado.setFechaIngreso(fechaIngresoDate);
        bonificacionPorAniosServicio = oficinaRRHH.calcularBonificacionPorAniosServicio(empleado,sueldoFijoMensual);
        assertEquals(187000,bonificacionPorAniosServicio,0.0);

        // Años de servicio >= 10 Y < 15
        fechaIngresoStr = "2010-06-02";
        fechaIngresoDate  = Date.valueOf(fechaIngresoStr);
        empleado.setFechaIngreso(fechaIngresoDate);
        bonificacionPorAniosServicio = oficinaRRHH.calcularBonificacionPorAniosServicio(empleado,sueldoFijoMensual);
        assertEquals(136000,bonificacionPorAniosServicio,0.0);

        // Años de servicio >= 5 Y < 10
        fechaIngresoStr = "2015-06-02";
        fechaIngresoDate  = Date.valueOf(fechaIngresoStr);
        empleado.setFechaIngreso(fechaIngresoDate);
        bonificacionPorAniosServicio = oficinaRRHH.calcularBonificacionPorAniosServicio(empleado,sueldoFijoMensual);
        assertEquals(85000,bonificacionPorAniosServicio,0.0);

        // Años de servicio < 5
        fechaIngresoStr = "2020-06-02";
        fechaIngresoDate  = Date.valueOf(fechaIngresoStr);
        empleado.setFechaIngreso(fechaIngresoDate);
        bonificacionPorAniosServicio = oficinaRRHH.calcularBonificacionPorAniosServicio(empleado,sueldoFijoMensual);
        assertEquals(0,bonificacionPorAniosServicio,0.0);
    }

    @Test
    public void calcularPagoHorasExtras(){
        // Empleado
        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado.setRutEmpleado("20.391.194-7");
        empleado.setApellidos("Toro Flores");
        empleado.setNombres("Luis Javier");
        String fechaIngresoStr = "2022-06-02";
        Date fechaIngresoDate  = Date.valueOf(fechaIngresoStr);
        empleado.setFechaIngreso(fechaIngresoDate);
        empleado.setCategoria("A");
        String fechaNacimientoStr = "2000-06-02";
        Date fechaNacimientoDate  = Date.valueOf(fechaNacimientoStr);
        empleado.setFechaNacimiento(fechaNacimientoDate);

        // Lista autorizaciones
        List<AutorizacionEntity> autorizaciones = new ArrayList<>();
        // Entidad autorizacion
        AutorizacionEntity autorizacion = new AutorizacionEntity();
        autorizacion.setRutEmpleado("20.391.194-7");
        String fechaAutorizacionStr = "2022-03-03";
        Date fechaAutorizacionDate  = Date.valueOf(fechaAutorizacionStr);
        autorizacion.setFecha(fechaAutorizacionDate);
        autorizacion.setCantidadHorasExtras(2);
        autorizacion.setAutorizado(1);
        autorizaciones.add(autorizacion);

        double pagoHorasExtras = oficinaRRHH.calcularPagoHorasExtras(empleado,autorizaciones);
        assertEquals(50000,pagoHorasExtras,0.0);

        empleado.setCategoria("B");
        pagoHorasExtras = oficinaRRHH.calcularPagoHorasExtras(empleado,autorizaciones);
        assertEquals(40000,pagoHorasExtras,0.0);

        empleado.setCategoria("C");
        pagoHorasExtras = oficinaRRHH.calcularPagoHorasExtras(empleado,autorizaciones);
        assertEquals(20000,pagoHorasExtras,0.0);

    }

    @Test
    public void calcularDescuentoPorAtraso(){
        double sueldoFijoMensual = 1700000;

        List<Integer> atrasos = new ArrayList<>();
        atrasos.add(3);
        atrasos.add(2);
        atrasos.add(1);

        double descuentoPorAtraso = oficinaRRHH.calcularDescuentoPorAtraso(sueldoFijoMensual,atrasos);
        assertEquals(255000,descuentoPorAtraso,0.0);
    }

    @Test
    public void calcularDescuentoPorInasistencia(){
        double sueldoFijoMensual = 1700000;

        // Lista justificativos
        List<JustificativoEntity> justificativos = new ArrayList<>();
        // Entidad justificativo
        JustificativoEntity justificativo = new JustificativoEntity();
        justificativo.setRutEmpleado("20.391.194-7");
        justificativo.setJustificada(0);
        String fechaStr = "2022-02-02";
        Date fechaDate  = Date.valueOf(fechaStr);
        justificativo.setFecha(fechaDate);
        justificativos.add(justificativo);

        // Empleado
        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado.setRutEmpleado("20.391.194-7");
        empleado.setApellidos("Toro Flores");
        empleado.setNombres("Luis Javier");
        String fechaIngresoStr = "2022-06-02";
        Date fechaIngresoDate  = Date.valueOf(fechaIngresoStr);
        empleado.setFechaIngreso(fechaIngresoDate);
        empleado.setCategoria("A");
        String fechaNacimientoStr = "2000-06-02";
        Date fechaNacimientoDate  = Date.valueOf(fechaNacimientoStr);
        empleado.setFechaNacimiento(fechaNacimientoDate);

        double descuentoPorInasistencia = oficinaRRHH.calcularDescuentoPorInasistencia(sueldoFijoMensual,justificativos,empleado);
        assertEquals(255000,descuentoPorInasistencia,0.0);
    }
}
