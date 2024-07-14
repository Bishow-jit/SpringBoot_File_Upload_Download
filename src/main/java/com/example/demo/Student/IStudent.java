package com.example.demo.Student;


import com.example.demo.Response.StudentResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IStudent {


    Student getImageNameById(Long id);

    String uploadImageToDB(String name, String department, MultipartFile multipartFile);

    StudentResponse downloadImageFromDB(Long id);
}
