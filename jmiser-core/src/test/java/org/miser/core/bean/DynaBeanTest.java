package org.miser.core.bean;

import org.junit.Assert;
import org.junit.Test;

import lombok.Data;

/**
 * {@link DynamicBean}单元测试
 *
 * @author Oliver
 */
public class DynaBeanTest {

	@Test
	public void beanTest() {
		User user = new User();
		DynamicBean bean = DynamicBean.create(user);
		bean.set("name", "李华");
		bean.set("age", 12);

		String name = bean.get("name");
		Assert.assertEquals(user.getName(), name);
		int age = bean.get("age");
		Assert.assertEquals(user.getAge(), age);

		// 重复包装测试
		DynamicBean bean2 = new DynamicBean(bean);
		User user2 = bean2.getBean();
		Assert.assertEquals(user, user2);

		// 执行指定方法
		Object invoke = bean2.invoke("testMethod");
		Assert.assertEquals("test for 李华", invoke);
	}

	@Test
	public void beanByStaticClazzConstructorTest() {
		String name_before = "李华";
		int age_before = 12;
		DynamicBean bean = DynamicBean.create(User.class);
		bean.set("name", name_before);
		bean.set("age", age_before);

		String name_after = bean.get("name");
		Assert.assertEquals(name_before, name_after);
		int age_after = bean.get("age");
		Assert.assertEquals(age_before, age_after);

		// 重复包装测试
		DynamicBean bean2 = new DynamicBean(bean);
		User user2 = bean2.getBean();
		User user1 = bean.getBean();
		Assert.assertEquals(user1, user2);

		// 执行指定方法
		Object invoke = bean2.invoke("testMethod");
		Assert.assertEquals("test for 李华", invoke);
	}

	@Test
	public void beanByInstanceClazzConstructorTest() {
		String name_before = "李华";
		int age_before = 12;
		DynamicBean bean = new DynamicBean(User.class);
		bean.set("name", name_before);
		bean.set("age", age_before);

		String name_after = bean.get("name");
		Assert.assertEquals(name_before, name_after);
		int age_after = bean.get("age");
		Assert.assertEquals(age_before, age_after);

		// 重复包装测试
		DynamicBean bean2 = new DynamicBean(bean);
		User user2 = bean2.getBean();
		User user1 = bean.getBean();
		Assert.assertEquals(user1, user2);

		// 执行指定方法
		Object invoke = bean2.invoke("testMethod");
		Assert.assertEquals("test for 李华", invoke);
	}

	@Data
	public static class User {
		private String name;
		private int age;

		public String testMethod() {
			return "test for " + this.name;
		}
	}
}
