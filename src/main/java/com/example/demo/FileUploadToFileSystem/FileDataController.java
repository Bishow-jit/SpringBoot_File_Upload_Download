package com.example.demo.FileUploadToFileSystem;

import com.example.demo.Response.FileDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping(value = "/fileSystem")
public class FileDataController {

    @Autowired
    private FileDataService fileDataService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFileToFileSystem(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String response = fileDataService.uploadToFileSystem(multipartFile);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File Upload to File System Failed");

    }

    @GetMapping(value = "/download/{filename}")
    public ResponseEntity<?> downloadFileFromFileSystem(@PathVariable("filename") String fileName) throws IOException {
        FileDataResponse fileDataResponse = fileDataService.downloadFromFileSystem(fileName);
        if (fileDataResponse != null) {
            String contentType = fileDataResponse.getFileData().getFileTye();
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(contentType))
                    .body(fileDataResponse.getFileByte());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail To Download File From File System");
    }

}
