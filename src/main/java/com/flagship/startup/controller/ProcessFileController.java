package com.flagship.startup.controller;

import com.flagship.startup.service.FileService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: flag ship
 * @Date: 2020/3/28 15:25
 */
@RestController
@EnableAsync
@RequestMapping(value = "/file")
public class ProcessFileController {

    @Autowired
    private FileService fileService;

    @Value("${upload.saved.folder}")
    private String uploadSavedFolder;

    @PostMapping(value = "/testAsync")
    public void processEmailFiles(){
        System.out.println("Controller thread:" + Thread.currentThread().getName() + " begin to execute...");
        fileService.processEmailFile(null);
        System.out.println("Controller thread  execute end.....");
    }

    @PostMapping(value = "/uploadFiles")
    public String uploadFiles(@RequestParam("file")MultipartFile file){
        if(file.isEmpty()){
            return "file is empty!";
        }
        String fileName = file.getOriginalFilename();
        String destPath = uploadSavedFolder + File.separator + fileName;
        try {
            transferTo(file, destPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "upload file success!";
    }

    private void transferTo(MultipartFile file, String destPath) throws IOException {
        File dest = new File(destPath);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        file.transferTo(dest);
    }
}
