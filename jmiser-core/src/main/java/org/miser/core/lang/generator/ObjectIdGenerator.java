package org.miser.core.lang.generator;

import org.miser.core.lang.ObjectId;

/**
 * ObjectId生成器
 *
 * @author Oliver
 * 
 */
public class ObjectIdGenerator implements Generator<String> {
	@Override
	public String next() {
		return ObjectId.next();
	}
}
