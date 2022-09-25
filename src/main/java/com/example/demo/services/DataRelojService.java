package com.example.demo.services;

import com.example.demo.entities.DatarelojEntity;
import com.example.demo.entities.EmpleadoEntity;
import com.example.demo.repositories.DataRelojRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.List;

@Service
public class DataRelojService {

    @Autowired
    DataRelojRepository dataRelojRepository;
    public DatarelojEntity guardarDataReloj(DatarelojEntity reloj){
        return dataRelojRepository.save(reloj);
    }

    public void guardarDatos() throws FileNotFoundException {
        /*List<DatarelojEntity> datos = new ArrayList<DatarelojEntity>();*/
        String ruta = "cargas/marcaRelojEmpleados.txt";
        File archivo = new File(ruta);

        try {
            Scanner scanner = new Scanner(archivo);
            DatarelojEntity datarelojEntity = null;
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                StringTokenizer atributo = new StringTokenizer(linea, ";");
                datarelojEntity = new DatarelojEntity();

                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/mm/dd");
                SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm");
                while (atributo.hasMoreElements()) {

                    Date fecha = new Date(formatoFecha.parse(atributo.nextElement().toString()).getTime());
                    datarelojEntity.setFecha(fecha);

                    Time hora = new Time(formatoHora.parse(atributo.nextElement().toString()).getTime());
                    datarelojEntity.setHora(hora);

                    datarelojEntity.setRutEmpleadoReloj(atributo.nextElement().toString());
                }
                guardarDataReloj(datarelojEntity);
            }
            scanner.close();
        }
        catch (FileNotFoundException | ParseException e){
            e.printStackTrace();
        }
    }

    public List<DatarelojEntity> listarMarcasReloj(){
        return dataRelojRepository.findAll();
    }

    public List<Integer> calcularAtrasos(List<DatarelojEntity> marcasReloj, EmpleadoEntity empleado){
        List<Integer> atrasos = new ArrayList<>();
        atrasos.set(0,0);
        atrasos.set(1,0);
        atrasos.set(2,0);

        int i=0;
        while(i<marcasReloj.size()){
            if(marcasReloj.get(i).getRutEmpleadoReloj().equals(empleado.getRutEmpleado())){
                if( (marcasReloj.get(i).getHora().getHours() == 8) && (marcasReloj.get(i).getHora().getMinutes() > 10) && (marcasReloj.get(i).getHora().getMinutes() < 25)){
                    atrasos.set(0,atrasos.get(0)+1);
                }
                else if( (marcasReloj.get(i).getHora().getHours() == 8) && (marcasReloj.get(i).getHora().getMinutes() > 25) && (marcasReloj.get(i).getHora().getMinutes() < 45)){
                    atrasos.set(1,atrasos.get(1)+1);
                }
                else if(((marcasReloj.get(i).getHora().getHours() == 8) && ((marcasReloj.get(i).getHora().getMinutes() > 45))) || ((marcasReloj.get(i).getHora().getHours() == 9) && (marcasReloj.get(i).getHora().getMinutes() < 10))){
                    atrasos.set(1,atrasos.get(2)+1);
                }
            }
            i = i + 1;
        }
        return atrasos;
    }

}
