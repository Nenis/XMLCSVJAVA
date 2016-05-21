/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitacora;

/**
 *
 * @author SilviaElena
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        Bitacora bit = new BitacoraCSV();
        bit.añadirRegristro("Silvia Elena Alpizar", 200000, 4, 0.19, "Aleman");
        bit.añadirRegristro("Valery Alpizar", 60000, 5, 0.15, "Frances");

        Bitacora bit2 = new BitacoraXML();
        bit2.añadirRegristro("Silvia Elena Alpizar", 200000, 4, 0.19, "Aleman");
        bit2.añadirRegristro("Valery Alpizar", 60000, 5, 0.15, "Frances");

    }

}
