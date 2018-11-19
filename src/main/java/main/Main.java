/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import http.XmlParserServlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import xmlClasses.Account;
import xmlClasses.Body;
import xmlClasses.Envelope;
import xmlClasses.Field;
import xmlClasses.SendPayment;

/**
 *
 * @author Андрюха
 */
public class Main {

    public static void main(String[] args) throws Exception {

        /*
        String httpPort=null;
        String tcpDestAddr;
        String tcpDestPort;
            
        // Чтение файла config.property
        Properties prop = new Properties();
        FileInputStream input = null;

        try {
            input = new FileInputStream("config.property");

            // load a properties file
            prop.load(input);

            // get the property value
            httpPort = prop.getProperty("http.port");
            tcpDestAddr = prop.getProperty("tcp.dest.addr");
            tcpDestPort = prop.getProperty("tcp.dest.port");
            

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        
        XmlParserServlet xmlParserServlet = new XmlParserServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(xmlParserServlet), "/*");

        Server server = new Server(Integer.parseInt(httpPort));
        server.setHandler(context);

        server.start();
        server.join();
        
         */
        String xmldata = "<Envelope xmlns:urn=\"wsapi:Payment\" xmlns:uts=\"wsapi:Utils\">\n"
                + "<Body>\n"
                + "<urn:sendPayment>\n"
                + "<token>001234</token>\n"
                + "<cardNumber>811626834823422</cardNumber>\n"
                + "<requestId>2255086658</requestId>\n"
                + "<amount>100000.00</amount>\n"
                + "<currency>RUB</currency>\n"
                + "<uts:account type=\"source\">009037269229</uts:account>\n"
                + "<uts:account type=\"destination\">088127269229</uts:account>\n"
                + "<page>1</page>\n"
                + "<field id=\"0\" value=\"0800\" />\n"
                + "<field id=\"11\" value=\"000001\" />\n"
                + "<field id=\"70\" value=\"301\" />\n"
                + "</urn:sendPayment>\n"
                + "</Body>\n"
                + "</Envelope>";

        StringReader reader = new StringReader(xmldata);

        JAXBContext context = JAXBContext.newInstance(Field.class, Account.class, SendPayment.class, Body.class, Envelope.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Envelope envelope = (Envelope) unmarshaller.unmarshal(reader);
        //Account account = (Account) unmarshaller.unmarshal(reader);
        //Field field = (Field) unmarshaller.unmarshal(reader);

        //System.out.println(sendPayment.field.get(0).value);
        //System.out.println(field);
        //System.out.println(sendPayment.account);
        //------------------------------------------
        //Marshaller marshaller = context.createMarshaller();
        //marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //Marshal the employees list in console
        //marshaller.marshal(envelope, System.out);
        
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(envelope));
    }

}
