/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitacora;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SilviaElena
 */
public class BitacoraCSV extends Bitacora {

    private static String pathCSV;

    public BitacoraCSV() {
        BitacoraCSV.pathCSV = "Registro_Historico.csv";
    }

    @Override
    public void crearArchivo() {
        try {
            FileWriter archivo = new FileWriter(BitacoraCSV.pathCSV);
            archivo.append("_____________________________________________________\n");
            archivo.append("  ------------- |  Sistema Amortizacion  | -------------\n");
            archivo.append("_____________________________________________________\n");
            archivo.close();
        } catch (IOException ex) {
            System.out.println("No se pudo crear el archivo");
        }
    }

    @Override
    public void añadirRegristro(String nombre, double monto, int plazo, double interes, String sistema) {
        if (!validarArchivo(this.pathCSV)) 
            crearArchivo();
        crearNuevoRegistro(nombre, monto, plazo, interes, sistema);
        
    }

    @Override
    public void crearNuevoRegistro(String nombre, double monto, int plazo, double interes, String sistema) {
        try {
            FileWriter archivo = new FileWriter(BitacoraCSV.pathCSV,true);
            archivo.append("-------------------------Registro------------------------------\n");
            archivo.append("Nombre_Cliente: " + nombre + "\n");
            archivo.append("Monto_Prestamo_Otorgado: " + String.valueOf(monto) + "\n");
            archivo.append("Plazo_Prestamo: " + String.valueOf(plazo) + "\n");
            archivo.append("Interes_Prestamo: " + String.valueOf(interes) + "\n");
            archivo.append("Sistema: " + sistema + "\n");
            archivo.append("_____________________________________________________\n");
            archivo.flush();
            archivo.close();
        } catch (IOException ex) {
            Logger.getLogger(BitacoraCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
