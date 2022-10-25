package com.aqara.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static String fileSave(MultipartFile file, String path) {
		String fileName = file.getOriginalFilename();
		try {
			File files = new File(path, fileName);
			OutputStream out = new FileOutputStream(files);
			InputStream inputStream = file.getInputStream();
			byte[] buff = new byte[1024 * 10];
			if (!files.exists()) {
				int len;
				while ((len = inputStream.read(buff)) != -1) {
					out.write(buff, 0, len);
					out.flush();
				}
				out.close();
				inputStream.close();
			}
			return path + fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static String uploadFileToWeixin(String filePath, String requestUrl, String WX_TOKEN, String type) {
		File files = new File(filePath);
		if (!files.exists()) {
			return "";
		}
		String req = requestUrl + "?access_token=" + WX_TOKEN + "&type=" + type;
		
		return "";
	}
}
