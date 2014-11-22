/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.procesar.mensaje;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import uy.edu.ort.dominio.*;
import uy.edu.ort.negocio.core.RecetaSBNegocio;
/**
 *
 * @author Richard
 */
@Stateless
public class ProcesarMensajeSB implements ProcesarMensajeSBLocal {
    @EJB
    RecetaSBNegocio recetaSB;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void procesoMensaje(MapMessage mensaje) {
        System.out.println("Procesar Mensaje SB recibe :" + mensaje);
        MapMessage mapMessage = (MapMessage) mensaje;
        Receta receta;
        Usuario usuario;
        try { 
            switch (mensaje.getString("comando"))
            {
                case "01":
                    receta = new Receta();
                    usuario= new Usuario();
                    receta.setNombre(mensaje.getString("nombre"));
                    receta.setProcedimiento(mensaje.getString("procedimiento"));
                    List<Ingrediente> lista= new ArrayList();
                    String ingredientes = mensaje.getString("ingredientes");
                    String [] array = ingredientes.split(":");
                    for( int i=0; i< array.length; i++)
                    {
                        Ingrediente ingrediente= new Ingrediente(array[i]);
                        lista.add(ingrediente);
                    }
                    receta.setIngredientes(lista);
                    usuario.setNombre(mensaje.getString("usuario"));
                    //llamo al negocio
                    recetaSB.alta(receta, usuario);
                    //TODO
                    break;
                case "02":
                    receta= new Receta();
                    receta.setNombre(mensaje.getString("nombre"));
                    float valoracion= mensaje.getFloat("valoracion");
                    recetaSB.valorar(receta,valoracion);
                    break;
            }
        }catch (JMSException ex) {
            Logger.getLogger(ProcesarMensajeSB.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
