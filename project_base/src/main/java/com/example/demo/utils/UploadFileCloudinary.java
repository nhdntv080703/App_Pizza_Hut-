package com.example.demo.utils;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Component
public class UploadFileCloudinary {

    @Autowired
    private Cloudinary cloudinary;

    public String getUrlFromFile(MultipartFile multipartFile) throws Exception {
        try {
            Map<?, ?> mapFile = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
            //Todo : ObjectUtils.emptyMap : returns an empty 'java.util.Map' object
            //Todo : mapFile.get("secure_url") : is a key used in the metadata returned after uploading an image
            return mapFile.get("secure_url").toString();
        } catch (Exception exception) {
            throw new Exception("The process get url from file failed!");
        }
    }

    public String removeFileToUrl(String imageUrl) throws Exception {
        try {

            String publicId = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.lastIndexOf("."));
            //Todo : publicId : mã định danh duy nhất của file , đoạn code trên để trích xuất mã đấy

            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            //Todo : Xóa file trên cloudinary dựa trên mã đấy
            return "Remove file to url is successfully";
        } catch (Exception exception) {
            throw new Exception("Upload image failed");
        }
    }
}
