package com.example.demo.FileUploadToDb;

import com.example.demo.Response.StudentResponse;
import com.example.demo.Util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudent {

    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity<?> create(Student student) {
        if (student != null) {
            if (student.getName() != null && !student.getName().isEmpty()) {
                Student studentToSave = new Student();
                studentToSave.setName(student.getName());

                if (student.getDepartment() != null && !student.getDepartment().isEmpty()) {
                    studentToSave.setDepartment(student.getDepartment());
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student dept is required");
                }

                Student savedStudent = studentRepository.save(studentToSave);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student name is required");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid student object");
        }
    }


    public List<Student> getAllStudentDetails() {
        List<Student> studentList = new ArrayList<>();
        try {
            studentList = studentRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return studentList;
    }

    @Override
    public String uploadImageToDB(String name, String department, MultipartFile multipartFile) {
        try {
            Student student = new Student();
            student.setName(name != null ? name : "N/A");
            student.setDepartment(department != null ? department : "N/A");
            student.setImageData(Base64.getEncoder().encodeToString(ImageUtils.compressImage(multipartFile.getBytes())));
            student.setImageName(multipartFile.getOriginalFilename());
            student.setImageType(multipartFile.getContentType());
            studentRepository.save(student);
            return "Successful uploaded file named" + multipartFile.getOriginalFilename();
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }

    }

    @Override
    public StudentResponse downloadImageFromDB(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            byte[] imageData = ImageUtils.decompressImage(Base64.getDecoder().decode(student.get().getImageData()));
            StudentResponse studentResponse = new StudentResponse();
            studentResponse.setStudent(student.get());
            studentResponse.setImageData(imageData);
            return studentResponse;
        }
        return null;
    }


}
