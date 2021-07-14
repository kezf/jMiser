package org.miser.core.lang.generator;

import org.miser.core.util.IdUtil;

/**
 * UUID生成器
 *
 * @author Oliver
 * 
 */
public class UUIDGenerator implements Generator<String> {
	@Override
	public String next() {
		return IdUtil.fastUUID();
	}
}
