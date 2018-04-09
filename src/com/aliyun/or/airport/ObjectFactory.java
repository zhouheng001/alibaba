
package com.aliyun.or.airport;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.aliyun.or.airport package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _QueryFlightData_QNAME = new QName("http://service.acdmwebservice.unionflight.com/", "queryFlightData");
    private final static QName _QueryFlightDataResponse_QNAME = new QName("http://service.acdmwebservice.unionflight.com/", "queryFlightDataResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.aliyun.or.airport
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryFlightDataResponse }
     * 
     */
    public QueryFlightDataResponse createQueryFlightDataResponse() {
        return new QueryFlightDataResponse();
    }

    /**
     * Create an instance of {@link QueryFlightData }
     * 
     */
    public QueryFlightData createQueryFlightData() {
        return new QueryFlightData();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryFlightData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.acdmwebservice.unionflight.com/", name = "queryFlightData")
    public JAXBElement<QueryFlightData> createQueryFlightData(QueryFlightData value) {
        return new JAXBElement<QueryFlightData>(_QueryFlightData_QNAME, QueryFlightData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryFlightDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.acdmwebservice.unionflight.com/", name = "queryFlightDataResponse")
    public JAXBElement<QueryFlightDataResponse> createQueryFlightDataResponse(QueryFlightDataResponse value) {
        return new JAXBElement<QueryFlightDataResponse>(_QueryFlightDataResponse_QNAME, QueryFlightDataResponse.class, null, value);
    }

}
