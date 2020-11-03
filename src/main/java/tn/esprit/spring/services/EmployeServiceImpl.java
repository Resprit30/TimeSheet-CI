package tn.esprit.spring.services;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;

import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import org.apache.log4j.Logger;

@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	private static final Logger l = Logger.getLogger(EmployeServiceImpl.class);



	public Integer addOrUpdateEmploye(Employe employe) {
		l.debug("Methode ajouterEmployee");
		try {
			employeRepository.save(employe);
			l.info("Employe ajoutée avec id = "+employe.getId());
		return employe.getId();
		}
		catch (Exception e) {
		       l.error("erreur methode ajouterEntreprise :" +e);	
		       return null;       
				}		
	}


	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		l.debug("Methode mettre a jour email employee");
		try {
		Employe employe = employeRepository.findById(employeId).orElse(null);
		if(employe!=null){
		employe.setEmail(email);
		employeRepository.save(employe);
		l.debug("mettreAjourEmailByEmployeId fini avec succes ");
		
		}
		}
		catch (Exception e) {
			l.error("erreur methode affecter employeadepartement : " +e);
		}
		
	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		l.debug("Methode mettre a jour email employee");
		Departement depManagedEntity = deptRepoistory.findById(depId).orElse(null);
		Employe employeManagedEntity = employeRepository.findById(employeId).orElse(null);
try {
	 if(depManagedEntity!=null)
		if( depManagedEntity.getEmployes() == null){

			List<Employe> employes = new ArrayList<>();
			employes.add(employeManagedEntity);
			depManagedEntity.setEmployes(employes);
		}else{

			depManagedEntity.getEmployes().add(employeManagedEntity);
		}


		deptRepoistory.save(depManagedEntity); 
		l.debug("effecterEmployeAdepartement fini avec succes ");
		}
catch (Exception e) {
	l.error("erreur methode affecter employeadepartement : " +e);
	
}		

	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		l.debug("methode desaffecterEmployeDuDepartement ");
		try{
		Departement dep = deptRepoistory.findById(depId).orElse(null);
if(dep!=null){
		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				break;
				
			}
			l.debug("desaffecterEmployeDuDepartement fini avec succes ");
		}}
		}
		catch (Exception e) {
			l.error("erreur methode desaffecterEmployeDuDepartement : " +e);}
		
	} 
	
	
	public String getEmployePrenomById(int employeId) {
		l.debug("methode getEmplpyeById ");
		try {
		Employe employeManagedEntity = employeRepository.findById(employeId).orElse(null);
		l.debug("getEmployeById fini avec succes ");
		
		return employeManagedEntity!=null ?employeManagedEntity.getPrenom():null;}
		catch (Exception e) {
			l.error("erreur methode getEmployeeById : " +e);
			return null;
		}	
		
	}
	 
	public int deleteEmployeById(int employeId)
	{
		l.debug("methode deleteEmployeById ");
		
		try {
		Employe employe = employeRepository.findById(employeId).orElse(null);

		
	if(employe!=null && employe.getDepartements()!=null)
		for(Departement dep : employe.getDepartements()){
			dep.getEmployes().remove(employe);
		
		}

		employeRepository.delete(employe);
		return -1;}
		catch (Exception e) {
			l.error("erreur methode deleteEmpolyeById : " +e);
			return 0;
		}		
	}

	public Employe getEmployeeById(int employeId) {
		l.debug("methode getEmployeById ");
		
		
		try {
			Employe et= employeRepository.findById(employeId).orElse(null);
			l.debug("getEmployeById fini avec succes ");
			return et;
		} catch (Exception e) {
			l.error("erreur methode getEmployeById : " +e);
			return null;
		}	
		
		
	}
	

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}

	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}

	

	public List<Employe> getAllEmployes() {
		return (List<Employe>) employeRepository.findAll();
	}
	

}
