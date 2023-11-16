package com.yunqi.common.controller;

import com.yunqi.common.entity.Upload;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URLEncoder;

@RestController
@RequestMapping("/file")
public class FileController {

//    @CrossOrigin
//    @RequestMapping("/download11")
//    public void download(String path, HttpServletResponse response) {
//        try {
//            File file = new File(path);
//            String filename = file.getName();
//            String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
//            FileInputStream fileInputStream = new FileInputStream(file);
//            InputStream fis = new BufferedInputStream(fileInputStream);
//            byte[] buffer = new byte[fis.available()];
//            fis.read(buffer);
//            fis.close();
//            response.reset();
//            response.setCharacterEncoding("UTF-8");
//            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
//            response.addHeader("Content-Length", "" + file.length());
//            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
//            response.setContentType("application/octet-stream");
//            outputStream.write(buffer);
//            outputStream.flush();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
    @CrossOrigin
    @RequestMapping("/download")
    public void downloadLocal(Upload Upload, HttpServletResponse response) throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(Upload.getPath()));
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpg");
            int len = 0;
            byte[] bytes = new byte[1024];// 使用一个字节数组存容量为1024作为一组数据
            while((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
            fileInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
