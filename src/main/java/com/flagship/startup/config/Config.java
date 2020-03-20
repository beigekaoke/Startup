package com.flagship.startup.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "1")
public class Config {
	private List<String> servers = new ArrayList<String>();

	public List<String> getServers() {
		return this.servers;
	}
}