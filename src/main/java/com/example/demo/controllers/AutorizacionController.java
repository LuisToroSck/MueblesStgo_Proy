package com.example.demo.controllers;

import com.example.demo.entities.AutorizacionEntity;
import com.example.demo.services.AutorizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AutorizacionController {

    @Autowired
    AutorizacionService autorizacionService;

    @GetMapping("/autorizaciones")
    public String listarAutorizaciones(Model model){
        model.addAttribute("autorizaciones",autorizacionService.listarAutorizaciones());
        return "autorizaciones";
    }

    @GetMapping("/autorizaciones/nuevo")
    public String mostrarIngresarAutorizacion(Model model){
        AutorizacionEntity autorizacion = new AutorizacionEntity();
        model.addAttribute("autorizacion",autorizacion);
        return "crear_autorizacion";
    }

    @PostMapping("/autorizaciones")
    public String guardarAutorizaciones(@ModelAttribute("autorizacion") AutorizacionEntity autorizacion){
        autorizacionService.guardarAutorizacion(autorizacion);
        return "redirect:/autorizaciones";
    }

}
