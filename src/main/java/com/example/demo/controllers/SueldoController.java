package com.example.demo.controllers;

import com.example.demo.entities.AutorizacionEntity;
import com.example.demo.entities.DatarelojEntity;
import com.example.demo.entities.EmpleadoEntity;
import com.example.demo.entities.JustificativoEntity;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping
public class SueldoController {

    @Autowired
    SueldoService sueldoService;

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    JustificativoService justificativoService;

    @Autowired
    DataRelojService dataRelojService;

    @Autowired
    AutorizacionService autorizacionService;

    @GetMapping("/calcularPlanilla")
    public String calcularPlanilla(Model model){

        List<EmpleadoEntity> empleados           = empleadoService.obtenerEmpleados();
        List<JustificativoEntity> justificativos = justificativoService.listarJustificativos();
        List<DatarelojEntity> marcasReloj        = dataRelojService.listarMarcasReloj();
        List<AutorizacionEntity> autorizaciones  = autorizacionService.listarAutorizaciones();

        sueldoService.eliminarSueldos();
        sueldoService.calcularPlanilla(empleados,justificativos,marcasReloj,autorizaciones);

        int anioActual  = LocalDateTime.now().getYear();

        model.addAttribute("anioActual",anioActual);
        model.addAttribute("sueldos",sueldoService.listarSueldos());
        model.addAttribute("empleados",empleados);
        return "planilla";
    }

}
