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
@XmlType(name = "sendPayment")
@XmlRootElement(name = "Body")
@XmlAccessorType (XmlAccessType.FIELD)
public class SendPayment {
    
    @XmlElement(name = "token")
    private String token;
    
    @XmlElement(name = "cardNumber")
    private String cardNumber;
    
    @XmlElement(name = "requestId")
    private String requestId;
    
    @XmlElement(name = "amount")
    private String amount;
    
    @XmlElement(name = "currency")
    private String currency;
    
    @XmlElement(name="account", namespace = "wsapi:Utils")
    private ArrayList<Account> account = new ArrayList<>();
    
    @XmlElement(name = "page")
    private String page;
    
    @XmlElement(name="field")
    private ArrayList<Field> field = new ArrayList<>();

    public SendPayment() {
    }    

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ArrayList<Account> getAccount() {
        return account;
    }

    public void setAccount(ArrayList<Account> account) {
        this.account = account;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public ArrayList<Field> getField() {
        return field;
    }

    public void setField(ArrayList<Field> field) {
        this.field = field;
    }
    
}
