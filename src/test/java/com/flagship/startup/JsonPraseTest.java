package com.flagship.startup;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flagship.startup.entity.CsClaimData;
import com.flagship.startup.entity.ValidationConfig;
import com.flagship.startup.utils.ConstrantString;

public class JsonPraseTest {
	
	@Test
	public void praseTest() {
		ClassPathResource classPathResource = new ClassPathResource("validationConfig.json");
		String configJson = null;
		if (classPathResource != null) {
			try {
				byte[] bdata = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
				configJson = new String(bdata, StandardCharsets.UTF_8);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		JSONObject jsonObject = JSON.parseObject(configJson);
		JSONArray array = jsonObject.getJSONArray("ValidateConfig");
		List<ValidationConfig> list = JSONObject.parseArray(array.toJSONString(), ValidationConfig.class);
		for(ValidationConfig config:list) {
			System.out.println(config.toString());
		}
		String scanSrcFolder = "C:\\OCR_file_folder\\input";
		Collection<File> srcListFiles = FileUtils.listFiles(new File(scanSrcFolder),
				new String[] { ConstrantString.CSV_EXTENSION }, false);
		List<CsClaimData> resultDatas = new ArrayList<CsClaimData>();
		for (File srcfile : srcListFiles) {
			
		}
	}
}
