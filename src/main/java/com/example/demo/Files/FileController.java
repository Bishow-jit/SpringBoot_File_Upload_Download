package com.example.demo.Files;


import com.example.demo.Response.FileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v1")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    String path;

    @PostMapping(value = "/uploadFile")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = null;
        try {
            fileName = this.fileService.uploadImage(path, multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(null, "File Upload Failed"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new FileResponse(fileName, "File Upload Successfully"), HttpStatus.OK);
    }
}
