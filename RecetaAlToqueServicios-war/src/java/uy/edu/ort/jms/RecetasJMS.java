/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.jms;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import uy.edu.ort.dominio.Ingrediente;

import uy.edu.ort.dominio.Usuario;
import uy.edu.ort.dominio.Receta;
    

/**
 *
 * @author ASUS
 */
public class RecetasJMS {
    
    @Resource(lookup = "TRAConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(lookup = "jms/TRAQueue")
    private Queue queue;
    
    Connection connection;
    Session session;
    MessageProducer messageProducer;
    
    
    public RecetasJMS () {
	try {
	    Properties props = new Properties();
	    props.setProperty("java.naming.factory.initial",
		    "com.sun.enterprise.naming.SerialInitContextFactory");
	    props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
	    props.setProperty("java.naming.factory.state",
		    "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
	    props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
	    props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
	    //Creo el Contexto para obtener los recursos del servidor
	    InitialContext ic = new InitialContext(props);
	    // Obtenemos a traves del servicio JNDI la ConnectionFactory del servidor de aplicaciones
	    this.connectionFactory = (ConnectionFactory) ic.lookup("TRAConnectionFactory");
	    // Obtenemos a traves del servicio JNDI la "destination" que vamos a utilizar, en este caso una Queue
	    this.queue = (Queue) ic.lookup("jms/TRAQueue");
	    //Creo la Connection mediante la ConnectionFactory
	    this.connection = this.connectionFactory.createConnection();
	    //Creo la Session mediante la Connection
	    this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    //Creo la MessageProducer mediante la Session
	    this.messageProducer = this.session.createProducer(this.queue);
	} catch (NamingException ex) {
	    System.out.println("NamingException");
	    Logger.getLogger(RecetasJMS.class.getName()).log(Level.SEVERE, null, ex);
	} catch (JMSException ex) {
	    System.out.println("JMSException");
	    Logger.getLogger(RecetasJMS.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    
    public void altaReceta (Usuario usuario, Receta receta ) {
	try {
	    MapMessage mapMessage = this.session.createMapMessage();
	    mapMessage.setString("comando", "01");
	    mapMessage.setString("usuario", usuario.getNombre());
	    mapMessage.setString("nombre", receta.getNombre());
	    mapMessage.setString("procedimiento", receta.getProcedimiento());
	    
	    String ingredientes = "";
	    for (Ingrediente ing: receta.getIngredientes()) {
		ingredientes += ing.getNombre()+":";
	    }
            mapMessage.setString("ingredientes", ingredientes);

            messageProducer.send(mapMessage);
	} catch (JMSException ex) {
	    Logger.getLogger(RecetasJMS.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    
    public void valorarReceta (Receta receta, float valoracion) {
	try {
	    MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("comando", "02");
            mapMessage.setString("nombre", receta.getNombre());
            mapMessage.setFloat("valoracion", valoracion);
            messageProducer.send(mapMessage);
	} catch (JMSException ex) {
	    Logger.getLogger(RecetasJMS.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
}
