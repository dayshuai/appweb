package com.ssm.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssm.bean.Birth;
import com.ssm.mapper.BirthDAO;

public class MyBatisTest {
	private static ApplicationContext applicationContext;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		applicationContext = new ClassPathXmlApplicationContext("applicationContext-dao.xml");
		BirthDAO birthDAO = (BirthDAO) applicationContext.getBean("birthDAO");
		Birth birth;
		try {
			birth = birthDAO.selectById(1L);
			System.out.println(birth.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
