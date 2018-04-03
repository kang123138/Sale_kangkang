package com.atguigu.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MyFileUpload {

	public static List<String> upload_image(MultipartFile[] files) {
		// 建工具类读取properties配置文件-读取myUpload.properties配置文件中的windows_path路径
		String path = MyPropertyUtil.getProperty("myUpload.properties", "windows_path");
		// 最终需要返回一个List集合
		List<String> list_image = new ArrayList<String>();
		// 遍历所有图片
		for (int i = 0; i < files.length; i++) {
			// 如果文件数组不为空
			if (!files[i].isEmpty()) {
				// 获取每张图片的原始文件名,是带有后缀的
				String originalFilename = files[i].getOriginalFilename();
				// 得到真实的不会重复的文件名,即路径+UUID/currentTimeMillis
				/*
				 * String uuidNoXiexian = UUID.randomUUID().toString().replace("-", ""); String
				 * name = uuidNoXiexian + originalFilename;
				 */
				String name = System.currentTimeMillis() + originalFilename;
				String upload_name = path + "/" + name;

				// 把文件上传上去
				try {
					files[i].transferTo(new File(upload_name));
					// 在每次上传成功后，把文件名添加到集合中-是没有路径的name，不是upload_name;
					list_image.add(name);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}

		}
		return list_image;
	}

}
