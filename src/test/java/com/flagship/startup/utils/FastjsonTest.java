package com.flagship.startup.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import com.flagship.startup.entity.ComData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.flagship.startup.entity.CsClaimData;

public class FastjsonTest {

	@Test
	public void beanToJsonTest() {
		CsClaimData ccd = new CsClaimData();
		ccd.setMemberName("巴巴爸爸");
		ccd.setEclaimRefNo("99999");
		ccd.setReceiptAmt(3434.00);
		ccd.setIncurDate(new Date());

		System.out.println(JSON.toJSONString(ccd));
	}

	@Test
	public void listToJsonTest(){
		String content = JSON.toJSONString(getTestData());
		System.out.println("List to json:"+content);
	}

	@Test
	public void jsonToBeanTest() {
		String str = "{\"meta\":{\"code\":\"0\",\"message\":\"同步成功!\"},\"data\":{\"orderno\":\"U_2018062790915774\",\"suborderno\":\"SUB_2018062797348039\",\"type\":\"organunit\",\"result\":{\"organunit\":{\"totalCount\":2,\"successCount\":0,\"failCount\":2,\"errors\":[{\"code\":\"UUM70004\",\"message\":\"组织单元名称不能为空\",\"data\":{\"id\":\"254\",\"suborderNo\":\"SUB_2018062797348039\",\"organUnitType\":\"部门\",\"action\":\"add\",\"parent\":\"10000\",\"ordinal\":0,\"organUnitFullName\":\"组织单元全称\"},\"success\":false},{\"code\":\"UUM70004\",\"message\":\"组织单元名称不能为空\",\"data\":{\"id\":\"255\",\"suborderNo\":\"SUB_2018062797348039\",\"organUnitType\":\"部门\",\"action\":\"add\",\"parent\":\"10000\",\"ordinal\":0,\"organUnitFullName\":\"组织单元全称\"},\"success\":false}]},\"role\":{\"totalCount\":0,\"successCount\":0,\"failCount\":0,\"errors\":[]},\"user\":{\"totalCount\":0,\"successCount\":0,\"failCount\":0,\"errors\":[]}}}}";
		JSONObject jsonObject = JSON.parseObject(str);
		JSONObject data = jsonObject.getJSONObject("data");
		JSONObject result = data.getJSONObject("result");

		String organunit1 = result.getString("organunit");
		System.out.println(organunit1);
		JSONObject organunit = result.getJSONObject("organunit");
		JSONArray errors2 = organunit.getJSONArray("errors");
		List<Error> error = JSON.parseObject(errors2.toJSONString(), new TypeReference<List<Error>>() {});
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
