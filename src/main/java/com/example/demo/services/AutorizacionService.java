package com.example.demo.services;

import com.example.demo.entities.AutorizacionEntity;
import com.example.demo.repositories.AutorizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorizacionService {

    @Autowired
    private AutorizacionRepository autorizacionRepository;

    public List<AutorizacionEntity> listarAutorizaciones(){
        return autorizacionRepository.findAll();
    }

    public AutorizacionEntity guardarAutorizacion(AutorizacionEntity autorizacion){
        return autorizacionRepository.save(autorizacion);
    }

}
