package com.example.demo.FileUploadToDb;


import com.example.demo.Response.StudentResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IStudent {

    String uploadImageToDB(String name, String department, MultipartFile multipartFile);

    StudentResponse downloadImageFromDB(Long id);
}
