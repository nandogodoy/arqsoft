/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.procesar.mensaje;

import javax.ejb.Local;
import javax.jms.MapMessage;

/**
 *
 * @author Richard
 */
@Local
public interface ProcesarMensajeSBLocal {
    public void procesoMensaje ( MapMessage mensaje );
}
