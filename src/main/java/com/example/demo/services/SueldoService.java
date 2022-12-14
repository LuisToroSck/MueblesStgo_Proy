package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.repositories.SueldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SueldoService {

    @Autowired
    private SueldoRepository sueldoRepository;

    @Autowired
    private OficinaRRHH oficinaService;

    @Autowired
    private DataRelojService dataRelojService;

    public void eliminarSueldos(){
        sueldoRepository.deleteAll();
    }

    public SueldoEntity guardarSueldo(SueldoEntity sueldo){
        return sueldoRepository.save(sueldo);
    }

    public List<SueldoEntity> listarSueldos(){
        return sueldoRepository.findAll();
    }

    public double calcularSueldoBruto(double sueldoFijoMensual, double bonificacionPorAniosServicio, double pagoHorasExtras, double descuentoPorAtraso, double descuentoPorInasistencia){

        double sueldoBruto = 0;

        sueldoBruto = sueldoFijoMensual + bonificacionPorAniosServicio + pagoHorasExtras - descuentoPorAtraso - descuentoPorInasistencia;

        return sueldoBruto;
    }

    public double calcularCotizacionPrevisional(double sueldoBruto){

        double cotizacionPrevisional = sueldoBruto*0.1;

        return cotizacionPrevisional;
    }

    public double calcularCotizacionSalud(double sueldoBruto){

        double cotizacionSalud = sueldoBruto*0.08;

        return cotizacionSalud;
    }

    public double calcularSueldoFinal(double sueldoBruto, double cotizacionPrevisional, double cotizacionSalud){

        double sueldoFinal = sueldoBruto - cotizacionPrevisional - cotizacionSalud;

        return sueldoFinal;
    }

    public void calcularPlanilla(List<EmpleadoEntity> empleados, List<JustificativoEntity> justificativos, List<DatarelojEntity> marcasReloj, List<AutorizacionEntity> autorizaciones){

        int i=0;
        while(i<empleados.size()){

            double sueldoFijoMensual            = oficinaService.calcularSueldoFijoMensual(empleados.get(i));
            double bonificacionPorAniosServicio = oficinaService.calcularBonificacionPorAniosServicio(empleados.get(i),sueldoFijoMensual);
            double pagoHorasExtras              = oficinaService.calcularPagoHorasExtras(empleados.get(i),autorizaciones);
            List<Integer> atrasos               = dataRelojService.calcularAtrasos(marcasReloj,empleados.get(i));
            double descuentoPorAtraso           = oficinaService.calcularDescuentoPorAtraso(sueldoFijoMensual,atrasos);
            double descuentoPorInasistencia     = oficinaService.calcularDescuentoPorInasistencia(sueldoFijoMensual,justificativos,empleados.get(i));

            double sueldoBruto           = calcularSueldoBruto(sueldoFijoMensual,bonificacionPorAniosServicio,pagoHorasExtras,descuentoPorAtraso,descuentoPorInasistencia);
            double cotizacionPrevisional = calcularCotizacionPrevisional(sueldoBruto);
            double cotizacionSalud       = calcularCotizacionSalud(sueldoBruto);

            double sueldoFinal = calcularSueldoFinal(sueldoBruto,cotizacionPrevisional,cotizacionSalud);

            SueldoEntity nuevoSueldo = new SueldoEntity();

            nuevoSueldo.setRutEmpleado(empleados.get(i).getRutEmpleado());
            nuevoSueldo.setSueldoFijoMensual(sueldoFijoMensual);
            nuevoSueldo.setBonificacionAniosServicio(bonificacionPorAniosServicio);
            nuevoSueldo.setPagoHorasExtras(pagoHorasExtras);
            nuevoSueldo.setDescuentos(descuentoPorAtraso+descuentoPorInasistencia);
            nuevoSueldo.setSueldoBruto(sueldoBruto);
            nuevoSueldo.setCotizacionPrevisional(cotizacionPrevisional);
            nuevoSueldo.setCotizacionSalud(cotizacionSalud);
            nuevoSueldo.setSueldoFinal(sueldoFinal);

            guardarSueldo(nuevoSueldo);

            i=i+1;
        }
    }
}
