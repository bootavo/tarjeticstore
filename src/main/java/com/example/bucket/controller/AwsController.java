package com.example.bucket.controller;

import com.example.bucket.service.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/")
public class AwsController {

    private AwsService awsService;

    @Autowired
    AwsController(AwsService awsService) {
        this.awsService = awsService;
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String uploadFile(@RequestPart(value = "name") String name, @RequestPart(value = "file") MultipartFile file) {
        return this.awsService.uploadFile(file, name);
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.awsService.deleteFileFromS3Bucket(fileUrl);
    }

}
