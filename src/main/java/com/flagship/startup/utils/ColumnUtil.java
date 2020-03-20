package com.flagship.startup.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.flagship.startup.entity.CsClaimData;
import com.opencsv.bean.CsvBindByName;

public class ColumnUtil {

	public static Map<String,String> getColumnMap(Class classz) throws NoSuchFieldException, SecurityException {
		Map<String,String> columnMap = new HashMap<String,String>();
		Field[] fields = classz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			Field field = classz.getDeclaredField(fields[i].getName());
			CsvBindByName column = field.getAnnotation(CsvBindByName.class); 
			if (column != null) {
				columnMap.put(column.column(), field.getName());
			}
		}
		return columnMap;
	}	
	
	/**
	 * 根据属性，获取get方法
	 * @param ob 对象
	 * @param fieldName 属性名
	 * @return
	 * @throws Exception
	 */
	public static Object getGetMethod(Object ob , String fieldName)throws Exception{
		Method[] m = ob.getClass().getMethods();
		for(int i = 0;i < m.length;i++){
			if(("get"+fieldName).toLowerCase().equals(m[i].getName().toLowerCase())){
				return m[i].invoke(ob);
			}
		}
		return null;
	}
	
	public static String getDateStr(int days,String format) {
		SimpleDateFormat sj = new SimpleDateFormat(format);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return sj.format(calendar.getTime());
	}

	public static void main(String[] args) throws Exception {
		Map<String,String> columnMap = getColumnMap(CsClaimData.class);
		
		CsClaimData data = new CsClaimData();
		data.setEclaimRefNo("112233445");
		data.setMemberName("wu hua hua");
		data.setReceiptAmt(12.99);
		
		for(String key:columnMap.keySet()) {
			System.out.println(key + "______"+columnMap.get(key));
			Object ob = getGetMethod(data, columnMap.get(key));
			System.out.println(ob);
		}
		
		System.out.print(getDateStr(0,"yyyyMMdd"));
	}
}
