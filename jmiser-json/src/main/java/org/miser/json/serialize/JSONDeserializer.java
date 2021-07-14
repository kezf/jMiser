package org.miser.json.serialize;

import org.miser.json.JSON;

/**
 * JSON反序列话自定义实现类
 * 
 * @author Oliver
 *
 * @param <T> 反序列化后的类型
 */
@FunctionalInterface
public interface JSONDeserializer<T> {
	
	/**
	 * 反序列化，通过实现此方法，自定义实现JSON转换为指定类型的逻辑
	 * 
	 * @param json {@link JSON}
	 * @return 目标对象
	 */
	T deserialize(JSON json);
}
