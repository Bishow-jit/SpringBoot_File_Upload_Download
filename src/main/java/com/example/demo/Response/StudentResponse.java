package com.example.demo.Response;

import com.example.demo.FileUploadToDb.Student;
import lombok.Data;

@Data
public class StudentResponse {
    private Student student;

    private byte[] imageData;
}
