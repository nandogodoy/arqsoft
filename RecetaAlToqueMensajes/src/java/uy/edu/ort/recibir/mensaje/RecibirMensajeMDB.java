/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.recibir.mensaje;

import java.util.logging.*;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MapMessage;
import uy.edu.ort.procesar.mensaje.ProcesarMensajeSBLocal;

/**
 *
 * @author Richard
 */
@MessageDriven(mappedName = "jms/SDEQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class RecibirMensajeMDB implements MessageListener {

    @EJB
    ProcesarMensajeSBLocal mensajeSBLocal;

    public RecibirMensajeMDB() {
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof MapMessage) {
//Asigno el procesamiento del mensaje
            mensajeSBLocal.procesoMensaje((MapMessage) message);
        }
    }

}
