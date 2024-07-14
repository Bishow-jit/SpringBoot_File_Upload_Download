package com.example.demo.FileUploadToDb;

import com.example.demo.Response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/student", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        return studentService.create(student);
    }

    @GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getALLStudentDetails() {
        try {
            return ResponseEntity.ok(studentService.getAllStudentDetails());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("SORRY NO DATA RETRIEVED");
        }
    }

    @PostMapping(value = "/imageUploadWithDetails")
    public ResponseEntity<?> uploadImage(@RequestParam("name") String name, @RequestParam("department") String department,
                                         @RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(studentService.uploadImageToDB(name, department, file));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("FAILED TO UPLOAD IMAGE");
        }
    }

    @GetMapping(value = "/image/Download/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable("id") Long id) {
        try {
            StudentResponse studentResponse = studentService.downloadImageFromDB(id);
//            String mediaFormat = imageName.substring(imageName.lastIndexOf(".")+1);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf(studentResponse.getStudent().getImageType()))
                    .body(studentResponse.getImageData());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Image Download Failed");
        }
    }
}
