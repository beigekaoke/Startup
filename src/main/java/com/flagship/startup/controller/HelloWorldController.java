package com.flagship.startup.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flagship.startup.entity.Location;
import com.flagship.startup.repository.LocationRepository;
import com.flagship.startup.websocket.WebSocketServer;

@Api(value="Hello world controller",tags={"Websocket operation api"})
@RestController
public class HelloWorldController {
	
	@Autowired
    private LocationRepository locationRepository;

	@RequestMapping("info")
	public String info() {
		return "Hello world!";
	}

	@ApiOperation(value = "push video", notes="push video to webd")
	@ApiImplicitParam(name = "account", value = "账户", paramType = "query", required = true, dataType = "json")
	@RequestMapping(value = "/pushVideoListToWeb", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, Object> pushVideoListToWeb(@RequestBody Map<String, Object> param) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			WebSocketServer.sendInfo("有新客户呼入,sltAccountId:" + param);
			result.put("operationResult", true);
		} catch (IOException e) {
			result.put("operationResult", true);
		}
		return result;
	}
	
	@ResponseBody
    @RequestMapping("/hello")
    public List<Location> hello(){
        return locationRepository.findAll();
    }
}
