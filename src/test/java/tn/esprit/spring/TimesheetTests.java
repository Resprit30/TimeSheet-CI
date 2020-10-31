package tn.esprit.spring;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.EmployeServiceImpl;
import tn.esprit.spring.services.IDepartementService;
import tn.esprit.spring.services.IMissionService;
import tn.esprit.spring.services.ITimesheetService;
import tn.esprit.spring.services.TimesheetServiceImpl;



@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetTests {

	private static final Logger logger = Logger.getLogger(TimesheetTests.class);
	@Autowired
	private EmployeServiceImpl employeServiceImpl;
	@Autowired
	private IMissionService mission;
	@Autowired
	private ITimesheetService timesheetService;
	@Autowired
	private IDepartementService depService;
	@Autowired 
	private MissionRepository missionRepository;
	@Autowired
	private TimesheetRepository timesheetRepository;
	@Autowired
	private EmployeRepository employeRepository;
	
	DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
	
	@Test
	public void alimentation() throws ParseException { 

		
		Mission m=new Mission("Managment", "description");
		Employe employe = new Employe("Rihab","Ben Mansour","rihab.benmansour@esprit.tn","",true,Role.CHEF_DEPARTEMENT);
		Departement dep=new Departement("Conception");
		
		
//		depService.ajouterDepartement(dep);
//		m.setDepartement(dep);
//		
//		employeServiceImpl.addOrUpdateEmploye(employe);
	//	missionRepository.save(m);
	
		Date datedeb=format.parse("2020/09/03");
		Date datefin=format.parse("2021/10/01");
		//timesheetService.ajouterTimesheet(3, 2,datedeb, datefin);
	
	}
	
	
	@Test
	public void affichage() throws ParseException{
		int i=0; 
		Long num=timesheetRepository.count();
		logger.info("there are "+num.intValue() +" timesheets in total");
	List<Timesheet> lt=timesheetService.getTimesheetsByMissionAndDate(employeRepository.findById(1).get(), missionRepository.findById(1).get(), format.parse("2020/00/00"), format.parse("2021/00/00"));
		for (Timesheet t:lt)
	{String valide="NOT VALID";
	i++;
	if (t.isValide())
		valide="VALID";
			logger.info("timesheet "+i +" Mission "+ missionRepository.findById(1).get().getName()+ " has employee  "+t.getEmploye().getNom()+" "+t.getEmploye().getPrenom()+ " working from " +t.getTimesheetPK().getDateDebut() + " to " +t.getTimesheetPK().getDateFin()+"======"+valide);}
		
	}
	
	
	@Test 
	public void validation () throws ParseException{
	//timesheetService.validerTimesheet(2, 3,format.parse("2020/09/03"), format.parse("2020/10/01"),2);
		TimesheetPK timesheetPK = new TimesheetPK(2, 2, format.parse("2020/10/31"), format.parse("2020/11/29"));
		Optional<Timesheet> timesheet =timesheetRepository.findById(timesheetPK);
		System.out.println(timesheet);
	//affichage();
	}
	
}
