/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitacora;

import java.io.File;

/**
 *
 * @author SilviaElena
 */
public abstract class Bitacora {

    public abstract void crearArchivo();
    
    public Boolean validarArchivo(String path){
        File archivo = new File(path);
        return archivo.exists();
    }
    
    public abstract void añadirRegristro(String nombre, double monto, int plazo, double interes, String sistema);
    
    public abstract void crearNuevoRegistro(String nombre, double monto, int plazo, double interes, String sistema);
    
}
