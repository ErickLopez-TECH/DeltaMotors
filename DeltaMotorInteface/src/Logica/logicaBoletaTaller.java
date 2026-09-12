package Logica;

import java.util.ArrayList;
import Datos.objAsignacionMante;

public class logicaBoletaTaller {
    
    public ArrayList configuraCMB(String placa) {
        logicaAsignacionMante logica = new logicaAsignacionMante();
        ArrayList resultado = new ArrayList<>();
        
        for (objAsignacionMante Asigna : logica.obtenerListaAsignaciones()) {
            if (Asigna.getPlacaVehiculo().equalsIgnoreCase(placa)) {
                resultado.add(Asigna.getNombreMantenimiento());
            }
        }
        return resultado;
    }
}