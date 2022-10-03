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
public class DataRelojServiceTest {

    DataRelojService dataRelojService = new DataRelojService();

    /*
    @Test
    public void guardarDataReloj(){}

    @Test
    public void guardarDatos(){}

    @Test
    public void listarMarcasReloj(){}
    */

    @Test
    public void calcularAtrasos(){
        // Lista marcasReloj
        List<DatarelojEntity> marcasReloj = new ArrayList<>();
        // Entidad marcaReloj 1
        DatarelojEntity marcaReloj1 = new DatarelojEntity();
        marcaReloj1.setRutEmpleadoReloj("20.391.194-7");
        String fechaRelojStr1 = "2022-03-03";
        Date fechaRelojDate1  = Date.valueOf(fechaRelojStr1);
        marcaReloj1.setFecha(fechaRelojDate1);
        String horaStr1 = "08:15:00";
        Time horaTime1  = Time.valueOf(horaStr1);
        marcaReloj1.setHora(horaTime1);
        marcasReloj.add(marcaReloj1);
        // Entidad marcaReloj 2
        DatarelojEntity marcaReloj2 = new DatarelojEntity();
        marcaReloj2.setRutEmpleadoReloj("20.391.194-7");
        String fechaRelojStr2 = "2022-03-04";
        Date fechaRelojDate2  = Date.valueOf(fechaRelojStr2);
        marcaReloj2.setFecha(fechaRelojDate2);
        String horaStr2 = "08:30:00";
        Time horaTime2  = Time.valueOf(horaStr2);
        marcaReloj2.setHora(horaTime2);
        marcasReloj.add(marcaReloj2);
        // Entidad marcaReloj 3
        DatarelojEntity marcaReloj3 = new DatarelojEntity();
        marcaReloj3.setRutEmpleadoReloj("20.391.194-7");
        String fechaRelojStr3 = "2022-03-05";
        Date fechaRelojDate3  = Date.valueOf(fechaRelojStr3);
        marcaReloj3.setFecha(fechaRelojDate3);
        String horaStr3 = "08:50:00";
        Time horaTime3  = Time.valueOf(horaStr3);
        marcaReloj3.setHora(horaTime3);
        marcasReloj.add(marcaReloj3);

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

        List<Integer> atrasos = dataRelojService.calcularAtrasos(marcasReloj,empleado);
        assertEquals(1,atrasos.get(0),0.0);
        assertEquals(1,atrasos.get(1),0.0);
        assertEquals(1,atrasos.get(2),0.0);
    }


}
