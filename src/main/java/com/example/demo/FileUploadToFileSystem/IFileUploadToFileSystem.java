package com.example.demo.FileUploadToFileSystem;

import com.example.demo.Response.FileDataResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileUploadToFileSystem {

    String uploadToFileSystem(MultipartFile multipartFile) throws IOException;

    FileDataResponse downloadFromFileSystem(String fileName) throws IOException;

}
