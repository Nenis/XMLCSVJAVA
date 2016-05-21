/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitacora;

import java.io.*;
import java.util.logging.*;
import javax.xml.parsers.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author SilviaElena
 */
public class BitacoraXML extends Bitacora {

    private static String pathXML;

    public BitacoraXML() {
        this.pathXML = "Registro_Historico.xml";
    }

    @Override
    public void crearArchivo() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element element = document.createElement("Sistema_Amortizacion");
            Element registro = document.createElement("Registro_Historico");
            document.appendChild(element);
            element.appendChild(registro);
            transformarArchivo(document);

        } catch (ParserConfigurationException ex) {
            System.out.println("No se pudo crear el archivo");
        }
    }

    @Override
    public void añadirRegristro(String nombre, double monto, int plazo, double interes, String sistema) {
        if (!validarArchivo(BitacoraXML.pathXML))
            crearArchivo();
        crearNuevoRegistro(nombre, monto, plazo, interes, sistema);
    }

    @Override
    public void crearNuevoRegistro(String nombre, double monto, int plazo, double interes, String sistema) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(BitacoraXML.pathXML);

            Element etiqueta = doc.getDocumentElement();
            Element tagRegistro = (Element) etiqueta.getElementsByTagName("Registro_Historico").item(0);

            //Añadir nuevo registro
            Element nuevoRegistro = doc.createElement("Registro");

            Element nombreCliente = doc.createElement("Nombre_Cliente");
            nombreCliente.setTextContent(" " + nombre + " ");

            Element montoPrestamo = doc.createElement("Monto_Prestamo_Otorgado");
            montoPrestamo.setTextContent(String.valueOf(" " + monto + " "));

            Element plazoPrestamo = doc.createElement("Plazo_Prestamo");
            plazoPrestamo.setTextContent(String.valueOf(" " + plazo + " "));

            Element interesPrestamo = doc.createElement("Interes_Prestamo");
            interesPrestamo.setTextContent(String.valueOf(" " + interes + " "));

            Element sistemaAmortizacion = doc.createElement("Sistema");
            sistemaAmortizacion.setTextContent(" " + sistema + " ");

            nuevoRegistro.appendChild(nombreCliente);
            nuevoRegistro.appendChild(montoPrestamo);
            nuevoRegistro.appendChild(plazoPrestamo);
            nuevoRegistro.appendChild(interesPrestamo);
            nuevoRegistro.appendChild(sistemaAmortizacion);

            tagRegistro.appendChild(nuevoRegistro);
            transformarArchivo(doc);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(BitacoraXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void transformarArchivo(Document doc) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);

            StreamResult streamResult = new StreamResult(new File(this.pathXML));
            transformer.transform(source, streamResult);
        } catch (TransformerException ex) {
            System.out.println("No se pudo tranformar el archivo");
        }
    }
}
