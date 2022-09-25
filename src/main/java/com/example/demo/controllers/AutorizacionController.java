package com.example.demo.controllers;

import com.example.demo.entities.AutorizacionEntity;
import com.example.demo.repositories.AutorizacionRepository;
import com.example.demo.services.AutorizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class AutorizacionController {

    @Autowired
    AutorizacionService autorizacionService;

    @Autowired
    AutorizacionRepository autorizacionRepository;

    @GetMapping("/autorizaciones")
    public String listarAutorizaciones(Model model){
        model.addAttribute("autorizaciones",autorizacionService.listarAutorizaciones());
        return "autorizaciones";
    }

    /*@GetMapping("/autorizaciones/nuevo")
    public String mostrarIngresarAutorizacion(Model model){

        AutorizacionEntity autorizacion = new AutorizacionEntity();
        model.addAttribute("autorizacion",autorizacion);

        List<AutorizacionEntity> autorizaciones = autorizacionService.listarAutorizaciones();
        model.addAttribute("autorizaciones",autorizaciones);
        return "crear_autorizacion";
    }*/

    /* este es el guardar*/
    @PostMapping("/guardar/{id}")
    public String guardarAutorizaciones(@PathVariable Long id){
        AutorizacionEntity autorizacion = autorizacionRepository.getById(id);
        if(autorizacion.getAutorizado()==0){
            autorizacionService.actualizarAutorizacion(1, id);
        }else{
            autorizacionService.actualizarAutorizacion(0, id);
        }


        return "redirect:/autorizaciones";
    }

    /*@GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id,Model model){
        Optional<AutorizacionEntity> autorizacion = autorizacionService.listarId(id);
        model.addAttribute("autorizacion",autorizacion);
        return "crear_autorizacion";
    }*/
}
