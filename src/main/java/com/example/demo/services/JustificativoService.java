package com.example.demo.services;

import com.example.demo.entities.JustificativoEntity;
import com.example.demo.repositories.JustificativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JustificativoService {

    @Autowired
    private JustificativoRepository justificativoRepository;

    public List<JustificativoEntity> listarJustificativos(){
        return justificativoRepository.findAll();
    }

    public JustificativoEntity guardarJustificativo(JustificativoEntity justificativo){
        return justificativoRepository.save(justificativo);
    }

    public Optional<JustificativoEntity> listarId(Long id){
        return justificativoRepository.findById(id);
    }

    public void actualizarJustativo(int just, Long id){
        justificativoRepository.actualizarJustificativo(just,id);
    }

}