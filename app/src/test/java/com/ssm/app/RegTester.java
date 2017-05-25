package com.ssm.app;
import java.util.regex.Matcher;
import java.util.regex.Pattern;   
public class RegTester {  
	   
    public static void main(String[] args) {  
    	String s = "se052tsysdictitem20170320001";
    	// 把要匹配的字符串写成正则表达式，然后要提取的字符使用括号括起来
    	// 在这里，我们要提取最后一个数字，正则规则就是“一个数字加上大于等于0个非数字再加上结束符”
    	Pattern pattern = Pattern.compile("[\\d]+([^\\d]+)[\\d]+");
    	Matcher matcher = pattern.matcher(s);
    	if(matcher.find())
    	System.out.println(matcher.group(1));
    }  
}  
