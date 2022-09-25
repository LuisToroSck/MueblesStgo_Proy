package com.example.demo.controllers;

import com.example.demo.entities.EmpleadoEntity;
import com.example.demo.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/listar")
    public String listar(Model model){
        ArrayList<EmpleadoEntity> empleados = empleadoService.obtenerEmpleados();
        model.addAttribute("empleados",empleados);
        return "home";
    }

    @GetMapping("/empleados")
    public String listarEmpleados(Model model){
        model.addAttribute("empleados",empleadoService.obtenerEmpleados());
        return "empleados";
    }


    @GetMapping("/empleados/nuevo")
    public String crearEmpleadoForm(Model model){
        EmpleadoEntity empleado = new EmpleadoEntity();
        model.addAttribute("empleado",empleado);
        return "crear_empleado";
    }

    @PostMapping("/empleados")
    public String guardarEmpleado(@ModelAttribute("empleado") EmpleadoEntity empleado){
        empleadoService.guardarEmpleado(empleado);
        return "redirect:/empleados";
    }
}
