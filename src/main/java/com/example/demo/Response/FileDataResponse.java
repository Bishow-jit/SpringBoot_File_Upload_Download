package com.example.demo.Response;

import com.example.demo.FileUploadToFileSystem.FileData;
import lombok.Data;

@Data
public class FileDataResponse {
    private FileData fileData;
    private  byte [] fileByte;
}
