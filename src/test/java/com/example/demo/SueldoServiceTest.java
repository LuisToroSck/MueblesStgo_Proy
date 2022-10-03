package com.example.demo;

import com.example.demo.entities.*;
import com.example.demo.repositories.SueldoRepository;
import com.example.demo.services.DataRelojService;
import com.example.demo.services.SueldoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SueldoServiceTest {

    SueldoService sueldoService = new SueldoService();

    @Autowired
    SueldoRepository sueldoRepository;

    @Test
    public void listarSueldos(){
        List<SueldoEntity> sueldos = (List<SueldoEntity>) sueldoRepository.findAll();
        assertThat(sueldos).size().isGreaterThan(0);
    }

    @Test
    void calcularSueldoBruto(){
        double sueldoFijoMensual            = 1700000;
        double bonificacionPorAniosServicio = 85000;
        double pagoHorasExtras              = 50000;
        double descuentoPorAtraso           = 17000;
        double descuentoPorInasistencia     = 255000;

        double sueldoBruto = sueldoService.calcularSueldoBruto(sueldoFijoMensual,bonificacionPorAniosServicio,pagoHorasExtras,descuentoPorAtraso,descuentoPorInasistencia);

        assertEquals(1563000,sueldoBruto,0.0);
    }

    @Test
    void calcularCotizacionPrevisional(){
        double sueldoBruto = 1563000;

        double cotizacionPrevisional = sueldoService.calcularCotizacionPrevisional(sueldoBruto);

        assertEquals(156300,cotizacionPrevisional,0.0);
    }

    @Test
    void calcularCotizacionSalud(){
        double sueldoBruto = 1563000;

        double cotizazcionSalud = sueldoService.calcularCotizacionSalud(sueldoBruto);

        assertEquals(125040,cotizazcionSalud,0.0);
    }

    @Test
    void calcularSueldoFinal(){
        double sueldoBruto           = 1563000;
        double cotizacionPrevisional = 156300;
        double cotizacionSalud       = 125040;

        double sueldoFinal = sueldoService.calcularSueldoFinal(sueldoBruto,cotizacionPrevisional,cotizacionSalud);

        assertEquals(1281660,sueldoFinal,0.0);
    }

    @Test
    void calcularPlanilla(){
        // Lista empleados
        List<EmpleadoEntity> empleados = new ArrayList<>();
        // Entidad empleado
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
        empleados.add(empleado);

        // Lista justificativos
        List<JustificativoEntity> justificativos = new ArrayList<>();
        // Entidad justificativo
        JustificativoEntity justificativo = new JustificativoEntity();
        justificativo.setRutEmpleado("20.391.194-7");
        justificativo.setJustificada(1);
        String fechaStr = "2022-02-02";
        Date fechaDate  = Date.valueOf(fechaStr);
        justificativo.setFecha(fechaDate);
        justificativos.add(justificativo);

        // Lista marcasReloj
        List<DatarelojEntity> marcasReloj = new ArrayList<>();
        // Entidad marcaReloj 1
        DatarelojEntity marcaReloj1 = new DatarelojEntity();
        marcaReloj1.setRutEmpleadoReloj("20.391.194-7");
        String fechaRelojStr1 = "2022-03-03";
        Date fechaRelojDate1  = Date.valueOf(fechaRelojStr1);
        marcaReloj1.setFecha(fechaRelojDate1);
        String horaStr1 = "08:00:00";
        Time horaTime1  = Time.valueOf(horaStr1);
        marcaReloj1.setHora(horaTime1);
        marcasReloj.add(marcaReloj1);
        // Entidad marcaReloj 2
        DatarelojEntity marcaReloj2 = new DatarelojEntity();
        marcaReloj2.setRutEmpleadoReloj("20.391.194-7");
        String fechaRelojStr2 = "2022-03-03";
        Date fechaRelojDate2  = Date.valueOf(fechaRelojStr2);
        marcaReloj2.setFecha(fechaRelojDate2);
        String horaStr2 = "20:00:00";
        Time horaTime2  = Time.valueOf(horaStr2);
        marcaReloj2.setHora(horaTime2);
        marcasReloj.add(marcaReloj2);

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


    }

}
