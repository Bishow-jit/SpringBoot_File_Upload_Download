package com.example.demo.FileUploadToFileSystem;


import com.example.demo.Auditing.BaseModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileData extends BaseModel {

    private String fileName;

    private String filePath;

    private String fileTye;


}
