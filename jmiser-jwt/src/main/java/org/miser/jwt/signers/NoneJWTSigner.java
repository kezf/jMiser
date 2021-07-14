package org.miser.jwt.signers;

import org.miser.core.util.StringUtil;

/**
 * 无需签名的JWT签名器
 *
 * @author Oliver
 * 
 */
public class NoneJWTSigner implements JWTSigner {

	public static final String ID_NONE = "none";

	public static NoneJWTSigner NONE = new NoneJWTSigner();

	@Override
	public String sign(String headerBase64, String payloadBase64) {
		return StringUtil.EMPTY;
	}

	@Override
	public boolean verify(String headerBase64, String payloadBase64, String signBase64) {
		return StringUtil.isEmpty(signBase64);
	}

	@Override
	public String getAlgorithm() {
		return ID_NONE;
	}
}
