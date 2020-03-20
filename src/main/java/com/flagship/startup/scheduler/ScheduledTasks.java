package com.flagship.startup.scheduler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flagship.startup.entity.CsClaimData;
import com.flagship.startup.entity.CsClaimOcr;
import com.flagship.startup.entity.ValidationConfig;
import com.flagship.startup.utils.ColumnUtil;
import com.flagship.startup.utils.ConstrantString;
import com.flagship.startup.utils.CsvUtil;
import com.flagship.startup.utils.RedisUtil;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

@Component
public class ScheduledTasks {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

	private static long cacheExpiredSeconds = 24 * 60 * 60;

	@Value("${ocr.validation.scan.srcfolder}")
	private String scanSrcFolder;

	@Value("${ocr.validation.scan.destfolder}")
	private String scanDestFolder;

	@Autowired
	private RedisUtil redisUtil;

	@Scheduled(fixedDelay = 300000) // 30 minutes to execute batch job
	public void scanFolderAndProcess() {
		Map<String,Map<String,Object>> srcDataMap = new HashMap<String,Map<String,Object>>();
		Map<String,Map<String,Object>> destDataMap = new HashMap<String,Map<String,Object>>();
		// 1.get json config
		ClassPathResource classPathResource = new ClassPathResource("validationConfig.json");
		String configJson = null;
		if (classPathResource != null) {
			try {
				byte[] bdata = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
				configJson = new String(bdata, StandardCharsets.UTF_8);
			} catch (IOException e) {
				LOGGER.error("Get validationConfig error {}.", e.getMessage());
			}
		}
		JSONObject jsonObject = JSON.parseObject(configJson);
		JSONArray array = jsonObject.getJSONArray("ValidateConfig");
		List<ValidationConfig> validationConfigList = JSONObject.parseArray(array.toJSONString(),
				ValidationConfig.class);

		// 2. scan src folder and dest folder
		Collection<File> srcListFiles = FileUtils.listFiles(new File(scanSrcFolder),
				new String[] { ConstrantString.CSV_EXTENSION }, false);
		List<CsClaimData> claimDatas = new ArrayList<CsClaimData>();
		for (File srcfile : srcListFiles) {
			InputStreamReader is = null;
			try {
				is = new InputStreamReader(CsvUtil.getInputStream(new FileInputStream(srcfile.getAbsoluteFile())),
						"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			HeaderColumnNameMappingStrategy<CsClaimData> mappingStrategy = new HeaderColumnNameMappingStrategy<>();
			mappingStrategy.setType(CsClaimData.class);
			CsvToBean<CsClaimData> build = new CsvToBeanBuilder<CsClaimData>(is).withMappingStrategy(mappingStrategy)
					.withSeparator(',').build();
			List<CsClaimData> subList = build.parse();
			claimDatas.addAll(subList);
			LOGGER.info("File {} prase list size {}", srcfile.getAbsoluteFile(), subList.size());
		}
		// this get annotion can be cache
		Map<String, String> claimColumnMap = new HashMap<String, String>();
		try {
			claimColumnMap = ColumnUtil.getColumnMap(CsClaimData.class);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		// save claim data to redis or memory
		for (CsClaimData data : claimDatas) {
			System.out.println(data.toString());
			String key = "src_" + data.getEclaimRefNo().trim();
			Map<String, Object> map = new HashMap<String, Object>();
			// get CSV annotation and this field's getXXX method and invoke this method then
			// put to map
			for (String columnKey : claimColumnMap.keySet()) {
				try {
					Object obj = ColumnUtil.getGetMethod(data, claimColumnMap.get(columnKey));
					map.put(columnKey, obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			redisUtil.hmset(key, map, cacheExpiredSeconds);
			srcDataMap.put(data.getEclaimRefNo().trim(), map);
		}

		Collection<File> destListFiles = FileUtils.listFiles(new File(scanDestFolder),
				new String[] { ConstrantString.CSV_EXTENSION }, false);
		List<CsClaimOcr> ocrDatas = new ArrayList<CsClaimOcr>();
		for (File destfile : destListFiles) {
			InputStreamReader is = null;
			try {
				is = new InputStreamReader(CsvUtil.getInputStream(new FileInputStream(destfile.getAbsoluteFile())),
						"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			HeaderColumnNameMappingStrategy<CsClaimOcr> mappingStrategy = new HeaderColumnNameMappingStrategy<>();
			mappingStrategy.setType(CsClaimOcr.class);
			CsvToBean<CsClaimOcr> build = new CsvToBeanBuilder<CsClaimOcr>(is).withMappingStrategy(mappingStrategy)
					.withSeparator(',').build();
			List<CsClaimOcr> subList = build.parse();
			ocrDatas.addAll(subList);
			LOGGER.info("File {} prase list size {}", destfile.getAbsoluteFile(), subList.size());
		}

		// this get annotion can be cache
		Map<String, String> ocrColumnMap = new HashMap<String, String>();
		try {
			ocrColumnMap = ColumnUtil.getColumnMap(CsClaimOcr.class);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		// save claim data to redis or memory
		for (CsClaimOcr data : ocrDatas) {
			System.out.println(data.toString());
			String key = "dest_" + data.getRefNo().trim();
			Map<String, Object> map = new HashMap<String, Object>();
			// get CSV annotation and this field's getXXX method and invoke this method then
			// put to map
			for (String columnKey : ocrColumnMap.keySet()) {
				try {
					Object obj = ColumnUtil.getGetMethod(data, ocrColumnMap.get(columnKey));
					map.put(columnKey, obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			redisUtil.hmset(key, map, cacheExpiredSeconds);
			destDataMap.put(data.getRefNo().trim().trim(), map);
		}

		// get data from cache to compare
		for (ValidationConfig validationConfig : validationConfigList) {
			if (ConstrantString.TYPE_FILE.equals(validationConfig.getDestType())
					&& ConstrantString.TYPE_FILE.equals(validationConfig.getSrcType())) {
				for(String key:destDataMap.keySet()) {
					destDataMap.get(key).get(validationConfig.getDestField());
					
				}
			}
			if (ConstrantString.TYPE_FILE.equals(validationConfig.getDestType())
					&& ConstrantString.TYPE_DATA_BASE.equals(validationConfig.getSrcType())) {
				
			}
			///validationConfig.getDestType();
			///validationConfig.getDestField();
		}
		
	}
}
