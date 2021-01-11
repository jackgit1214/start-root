package com.consul.demo.controller;

import com.consul.demo.config.MySqlComplexConfig;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConfigController {

//	@Autowired
//	private MySqlConfig mySqlConfig;

	private final MySqlComplexConfig mySqlComplexConfig;

	public ConfigController(MySqlComplexConfig mySqlComplexConfig) {
		this.mySqlComplexConfig = mySqlComplexConfig;
	}


//	@GetMapping(value = "mysqlhost")
//	public String getMysqlHost(){
//		return mySqlConfig.getHost();
//	}

	@GetMapping(value = "mysqluser")
	public String getMysqlUser(){
		log.info(mySqlComplexConfig.getHost());
		MySqlComplexConfig.UserInfo userInfo = mySqlComplexConfig.getUser();
		return userInfo.toString();
	}
}