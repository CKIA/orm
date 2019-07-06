package com.ckia.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrmMybatisApplicationTests {

	private String key = "000";

	@Test
	public void contextLoads() {

		Ormb ormb = new OrmMybatisApplicationTests().new Ormb();
		ormb.test();
	}

	private String str(){
		return "rere";
	}

	public class Ormb {

		public void test(){
			System.out.println(OrmMybatisApplicationTests.this.key);
			System.out.println(OrmMybatisApplicationTests.this.str());
		}
	}
}
