package com.aqara.common.utils;

import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.Base64;

public class FileUtil {
    public static String fileSave(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        try {
            File files = new File(path, fileName);
            file.transferTo(files);
            return path + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(buffer);
    }

    /*
     *actions: 将base64字符保存文本文件
     *targetPath：文件路径
     *base64Code: base64字符串
     */
    public void toFile(String base64Code, String targetPath) throws Exception {
        byte[] buffer = base64Code.getBytes();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }
}
