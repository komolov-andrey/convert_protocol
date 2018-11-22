/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import http.XmlParserServlet;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Андрюха
 */
public class Main {
    
    // переменные настроек
    private static String httpPort=null;
    private static String tcpDestAddr=null;
    private static String tcpDestPort=null;

    public static String getHttpPort() {
        return httpPort;
    }

    public static String getTcpDestAddr() {
        return tcpDestAddr;
    }

    public static String getTcpDestPort() {
        return tcpDestPort;
    }

    // Инициализация логера
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        
        // инициализация логгера
        BasicConfigurator.configure();
            
        // Чтение файла настроек config.property
        Properties prop = new Properties();

        try (FileInputStream input = new FileInputStream("config.property")) {
            // load a properties file
            prop.load(input);
            // get the property value
            httpPort = prop.getProperty("http.port");
            tcpDestAddr = prop.getProperty("tcp.dest.addr");
            tcpDestPort = prop.getProperty("tcp.dest.port");
            
        } catch (IOException ex) {
            log.error("Ошибка чтения файла настроек");
        }

        // Запускаем Jetty Сервер
        XmlParserServlet xmlParserServlet = new XmlParserServlet();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(xmlParserServlet), "/*");
        Server server = new Server(Integer.parseInt(getHttpPort()));
        server.setHandler(context);
        server.start();
        server.join();
    }

}
