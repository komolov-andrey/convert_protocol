/**
 * Created by Андрюха on 19.11.2018.
 
@XmlSchema(
    namespace="wsapi:Payment",
    elementFormDefault=XmlNsForm.QUALIFIED),
    xmlns={@XmlNs(prefix="urn", namespaceURI="wsapi:Payment")})
*/
@XmlSchema(
    xmlns = { 
        @XmlNs(namespaceURI = "wsapi:Payment", prefix = "urn"),
        @XmlNs(namespaceURI = "wsapi:Utils", prefix = "uts")
    },
    elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)

package xmlClasses;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlSchema;

