package com.example.demo.services;

import com.example.demo.entities.AutorizacionEntity;
import com.example.demo.entities.DatarelojEntity;
import com.example.demo.repositories.AutorizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorizacionService {

    @Autowired
    private AutorizacionRepository autorizacionRepository;

    public List<AutorizacionEntity> listarAutorizaciones(){
        return autorizacionRepository.findAll();
    }

    public AutorizacionEntity guardarAutorizacion(AutorizacionEntity autorizacion){
        /*autorizacion.setAutorizado(1);*/
        return autorizacionRepository.save(autorizacion);
    }

    public Optional<AutorizacionEntity> listarId(Long id){
        return autorizacionRepository.findById(id);
    }

    public void actualizarAutorizacion(int aut, Long id){
        autorizacionRepository.actualizarAutorizacion(aut,id);
    }

    public void calcularHorasExtras(DatarelojEntity reloj){
        List<AutorizacionEntity> autorizaciones = autorizacionRepository.findAll();
        int horasExtras = 0;
        if(reloj.getHora().getHours()>=19){
            horasExtras = reloj.getHora().getHours() - 18;
            int i=0;
            while(i<autorizaciones.size()){
                if(reloj.getRutEmpleadoReloj().equals(autorizaciones.get(i).getRutEmpleado())){

                }
                else{
                    i = i+1;
                }
            }

    }


}
