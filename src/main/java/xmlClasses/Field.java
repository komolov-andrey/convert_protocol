/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlClasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Андрюха
 */
@XmlType(name = "field")
@XmlRootElement(name = "sendPayment")
@XmlAccessorType (XmlAccessType.FIELD)
public class Field {

    @XmlAttribute(name = "id")
    public String id;
    
    @XmlAttribute(name = "value")
    public String value;

    public Field() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    

}
