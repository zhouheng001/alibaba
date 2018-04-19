
package com.aliyun.or.airport;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "QueryDataServiceService", targetNamespace = "http://service.acdmwebservice.unionflight.com/", wsdlLocation = "http://10.40.1.79/acdmwebservice/services/queryDataService?wsdl")
public class QueryDataServiceService
    extends Service
{

    private final static URL QUERYDATASERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException QUERYDATASERVICESERVICE_EXCEPTION;
    private final static QName QUERYDATASERVICESERVICE_QNAME = new QName("http://service.acdmwebservice.unionflight.com/", "QueryDataServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://10.40.1.79/acdmwebservice/services/queryDataService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        QUERYDATASERVICESERVICE_WSDL_LOCATION = url;
        QUERYDATASERVICESERVICE_EXCEPTION = e;
    }

    public QueryDataServiceService() {
        super(__getWsdlLocation(), QUERYDATASERVICESERVICE_QNAME);
    }

    public QueryDataServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), QUERYDATASERVICESERVICE_QNAME, features);
    }

    public QueryDataServiceService(URL wsdlLocation) {
        super(wsdlLocation, QUERYDATASERVICESERVICE_QNAME);
    }

    public QueryDataServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, QUERYDATASERVICESERVICE_QNAME, features);
    }

    public QueryDataServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public QueryDataServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns QueryDataService
     */
    @WebEndpoint(name = "QueryDataServicePort")
    public QueryDataService getQueryDataServicePort() {
        return super.getPort(new QName("http://service.acdmwebservice.unionflight.com/", "QueryDataServicePort"), QueryDataService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns QueryDataService
     */
    @WebEndpoint(name = "QueryDataServicePort")
    public QueryDataService getQueryDataServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.acdmwebservice.unionflight.com/", "QueryDataServicePort"), QueryDataService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (QUERYDATASERVICESERVICE_EXCEPTION!= null) {
            throw QUERYDATASERVICESERVICE_EXCEPTION;
        }
        return QUERYDATASERVICESERVICE_WSDL_LOCATION;
    }

}
