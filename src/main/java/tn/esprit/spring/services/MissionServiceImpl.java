package tn.esprit.spring.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class MissionServiceImpl implements IMissionService {

	@Autowired
	MissionRepository missionRepository;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	
	private static final Logger l = LogManager.getLogger(IMissionService.class);

	
	
	public int ajouterMission(Mission mission) {
		missionRepository.save(mission);
		l.info("the mission " +mission.getName()+" is added successfully !!");
		return mission.getId();
	}
	
	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
		
		l.info("the emolyee's mission : " +timesheetRepository.findAllMissionByEmployeJPQL(employeId));

		return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
	}

	
	public List<Employe> getAllEmployeByMission(int missionId) {
		
		l.info("the mission's employees : " +timesheetRepository.getAllEmployeByMission(missionId));

		return timesheetRepository.getAllEmployeByMission(missionId);
	}
	
	public void affecterMissionADepartement(int missionId, int depId) {
		Mission mission = missionRepository.findById(missionId).get();
		Departement dep = deptRepoistory.findById(depId).get();
		mission.setDepartement(dep);
		missionRepository.save(mission);
		
		l.info("the mission " +mission.getName()+" is affected successfully to "+dep.getName());

		
	}
}
