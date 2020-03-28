package com.flagship.startup.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: flag ship
 * @Date: 2020/3/28 15:32
 */
public interface FileService {

    public void processEmailFile(MultipartFile...files);
}
