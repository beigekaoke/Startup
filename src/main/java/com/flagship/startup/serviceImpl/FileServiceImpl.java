package com.flagship.startup.serviceImpl;

import com.flagship.startup.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: flag ship
 * @Date: 2020/3/28 15:35
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    @Async("threadPoolTaskExecutor")
    public void processEmailFile(MultipartFile... files) {
        System.out.println("ProcessEmailFile thread:" + Thread.currentThread().getName() + " begin to execute...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ProcessEmailFile threadName:" + Thread.currentThread().getName() + " execute end ...");
    }
}
