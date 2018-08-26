package com.example.bucket.repository;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface AwsRepository {

    String uploadFile(MultipartFile multipartFile, String name);
    File convertMultiPartToFile(MultipartFile file) throws IOException;
    String generateFileName(MultipartFile multiPart, String name);
    void uploadFileTos3bucket(String fileName, File file) throws IOException;
    String deleteFileFromS3Bucket(String fileUrl);

}
