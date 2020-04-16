/**
 * SyncServiceStationLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.infowoo.purchase.config;

public class SyncServiceStationLocator extends org.apache.axis.client.Service implements SyncServiceStation {

    public SyncServiceStationLocator() {
    }


    public SyncServiceStationLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SyncServiceStationLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for syncServiceStationPort
    private String syncServiceStationPort_address = "http://211.88.20.132:8040/services/syncServiceStation";

    @Override
    public String getsyncServiceStationPortAddress() {
        return syncServiceStationPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String syncServiceStationPortWSDDServiceName = "syncServiceStationPort";

    public String getsyncServiceStationPortWSDDServiceName() {
        return syncServiceStationPortWSDDServiceName;
    }

    public void setsyncServiceStationPortWSDDServiceName(String name) {
        syncServiceStationPortWSDDServiceName = name;
    }
    @Override
    public SyncServiceStationPortType getsyncServiceStationPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(syncServiceStationPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getsyncServiceStationPort(endpoint);
    }
    @Override
    public SyncServiceStationPortType getsyncServiceStationPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            SyncServiceStationBindingStub _stub = new SyncServiceStationBindingStub(portAddress, this);
            _stub.setPortName(getsyncServiceStationPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setsyncServiceStationPortEndpointAddress(String address) {
        syncServiceStationPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @Override
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (SyncServiceStationPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                SyncServiceStationBindingStub _stub = new SyncServiceStationBindingStub(new java.net.URL(syncServiceStationPort_address), this);
                _stub.setPortName(getsyncServiceStationPortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @Override
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("syncServiceStationPort".equals(inputPortName)) {
            return getsyncServiceStationPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }
    @Override
    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.cvicse.com/service/", "syncServiceStation");
    }

    private java.util.HashSet ports = null;
    @Override
    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.cvicse.com/service/", "syncServiceStationPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("syncServiceStationPort".equals(portName)) {
            setsyncServiceStationPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
