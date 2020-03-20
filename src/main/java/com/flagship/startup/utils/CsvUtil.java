package com.flagship.startup.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class CsvUtil {
	/**
	 * 读取流中前面的字符，看是否有bom，如果有bom，将bom头先读掉丢弃 (opencsv 按列名获取bean对象，第一列缺失的情况)
	 * InputStreamReader is = new InputStreamReader( CsvUtil.getInputStream(new
	 * FileInputStream(fileName)), "utf-8");
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static InputStream getInputStream(InputStream in) throws IOException {

		PushbackInputStream testin = new PushbackInputStream(in);
		int ch = testin.read();
		if (ch != 0xEF) {
			testin.unread(ch);
		} else if ((ch = testin.read()) != 0xBB) {
			testin.unread(ch);
			testin.unread(0xef);
		} else if ((ch = testin.read()) != 0xBF) {
			throw new IOException("错误的UTF-8格式文件");
		} else {
		}
		return testin;
	}

}
