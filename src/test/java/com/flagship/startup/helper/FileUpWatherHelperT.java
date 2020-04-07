package com.flagship.startup.helper;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.junit.jupiter.api.Test;

public class FileUpWatherHelperT {

	@Test
	public void testFileUpWather() throws IOException {
		// 说明，这里的监听也必须是目录
		Path path = Paths.get("C:\\OCR_file_folder\\output");
		WatchService watcher = FileSystems.getDefault().newWatchService();
		path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

		new Thread(() -> {
			try {
				while (true) {
					WatchKey key = watcher.take();
					for (WatchEvent<?> event : key.pollEvents()) {

						if (event.kind() == StandardWatchEventKinds.OVERFLOW) {
							// 事件可能lost or discarded
							continue;
						}
						Path fileName = (Path) event.context();
						System.out.println("文件更新: " + fileName);
					}
					if (!key.reset()) { // 重设WatchKey
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		try {
			Thread.sleep(1000 * 60 * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
