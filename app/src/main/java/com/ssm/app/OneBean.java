package com.ssm.app;
import org.springframework.stereotype.Component;

/**
 * 第一个任务执行Bean
 * @author ZhangPengFei
 * @Discription
 * @Data 2017-2-25
 * @Version 1.0.0
 */
@Component
public class OneBean {
	public void outMessage(){
		
		
		System.out.println("hello 我是第一个任务执行类");
	}
}