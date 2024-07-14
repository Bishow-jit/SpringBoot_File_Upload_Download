package com.example.demo.FileUploadToFileSystem;

import com.example.demo.Response.FileDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class FileDataService implements IFileUploadToFileSystem {

    @Autowired
    private FileDataRepository fileDataRepository;

    private static final String folderPath = "/D:/Work/FileSystem Upload/";


    @Override
    public String uploadToFileSystem(MultipartFile multipartFile) throws IOException {
        String fileUploadPath = folderPath + multipartFile.getOriginalFilename();
        FileData fileData = fileDataRepository.save(FileData.builder()
                .fileName(multipartFile.getOriginalFilename())
                .filePath(fileUploadPath)
                .fileTye(multipartFile.getContentType())
                .build());
        multipartFile.transferTo(new File(fileUploadPath));

        if (fileData != null) {
            return "File " + fileData.getFileName() + " upload to path =" + fileData.getFilePath();
        }
        return null;
    }

    @Override
    public FileDataResponse downloadFromFileSystem(String fileName) throws IOException {
        if(fileName != null && !fileName.isEmpty()){
            Optional<FileData> fileData = fileDataRepository.findByName(fileName);
            if (fileData.isPresent()) {
                byte[] fileBytes = Files.readAllBytes(new File(fileData.get().getFilePath()).toPath());
                FileDataResponse fileDataResponse = new FileDataResponse();
                fileDataResponse.setFileByte(fileBytes);
                fileDataResponse.setFileData(fileData.get());
                return fileDataResponse;
            } else {
                return null;
            }
        }
        return null;
    }


}
