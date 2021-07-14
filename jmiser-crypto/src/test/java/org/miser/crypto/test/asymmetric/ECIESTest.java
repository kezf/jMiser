package org.miser.crypto.test.asymmetric;

import org.miser.core.util.StringUtil;
import org.miser.crypto.asymmetric.AsymmetricCrypto;
import org.miser.crypto.asymmetric.ECIES;
import org.miser.crypto.asymmetric.KeyType;
import org.junit.Assert;
import org.junit.Test;

public class ECIESTest {

	@Test
	public void eciesTest(){
		final ECIES ecies = new ECIES();

		doTest(ecies, ecies);
	}

	@Test
	public void eciesTest2(){
		final ECIES ecies = new ECIES();
		final byte[] privateKeyBytes = ecies.getPrivateKey().getEncoded();
		final ECIES ecies2 = new ECIES(privateKeyBytes, null);

		doTest(ecies, ecies2);
	}

	/**
	 * 测试用例
	 *
	 * @param cryptoForEncrypt 加密的Crypto
	 * @param cryptoForDecrypt 解密的Crypto
	 */
	private void doTest(AsymmetricCrypto cryptoForEncrypt, AsymmetricCrypto cryptoForDecrypt){
		String textBase = "我是一段特别长的测试";
		StringBuilder text = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			text.append(textBase);
		}

		// 公钥加密，私钥解密
		String encryptStr = cryptoForEncrypt.encryptBase64(text.toString(), KeyType.PublicKey);

		String decryptStr = StringUtil.utf8Str(cryptoForDecrypt.decrypt(encryptStr, KeyType.PrivateKey));
		Assert.assertEquals(text.toString(), decryptStr);
	}
}
