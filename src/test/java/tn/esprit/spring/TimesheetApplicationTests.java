package tn.esprit.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.services.IMissionService;


@SpringBootTest

@RunWith(SpringRunner.class)

public class TimesheetApplicationTests {

	@Autowired
	IMissionService ms;
	
	@Test
	public void contextLoads() {}
}
