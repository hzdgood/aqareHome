package com.aqara.common.utils;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

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

    public static String uploadFileToWeixin(String filePath, String requestUrl, String WX_TOKEN, String type) {
        File files = new File(filePath);
        if (!files.exists()) {
            return "";
        }
        String req = requestUrl + "?access_token=" + WX_TOKEN + "&type=" + type;

        return "";
    }
}
