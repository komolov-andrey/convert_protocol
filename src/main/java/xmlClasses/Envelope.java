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
import javax.xml.bind.annotation.*;

/**
 *
 * @author Андрюха
 */
@XmlType(name = "Envelope")
@XmlRootElement(name = "Envelope")
@XmlAccessorType (XmlAccessType.FIELD)
public class Envelope {

    public Envelope() {
    }
    
    @XmlElement(name="Body")
    public ArrayList<Body> body = new ArrayList<>();

    public ArrayList<Body> getBody() {
        return body;
    }

    public void setBody(ArrayList<Body> body) {
        this.body = body;
    }
    
}
