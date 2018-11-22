/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import main.Main;
import org.apache.log4j.Logger;
import templater.PageGenerator;
import xmlClasses.Account;
import xmlClasses.Body;
import xmlClasses.Envelope;
import xmlClasses.Field;
import xmlClasses.SendPayment;

/**
 *
 * @author Андрюха
 */
public class XmlParserServlet extends HttpServlet {

    // Инициализация логера
    private static final Logger log = Logger.getLogger(XmlParserServlet.class);

    private static String json = null;
    private static Map<String, Object> pageVariables = new HashMap<>();

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // инициализация переменных в карте
        pageVariables.put("message", "Enter xml and push button - parse");
        pageVariables.put("sendInfo", "");
        pageVariables.put("xmlField", "");

        pageVariables.put("token", "");
        pageVariables.put("cardNumber", "");
        pageVariables.put("amount", "");
        pageVariables.put("currency", "");
        pageVariables.put("source", "");
        pageVariables.put("destination", "");

        response.getWriter().println(PageGenerator.instance().getPage("index.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, JsonProcessingException {

        // получение компонентов
        String xml = request.getParameter("xml");
        String send = request.getParameter("send");

        // если нажали кнопку parse
        if (send == null) {
            try {
                // parsing xml to json
                json = papse(xml);

                pageVariables.put("message", "Your json - " + json);
                pageVariables.put("xmlField", xml);
                log.info("xml распарсен");
            } catch (JAXBException ex) {
                log.error("Не удалось распарсить xml");
                pageVariables.put("message", "Failed to parse xml");
            }
        } else {
            // если нажали кнопку send
            try {
                LinkedHashMap<String, String> map = new LinkedHashMap<>();
                map.put("description", "Json data");
                map.put("length", json.length() + "");
                map.put("byte", json.getBytes(StandardCharsets.UTF_16LE).toString());
                
                log.info("Будут отправлены данные: " + javax.xml.bind.DatatypeConverter.printHexBinary(map.toString().getBytes()));
                
                Socket socket = new Socket(Main.getTcpDestAddr(), Integer.parseInt(Main.getTcpDestPort()));

                ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));;
                out.writeObject(map);
                out.flush();
                
                pageVariables.put("sendInfo", "Data send");

            } catch (Exception e) {
                log.error("Не удалось отправить данные");
                pageVariables.put("sendInfo", "Data has not been sent");
            }
        }

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        response.getWriter().println(PageGenerator.instance().getPage("index.html", pageVariables));
    }

    public String papse(String xmldata) throws JAXBException, JsonProcessingException {

        StringReader reader = new StringReader(xmldata);

        JAXBContext contextJaxb = JAXBContext.newInstance(Field.class, Account.class, SendPayment.class, Body.class, Envelope.class);
        Unmarshaller unmarshaller = contextJaxb.createUnmarshaller();

        Envelope envelope = (Envelope) unmarshaller.unmarshal(reader);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);

        String json = mapper.writeValueAsString(envelope);

        // получение отдельных полей
        try {
            pageVariables.put("token", "token is " + envelope.getBody().get(0).getSendPayment().get(0).getToken());
        } catch (IndexOutOfBoundsException e) {
            pageVariables.put("token", "token is default");
        }
        try {
            pageVariables.put("cardNumber", "cardNumber is " + envelope.getBody().get(0).getSendPayment().get(0).getCardNumber());
        } catch (IndexOutOfBoundsException e) {
            pageVariables.put("cardNumber", "cardNumber is default");
        }
        try {
            pageVariables.put("amount", "amount is " + envelope.getBody().get(0).getSendPayment().get(0).getAmount());
        } catch (IndexOutOfBoundsException e) {
            pageVariables.put("amount", "amount is default");
        }
        try {
            pageVariables.put("currency", "currency is " + envelope.getBody().get(0).getSendPayment().get(0).getCurrency());
        } catch (IndexOutOfBoundsException e) {
            pageVariables.put("currency", "currency is default");
        }
        try {
            pageVariables.put("source", "source is " + envelope.getBody().get(0).getSendPayment().get(0).getAccount().get(0).getAcc());//source
        } catch (IndexOutOfBoundsException e) {
            pageVariables.put("source", "source is default");
        }
        try {
            pageVariables.put("destination", "destination is " + envelope.getBody().get(0).getSendPayment().get(0).getAccount().get(1).getAcc());//destination   
        } catch (IndexOutOfBoundsException e) {
            pageVariables.put("destination", "destination is default");
        }

        return json;

    }

}
