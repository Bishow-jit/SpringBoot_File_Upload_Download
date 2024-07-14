package com.example.demo.FileUploadToDb;

import com.example.demo.Auditing.BaseModel;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student_tbl")
@Data
public class Student extends BaseModel {


    @Column(name = "student_name")
    private String name;

    @Column(name = "student_department")
    private String department;

    @Column(name = "image_data",columnDefinition = "MEDIUMTEXT")
    private String imageData;

    @Column(name = "student_image_name")
    private String imageName;

    @Column(name = "image_type")
    private String imageType;


}
