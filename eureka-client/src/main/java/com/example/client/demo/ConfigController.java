package com.example.client.demo;

import com.example.client.config.GitAutoRefreshConfig;
import com.example.client.config.GitConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

	private final GitConfig gitConfig;

	private final GitAutoRefreshConfig gitAutoRefreshConfig;

	public ConfigController(GitConfig gitConfig, GitAutoRefreshConfig gitAutoRefreshConfig) {
		this.gitConfig = gitConfig;
		this.gitAutoRefreshConfig = gitAutoRefreshConfig;
	}

	@GetMapping(value = "show")
	public Object show(){
		return gitConfig;
	}

	@GetMapping(value = "autoShow")
	public Object autoShow(){
		return gitAutoRefreshConfig;
	}

}
