package org.miser.http.ssl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * 默认的SSLSocketFactory
 *
 * @author Oliver
 * 
 */
public class DefaultSSLFactory extends CustomProtocolsSSLFactory {

	public DefaultSSLFactory() throws KeyManagementException, NoSuchAlgorithmException {
	}

}