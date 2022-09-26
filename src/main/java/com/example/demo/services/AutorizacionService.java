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

    /*public AutorizacionEntity guardarAutorizacion(AutorizacionEntity autorizacion){
        autorizacion.setAutorizado(1);
        return autorizacionRepository.save(autorizacion);
    }*/

    public AutorizacionEntity guardarAutorizacion(AutorizacionEntity autorizacion){
        return autorizacionRepository.save(autorizacion);
    }

    public Optional<AutorizacionEntity> listarId(Long id){
        return autorizacionRepository.findById(id);
    }

    public AutorizacionEntity getByRut(String rutEmpleado){
        return autorizacionRepository.getByRut(rutEmpleado);
    }

    public void actualizarAutorizacion(int aut, Long id){
        autorizacionRepository.actualizarAutorizacion(aut,id);
    }

    public void calcularHorasExtras(List<DatarelojEntity> marcasReloj){
        List<AutorizacionEntity> autorizaciones = autorizacionRepository.findAll();
        int j = 0;
        while(j < marcasReloj.size()){
            int horasExtras = 0;
            if(marcasReloj.get(j).getHora().getHours() >= 19) {
                horasExtras = marcasReloj.get(j).getHora().getHours() - 18;

                int i = 0;
                int existeRut = 0;
                while (i < autorizaciones.size()) {
                    if (marcasReloj.get(j).getRutEmpleadoReloj().equals(autorizaciones.get(i).getRutEmpleado())) {
                        autorizaciones.get(i).setCantidadHorasExtras(autorizaciones.get(i).getCantidadHorasExtras() + horasExtras);
                        i = autorizaciones.size() + 1;
                        existeRut = 1;
                    } else {
                        i = i + 1;
                    }
                }

                if (existeRut == 0) {

                    AutorizacionEntity nuevaAutorizacion = new AutorizacionEntity();

                    int idNuevo = autorizaciones.size() + 1;
                    Long longId = (long) idNuevo;

                    nuevaAutorizacion.setId(longId);
                    nuevaAutorizacion.setRutEmpleado(marcasReloj.get(j).getRutEmpleadoReloj());
                    nuevaAutorizacion.setCantidadHorasExtras(horasExtras);
                    nuevaAutorizacion.setAutorizado(0);

                    autorizaciones.add(nuevaAutorizacion);
                }
            }
            j = j + 1;
        }

        int k = 0;
        while(k < autorizaciones.size()){
            guardarAutorizacion(autorizaciones.get(k));
            k = k + 1;
        }


        /*era para mostrar lo que se guardó en el array de autorizaciones
        int k = 0;
        while(k< autorizaciones.size()){
            System.out.println(autorizaciones.get(k).getRutEmpleado());
            System.out.println(autorizaciones.get(k).getCantidadHorasExtras());
            k = k + 1;
        }*/
    }


}
