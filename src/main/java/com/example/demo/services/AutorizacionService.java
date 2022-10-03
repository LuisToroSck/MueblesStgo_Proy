package com.example.demo.services;

import com.example.demo.entities.AutorizacionEntity;
import com.example.demo.entities.DatarelojEntity;
import com.example.demo.repositories.AutorizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return autorizacionRepository.save(autorizacion);
    }

    public void actualizarAutorizacion(int aut, Long id){
        autorizacionRepository.actualizarAutorizacion(aut,id);
    }

    public void calcularHorasExtras(List<DatarelojEntity> marcasReloj){
        int j = 0;
        while(j < marcasReloj.size()){
            int horasExtras = 0;
            if(marcasReloj.get(j).getHora().getHours() >= 19) {
                horasExtras = marcasReloj.get(j).getHora().getHours() - 18;

                AutorizacionEntity nuevaAutorizacion = new AutorizacionEntity();
                nuevaAutorizacion.setRutEmpleado(marcasReloj.get(j).getRutEmpleadoReloj());
                nuevaAutorizacion.setFecha(marcasReloj.get(j).getFecha());
                nuevaAutorizacion.setCantidadHorasExtras(horasExtras);
                nuevaAutorizacion.setAutorizado(0);

                guardarAutorizacion(nuevaAutorizacion);
            }
            j = j + 1;
        }
    }

    public void eliminarAutorizaciones(){
        autorizacionRepository.deleteAll();
    }
}
