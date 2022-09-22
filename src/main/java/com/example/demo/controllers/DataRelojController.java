package com.example.demo.controllers;

import com.example.demo.services.DataRelojService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;

@Controller
@RequestMapping

public class DataRelojController {

    @Autowired
    DataRelojService reloj;

    @GetMapping("/cargarReloj")
    public String cargarReloj(RedirectAttributes ms) throws FileNotFoundException {
        reloj.gurdarDatos();
        ms.addFlashAttribute("mensaje", "Archivo guardado correctamente!!");
        return "redirect:/";
    }

}