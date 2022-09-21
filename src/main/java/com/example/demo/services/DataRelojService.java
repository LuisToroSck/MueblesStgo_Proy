package com.example.demo.services;

import com.example.demo.entities.DatarelojEntity;
import com.example.demo.entities.EmpleadoEntity;
import com.example.demo.repositories.DataRelojRepository;
import com.example.demo.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

@Service
public class DataRelojService {

    @Autowired
    DataRelojRepository dataRelojRepository;
    public DatarelojEntity guardarDataReloj(DatarelojEntity reloj){
        return dataRelojRepository.save(reloj);
    }

    public void gurdarDatos() throws FileNotFoundException {
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

                    Time horaIngreso = new Time(formatoHora.parse(atributo.nextElement().toString()).getTime());
                    datarelojEntity.setHoraIngreso(horaIngreso);

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

}
