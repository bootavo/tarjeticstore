package com.example.bucket.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.bucket.repository.AwsRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class AwsService implements AwsRepository {

    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketTarjetic}")
    private String bucketTarjetic;
    @Value("${amazonProperties.bucketUsers}")
    private String bucketUsers;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;
    private String bucketCustom;

    private static Logger logger = LogManager.getLogger(AwsService.class);

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = new AmazonS3Client(credentials);
    }

    @Override
    public String uploadFile(MultipartFile multipartFile, String name) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile, name);

            //Create personal bucket
            if (!s3client.doesBucketExist(bucketTarjetic)) {
                s3client.createBucket(new CreateBucketRequest(name));
            }

            bucketCustom = name;
            fileUrl = endpointUrl + "/" + bucketTarjetic + "/" + bucketUsers + "/" + bucketCustom + "/" + fileName;

            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            fileUrl = null;
           e.printStackTrace();
        }
        return fileUrl;
    }

    @Override
    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    @Override
    public String generateFileName(MultipartFile multiPart, String name) {

        String original_name = multiPart.getOriginalFilename().replace(" ", "_");

        String based_name = FilenameUtils.getBaseName(original_name);
        String extension = "."+FilenameUtils.getExtension(original_name);

        return name + "-" + new Date().getTime() + extension;
    }

    @Override
    public void uploadFileTos3bucket(String fileName, File file) {
        String bucket = bucketTarjetic + "/" + bucketUsers + "/" + bucketCustom;
        s3client.putObject(new PutObjectRequest(bucket, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public String deleteFileFromS3Bucket(String fileUrl) {
        String bucket = bucketTarjetic + "/" + bucketUsers + "/" + bucketCustom;
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucket, fileName));
        return "Successfully deleted";
    }

}
