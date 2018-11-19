/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlClasses;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Андрюха
 */
@XmlType(name = "Body")
@XmlRootElement(name = "Envelope")
@XmlAccessorType (XmlAccessType.FIELD)
public class Body {

    public Body() {
    }
    
    @XmlElement(name="sendPayment")
    public ArrayList<SendPayment> sendPayment = new ArrayList<>();

    public ArrayList<SendPayment> getSendPayment() {
        return sendPayment;
    }

    public void setSendPayment(ArrayList<SendPayment> sendPayment) {
        this.sendPayment = sendPayment;
    }
    
}
