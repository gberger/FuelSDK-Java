package com.exacttarget.fuelsdk;

import org.apache.cxf.transports.http.configuration.ProxyServerType;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Created by nihardongara on 10/26/16.
 */
public class ETProxy {
    Proxy restProxy;
    ProxyServerType proxyServerType;
    String proxyServer;
    Integer proxyServerPort;


    /**
     * Creates the ETProxy which will be used by Soap connection as well as rest connection both.
     * @param proxyType - accepts only (HTTP and SOCKS)
     * @param proxyServer -  url for proxy e.g: proxy.example.com
     * @param proxyServerPort - port for the proxy server e.g: 3143
     */
    public ETProxy(String proxyType, String proxyServer, Integer proxyServerPort) {
        this.restProxy = new Proxy(Proxy.Type.valueOf(proxyType), new InetSocketAddress( proxyServer,proxyServerPort));
        this.proxyServerType = ProxyServerType.valueOf(proxyType);
        this.proxyServer = proxyServer;
        this.proxyServerPort = proxyServerPort;
    }

    public Proxy getRestProxy() {
        return restProxy;
    }

    public ProxyServerType getProxyServerType() {
        return proxyServerType;
    }

    public String getProxyServer() {
        return proxyServer;
    }

    public Integer getProxyServerPort() {
        return proxyServerPort;
    }
}