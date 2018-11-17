/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AllRequestsServlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

/**
 *
 * @author Андрюха
 */
public class Main {

    public static void main(String[] args) throws Exception {

        // Чтение файла config.property
        Properties prop = new Properties();
        FileInputStream input = null;

        try {

            input = new FileInputStream("config.property");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("http.port"));
            System.out.println(prop.getProperty("tcp.dest.addr"));
            System.out.println(prop.getProperty("tcp.dest.port"));

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

        /*
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), "/*");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
         */
    }
}
