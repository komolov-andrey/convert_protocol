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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
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

    // Инициализация логера
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        //init log configuration
        BasicConfigurator.configure();

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

        //Marshaller marshaller = context.createMarshaller();
        //marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //marshaller.marshal(envelope, System.out);
    
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(envelope);
        
        //System.out.println(json);
        //log.warn("Test");

        //Socket socket = new Socket("127.0.0.1", 1234);

        LinkedHashMap <String, String> map = new LinkedHashMap<>();
        map.put("description", "Json data");
        map.put("length", json.length()+"");
        map.put("byte", json.getBytes(StandardCharsets.UTF_16LE).toString());
        
        //ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        //ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));;

        //out.writeObject(map);
        //out.flush();
        
        
        //System.out.println(javax.xml.bind.DatatypeConverter.printHexBinary(map.toString().getBytes()));
    }

}
