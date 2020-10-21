package tn.esprit.spring;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.EmployeServiceImpl;
import tn.esprit.spring.services.MissionServiceImpl;
import tn.esprit.spring.services.TimesheetServiceImpl;

@SpringBootTest
public class TimesheetTests {
	@Autowired
	EmployeServiceImpl EmployeServiceImpl;
	@Autowired
	MissionServiceImpl mission;
	@Autowired
	TimesheetServiceImpl Timesheet;
	
	
	@Test
	public void contextLoads() throws ParseException {
		Employe employe = new Employe(1,"Rihab","Ben Mansour","rihab.benmansour@esprit.tn","",true,Role.INGENIEUR); 
		EmployeServiceImpl.addOrUpdateEmploye(employe);
		Mission m=new Mission("Design", "description");
		mission.ajouterMission(m);
		DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
		Date datedeb=new Date();
		Date datefin=format.parse("2021/00/00");
		Timesheet.ajouterTimesheet(1, 1,datedeb, datefin);
		
	}
}
