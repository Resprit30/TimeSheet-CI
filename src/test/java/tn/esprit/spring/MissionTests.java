package tn.esprit.spring;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.IMissionService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MissionTests {

	
	
	@Autowired
	IMissionService ms;
	
	@Test
	public void addMission() throws ParseException {
		Mission mission = new Mission( "testLog4j", "testestLog4j");
		ms.ajouterMission(mission);
	
	}
	
//	@Test
//	public void getAllEmployeByMission(){
//		List<Employe> employeList = new ArrayList<Employe>();
//		employeList=ms.getAllEmployeByMission(1);
//	}
//	
//	@Test
//	public void affecterMissionADepartement(){
//	
//		ms.affecterMissionADepartement(1, 1);
//		
//	}
//
//	@Test
//	public void findAllMissionByEmployeJPQL(){
//		List<Mission> missionList = new ArrayList<Mission>();
//		missionList=ms.findAllMissionByEmployeJPQL(1);
//	}

}
