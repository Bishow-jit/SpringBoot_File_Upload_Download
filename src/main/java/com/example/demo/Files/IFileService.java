package com.example.demo.Files;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class IFileService implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile multipartFile) throws IOException {
        //filename
        String fileName = multipartFile.getOriginalFilename();

        //Random Name of the file


        String fileName1 = null;
        if (fileName != null) {
            String randomId = UUID.randomUUID().toString();
            fileName1 = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));
            System.out.println(fileName1);
        }
        //file full path
        String filePath = path + File.separator + fileName1;

        // create folder if not create
        File file = new File(path);

        if (!file.exists()) {
            file.mkdir();
        }
        //file copy
        Files.copy(multipartFile.getInputStream(), Paths.get(filePath));
        return fileName;
    }
}
