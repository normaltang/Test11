package cn.test;

import java.util.HashMap;
import java.util.Map;

public class contains {
	public static void main(String[] args)
	{
	Map<String, String> paramMap=new HashMap<String, String>();
		paramMap.put("1", "b");
		paramMap.put("2", "b");
		paramMap.put("3", "ab");
		paramMap.put("4", "cc");                      
		System.out.println(paramMap.containsValue("b"));//返回true
		System.out.println(paramMap.containsValue("a"));//返回false
		System.out.println(paramMap.containsValue("cc"));//返回true
		System.out.println(paramMap.containsKey("1"));//返回true
		}
                                                 
}
