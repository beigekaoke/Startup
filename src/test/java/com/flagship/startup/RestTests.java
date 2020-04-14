package com.flagship.startup;

import com.alibaba.fastjson.JSON;
import com.flagship.startup.entity.ComData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: flag ship
 * @Date: 2020/4/14 22:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTests {

    @Value("${testValidator.service1.url}")
    private String serviceUrl1;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testPost(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String content = JSON.toJSONString(getTestData());
        HttpEntity<String> request = new HttpEntity<>(content, headers);
        ResponseEntity<String> postForEntity = restTemplate.postForEntity(serviceUrl1, request, String.class);
        String body = postForEntity.getBody();
        System.out.println(body);
    }

    private List<ComData> getTestData(){
        List<ComData> lists = new ArrayList<ComData>();
        for(int i=0;i<10;i++){
            ComData data1 = new ComData(i,i+"name",new BigDecimal(i+23));
            lists.add(data1);
        }
        return lists;
    }

}
