package com.yunqi.common.utils;

import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.Random;
import java.util.UUID;
public class MultipartFileUtils {
    public static String saveMultipartFile(MultipartFile file, String targetDirPath) {
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            return null;
        } else {
            /*获取文件原名称*/
            String originalFilename = file.getOriginalFilename();
            /*获取文件格式*/
            String fileFormat = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
            toFile = new File(targetDirPath + File.separator + originalFilename);
            String absolutePath = null;
            try {
                absolutePath = toFile.getCanonicalPath();
                /*判断路径中的文件夹是否存在，如果不存在，先创建文件夹*/
                String dirPath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
                File dir = new File(dirPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                InputStream ins = file.getInputStream();
                inputStreamToFile(ins, toFile);
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return absolutePath;
        }
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
