package org.miser.json;

import org.junit.Assert;
import org.junit.Test;
import org.miser.core.bean.BeanUtil;

import lombok.Data;

public class IssueI3EGJP {

	@Test
	public void testMapToBean() {
		JSONObject paramJson = new JSONObject();
		paramJson.set("is_booleana", "1");
		paramJson.set("is_booleanb", true);
		ConvertDO convertDO = BeanUtil.toBean(paramJson, ConvertDO.class);

		Assert.assertTrue(convertDO.isBooleana());
		Assert.assertTrue(convertDO.getIsBooleanb());
	}

	@Data
	public static class ConvertDO {
		private boolean isBooleana;
		private Boolean isBooleanb;
	}
}
