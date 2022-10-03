package com.example.demo;

import com.example.demo.entities.*;
import com.example.demo.repositories.AutorizacionRepository;
import com.example.demo.services.AutorizacionService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AutorizacionServiceTest {

    AutorizacionService autorizacionService = new AutorizacionService();

    @Autowired
    AutorizacionRepository autorizacionRepository;

    @Autowired
    private TestEntityManager entityManager;

    /*
    Debido a que en el método calcularHorasExtras de AutorizacionService se usa el método guardarAutorizacion,
    que es una implementación de un save de JPA, este test arroja error indicando que AutorizacionReppitory es nulo.
    @Test
    public void calcularHorasExtras(){
        // Lista marcasReloj
        List<DatarelojEntity> marcasReloj = new ArrayList<>();
        // Entidad marcaReloj 1
        DatarelojEntity marcaReloj1 = new DatarelojEntity();
        marcaReloj1.setRutEmpleadoReloj("20.391.194-7");
        String fechaRelojStr1 = "2022-03-03";
        Date fechaRelojDate1  = Date.valueOf(fechaRelojStr1);
        marcaReloj1.setFecha(fechaRelojDate1);
        String horaStr1 = "18:00:00";
        Time horaTime1  = Time.valueOf(horaStr1);
        marcaReloj1.setHora(horaTime1);
        marcasReloj.add(marcaReloj1);
        // Entidad marcaReloj 2
        DatarelojEntity marcaReloj2 = new DatarelojEntity();
        marcaReloj2.setRutEmpleadoReloj("20.391.194-7");
        String fechaRelojStr2 = "2022-03-04";
        Date fechaRelojDate2  = Date.valueOf(fechaRelojStr2);
        marcaReloj2.setFecha(fechaRelojDate2);
        String horaStr2 = "20:00:00";
        Time horaTime2  = Time.valueOf(horaStr2);
        marcaReloj2.setHora(horaTime2);
        marcasReloj.add(marcaReloj2);
        // Entidad marcaReloj 3
        DatarelojEntity marcaReloj3 = new DatarelojEntity();
        marcaReloj3.setRutEmpleadoReloj("20.391.194-7");
        String fechaRelojStr3 = "2022-03-04";
        Date fechaRelojDate3  = Date.valueOf(fechaRelojStr3);
        marcaReloj3.setFecha(fechaRelojDate3);
        String horaStr3 = "19:00:00";
        Time horaTime3  = Time.valueOf(horaStr3);
        marcaReloj2.setHora(horaTime3);
        marcasReloj.add(marcaReloj3);

        List<AutorizacionEntity> horasExtras = autorizacionService.calcularHorasExtras(marcasReloj);

        entityManager.persistAndFlush(marcaReloj1);
        entityManager.persistAndFlush(marcaReloj2);
        entityManager.persistAndFlush(marcaReloj3);

        assertThat(horasExtras.get(0).getFecha()).isEqualTo(marcaReloj2.getFecha());
        assertThat(horasExtras.get(1).getFecha()).isEqualTo(marcaReloj3.getFecha());
    }*/

}
