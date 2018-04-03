package com.atguigu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyPropertyUtil {

	public static String getProperty(String pro, String key) {
		// 创建Properties工具类util包下的
		Properties properties = new Properties();
		// 调用其load()方法加载MyLoad.properties文件
		// 获取MyUpload.properties配置文件的输入流
		InputStream resourceAsStream = MyPropertyUtil.class.getClassLoader().getResourceAsStream(pro);
		try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 读取配置文件，根据键获取值
		String property = properties.getProperty(key);
		return property;
	}

}
