package org.miser.core.net;

import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 默认信任管理器，默认信任所有客户端和服务端证书
 *
 * @author Oliver
 * 
 */
public class DefaultTrustManager implements X509TrustManager {

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType) {
	}

	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType) {
	}
}
