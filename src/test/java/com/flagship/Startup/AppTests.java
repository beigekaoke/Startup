package com.flagship.Startup;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.flagship.startup.entity.Location;
import com.flagship.startup.repository.LocationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTests {
	
	private static final Logger log = LoggerFactory.getLogger(AppTests.class);

	@Autowired
    private LocationRepository locationRepository;
	
	@Test
	public void test1() throws Exception {
		locationRepository.save(new Location((long) 1,"1",38.998064, 117.317267));
		locationRepository.save(new Location((long)2,"2",38.997793, 117.317069));
		locationRepository.save(new Location((long)3,"3",38.998006, 117.317101));
		locationRepository.save(new Location((long)4,"4",38.997814, 117.317332));
		List<Location> u1 = locationRepository.findAll();
		log.info("[条件查询] - [{}]", u1);
	}
	
	

}
