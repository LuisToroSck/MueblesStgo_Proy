package com.example.demo.controllers;

import com.example.demo.entities.AutorizacionEntity;
import com.example.demo.entities.DatarelojEntity;
import com.example.demo.repositories.DataRelojRepository;
import com.example.demo.services.AutorizacionService;
import com.example.demo.services.DataRelojService;
import com.example.demo.services.JustificativoService;
import org.hibernate.boot.model.relational.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.util.List;

@Controller
@RequestMapping

public class DataRelojController {

    @Autowired
    DataRelojService reloj;

    @Autowired
    AutorizacionService autorizacionService;

    @Autowired
    JustificativoService justificativoService;

    @GetMapping("/cargarReloj")
    public String cargarReloj(RedirectAttributes ms) throws FileNotFoundException {
        autorizacionService.eliminarAutorizaciones();
        justificativoService.eliminarInasistencias();
        reloj.eliminarMarcasReloj();

        reloj.guardarDatos();
        List<DatarelojEntity> marcasReloj = reloj.listarMarcasReloj();
        autorizacionService.calcularHorasExtras(marcasReloj);
        justificativoService.calcularInasistencias(marcasReloj);
        ms.addFlashAttribute("mensaje", "Archivo subido");
        return "redirect:/";
    }
}
