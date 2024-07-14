package com.example.demo.FileUploadToFileSystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileDataRepository extends JpaRepository<FileData,Long> {

//    Optional<FileData> findByFileName(String fileName);

    @Query(value = "SELECT * FROM file_data WHERE file_name=:fileName",nativeQuery = true)
    Optional<FileData> findByName(String fileName);


}
