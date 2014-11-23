package uy.edu.ort.productor.mensajes;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Richard
 */
public class SDEProductor {

    static final Logger logger = Logger.getLogger("SimpleMessageClient");

    @Resource(lookup = "TRAConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/TRAQueue")
    private static Queue queue;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
         String text;
         MapMessage mapeo
         try {
         JMSContext context = connectionFactory.createContext();
         for (int i = 0; i < 3; i++) {
         text = "This is message " + (i + 1);
         System.out.println("Sending message: " + text);
         context.createProducer().send(queue, text);
         }

         System.out.println("To see if the bean received the messages,");
         System.out.println(
         " check <install_dir>/domains/domain1/logs/server.log.");*/
        try {

 //Seteo las Properties para el contexto 
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
// Obtenemos a traves del servicio JNDI la ConnectionFactory del 
// servidor de aplicaciones 
            ConnectionFactory connectionFactory = (ConnectionFactory) ic.lookup("TRAConnectionFactory");
// Obtenemos a traves del servicio JNDI la "destination" que vamos 
// a utilizar, en este caso una Queue 
            Queue queue = (Queue) ic.lookup("jms/TRAQueue");
//Creo la Connection mediante la ConnectionFactory 
            Connection connection = connectionFactory.createConnection();
//Creo la Session mediante la Connection 
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
 //Creo la MessageProducer mediante la Session 
            MessageProducer messageProducer = session.createProducer(queue);
//Creo la TextMessage mediante la Session 
           MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("usuario", "mrodriguez");
            mapMessage.setString("nombre", "torta fritas");
            String procedimiento="Mezclar la harina con la grasa y la sal. Añadir la cantidad suficiente de líquido para formar una masa blanda. Dejar descansar la masa una media hora.\n" +
"\n" +"Espolvorear harina sobre la mesa de trabajo, y otro poco en el palo de amasar -si se utiliza- para que no se pegue la masa mientras se estira. Amasar, golpear y trabajar mucho la masa.\n" +
"\n" +"Estirar luego, dejándola de medio centímetro de espesor. Cortar con cuchillo en forma de cuadrados, triángulos o círculos.\n" +
"Formar las tortas y hacer un agujerito en el centro.\n" +
"\n" +"Poner en aceite o grasa muy caliente aunque se queme la primera. Deben quedar algo doradas y muy tiernas, nunca crocantes ni rígidas. Al darlas vuelta, hacerlo con cuidado para que no salte aceite o grasa, y nunca usar utensilios húmedos.\n" +
"\n" +"Sacar las tortas con cuidado, dejar escurrir el aceite unos instantes y finalmente dejarlas sobre papel de cocina. ";
            mapMessage.setString("procedimiento", procedimiento);
            mapMessage.setString("nombre", "torta fritas");
            String ingredientes = "harina:grasa:sal:agua";
            mapMessage.setString("ingredientes", ingredientes);
            mapMessage.setString("comando","01");
//Envío el mensaje mediante MessageProducer 
            messageProducer.send(mapMessage);
           
            MapMessage mapMessage2 = session.createMapMessage();
            mapMessage2.setString("comando", "02");
            mapMessage2.setString("nombre", "torta fritas");
            mapMessage2.setFloat("valoracion", 4);
            messageProducer.send(mapMessage2);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred: {0}", e.toString());
        }
    }

}
