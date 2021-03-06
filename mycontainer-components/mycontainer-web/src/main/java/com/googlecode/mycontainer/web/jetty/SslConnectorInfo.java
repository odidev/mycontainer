package com.googlecode.mycontainer.web.jetty;

import java.io.Serializable;
import java.security.KeyStore;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;

public class SslConnectorInfo implements Serializable {

	private static final long serialVersionUID = -1104209736781892413L;

	private int port = 8443;

	private boolean wantClientAuth = false;

	private boolean needClientAuth = false;

	private String keyStore;

	private String keyStorePassword;

	private String keyManagerPassword;

	private String certAlias;

	private KeyStore trustStore;

	private String trustStorePassword;

	private int maxIdleTime = 30000;

	public SslConnectorInfo() {

	}

	public SslConnectorInfo(String keyStore) {
		this.keyStore = keyStore;
	}

	public SslConnectorInfo(int port, String keyStore) {
		this.port = port;
		this.keyStore = keyStore;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isWantClientAuth() {
		return wantClientAuth;
	}

	public void setWantClientAuth(boolean wantClientAuth) {
		this.wantClientAuth = wantClientAuth;
	}

	public boolean isNeedClientAuth() {
		return needClientAuth;
	}

	public void setNeedClientAuth(boolean needClientAuth) {
		this.needClientAuth = needClientAuth;
	}

	public String getKeyStore() {
		return keyStore;
	}

	public void setKeyStore(String keyStore) {
		this.keyStore = keyStore;
	}

	public String getKeyStorePassword() {
		return keyStorePassword;
	}

	public void setKeyStorePassword(String keyStorePassword) {
		this.keyStorePassword = keyStorePassword;
	}

	public String getKeyManagerPassword() {
		return keyManagerPassword;
	}

	public void setKeyManagerPassword(String keyManagerPassword) {
		this.keyManagerPassword = keyManagerPassword;
	}

	public String getCertAlias() {
		return certAlias;
	}

	public void setCertAlias(String certAlias) {
		this.certAlias = certAlias;
	}

	public KeyStore getTrustStore() {
		return trustStore;
	}

	public void setTrustStore(KeyStore trustStore) {
		this.trustStore = trustStore;
	}

	public String getTrustStorePassword() {
		return trustStorePassword;
	}

	public void setTrustStorePassword(String trustStorePassword) {
		this.trustStorePassword = trustStorePassword;
	}

	public int getMaxIdleTime() {
		return maxIdleTime;
	}

	public void setMaxIdleTime(int maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
	}

	public Connector createConnector(Server server) {

		SslContextFactory sslContextFactory = new SslContextFactory(keyStore);

		sslContextFactory.setKeyStorePassword(keyStorePassword);
		sslContextFactory.setKeyManagerPassword(keyManagerPassword);
		sslContextFactory.setCertAlias(certAlias);

		sslContextFactory.setNeedClientAuth(needClientAuth);
		sslContextFactory.setWantClientAuth(wantClientAuth);

		sslContextFactory.setTrustStore(trustStore);
		sslContextFactory.setTrustStorePassword(trustStorePassword);

		ServerConnector connector = new ServerConnector(server, sslContextFactory);
		connector.setPort(port);
		connector.setIdleTimeout(maxIdleTime);

		return connector;
	}
}
