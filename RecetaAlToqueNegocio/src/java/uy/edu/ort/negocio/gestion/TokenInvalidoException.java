/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.negocio.gestion;

import javax.ejb.ApplicationException;

/**
 *
 * @author ASUS
 */
@ApplicationException(rollback=true) 
public class TokenInvalidoException extends Exception {

    TokenInvalidoException(String el_token_expiro) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
