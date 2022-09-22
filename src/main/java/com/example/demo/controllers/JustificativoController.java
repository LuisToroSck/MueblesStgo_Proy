package com.example.demo.controllers;

import com.example.demo.entities.JustificativoEntity;
import com.example.demo.repositories.JustificativoRepository;
import com.example.demo.services.JustificativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class JustificativoController {

    @Autowired
    JustificativoService justificativoService;

    @GetMapping("/justificativos")
    public String listarJustificativos(Model model){
        model.addAttribute("justificativos",justificativoService.listarJustificativos());
        return "justificativos";
    }

    @GetMapping("/justificativos/nuevo")
    public String mostrarIngresarJustificativo(Model model){
        JustificativoEntity justificativo = new JustificativoEntity();
        model.addAttribute("justificativo",justificativo);
        return "crear_justificativo";
    }

    @PostMapping ("/justificativos")
    public String guardarJustificativos(@ModelAttribute("justificativo") JustificativoEntity justificativo){
        justificativoService.guardarJustificativo(justificativo);
        return "redirect:/justificativos";
    }

}
