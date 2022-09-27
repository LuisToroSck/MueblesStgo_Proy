package com.example.demo.services;

import com.example.demo.entities.DatarelojEntity;
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

    public void calcularInasistencias(List<DatarelojEntity> marcasReloj){
        List<JustificativoEntity> justificativos = justificativoRepository.findAll();
        int i = 0;
        while(i < marcasReloj.size()){
            if( ((marcasReloj.get(i).getHora().getHours() >= 9) && (marcasReloj.get(i).getHora().getMinutes() > 10) || (marcasReloj.get(i).getHora().getHours() >=10)) && (marcasReloj.get(i).getHora().getHours() < 14)){
                JustificativoEntity nuevoJustificativo = new JustificativoEntity();

                int idNuevo = justificativos.size() + 1;
                Long longId = (long) idNuevo;

                nuevoJustificativo.setIdJustificativo(longId);
                nuevoJustificativo.setRutEmpleado(marcasReloj.get(i).getRutEmpleadoReloj());
                nuevoJustificativo.setFecha(marcasReloj.get(i).getFecha());
                nuevoJustificativo.setJustificada(0);

                justificativos.add(nuevoJustificativo);
            }
            i = i + 1;
        }
        i = 0;
        while(i < justificativos.size()){

            guardarJustificativo(justificativos.get(i));
            i = i + 1;
        }
    }
}