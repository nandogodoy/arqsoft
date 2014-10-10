/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.servicios.ws;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import uy.edu.ort.persistencia.UsuarioSBLocal;
import uy.edu.ort.entidades.UsuarioEntity;
/**
 *
 * @author Richard
 */
@WebService(serviceName = "UsuarioSB")
@Stateless()
public class UsuarioSB {
    @EJB
    private UsuarioSBLocal ejbUsuario;
    
    @WebMethod(operationName="alta")
    @Oneway
    public void alta(@WebParam(name="usuario") UsuarioEntity usuario){
        ejbUsuario.alta(usuario);
    }
    @WebMethod(operationName="eliminar")
    @Oneway
    public void eliminar(@WebParam(name="usuario") UsuarioEntity usuario){
        ejbUsuario.eliminar(usuario);
    }
    @WebMethod(operationName="modificar")
    @Oneway
    public void modificar(@WebParam(name="usuario") UsuarioEntity usuario){
        ejbUsuario.modificar(usuario);
    }
}
