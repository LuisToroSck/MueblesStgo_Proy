package com.example.demo.controllers;

import com.example.demo.entities.JustificativoEntity;
import com.example.demo.repositories.JustificativoRepository;
import com.example.demo.services.JustificativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
@RequestMapping
public class JustificativoController {

    @Autowired
    JustificativoService justificativoService;

    @Autowired
    JustificativoRepository justificativoRepository;

    @GetMapping("/justificativos")
    public String listarJustificativos(Model model){
        model.addAttribute("justificativos",justificativoService.listarJustificativos());
        return "justificativos";
    }

    /*@GetMapping("/justificativos/nuevo")
    public String mostrarIngresarJustificativo(Model model){
        JustificativoEntity justificativo = new JustificativoEntity();
        model.addAttribute("justificativo",justificativo);
        return "crear_justificativo";
    }*/

    @PostMapping ("/justificativos")
    public String guardarJustificativos(@ModelAttribute("justificativo") JustificativoEntity justificativo){
        justificativoService.guardarJustificativo(justificativo);
        return "redirect:/justificativos";
    }

    /*@GetMapping("/ediirJustificativo/{id}")
    public String editarJustificativo(@PathVariable Long id, Model model){
        Optional<JustificativoEntity> justificativo = justificativoService.listarId(id);
        model.addAttribute("justificativo",justificativo);
        return "crear_justificativo";
    }*/

    @PostMapping("/guardarJustificativo/{id}")
    public String actualizarJustificativo(@PathVariable Long id){
        JustificativoEntity justificativo = justificativoRepository.getById(id);
        if(justificativo.getJustificada()==0){
            justificativoService.actualizarJustativo(1,id);
        }else{
            justificativoService.actualizarJustativo(0,id);
        }
        return "redirect:/justificativos";
    }

}
