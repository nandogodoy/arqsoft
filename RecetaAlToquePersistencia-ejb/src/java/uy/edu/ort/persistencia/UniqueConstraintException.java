/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

/**
 *
 * @author Richard
 */
public class UniqueConstraintException extends Exception{
    
    UniqueConstraintException(String mensaje){
        super(mensaje);
    }
}
