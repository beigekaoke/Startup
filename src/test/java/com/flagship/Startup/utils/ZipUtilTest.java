package com.flagship.startup.utils;

import org.junit.Test;

public class ZipUtilTest {

    @Test
    public void unzipFileTest() throws Exception {
        String srcPath = "D:\\java_projects\\Startup\\mail_attachment\\flagship.zip";
        ZipUtil.decompress(srcPath);
    }
}
