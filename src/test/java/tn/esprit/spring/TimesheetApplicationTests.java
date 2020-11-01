package tn.esprit.spring;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.IMissionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetApplicationTests {

	@Autowired
	IMissionService ms;
	
	@Test
	public void addMission() throws ParseException {
		Mission mission = new Mission( "test", "testest");
		ms.ajouterMission(mission);
	
	}
}
