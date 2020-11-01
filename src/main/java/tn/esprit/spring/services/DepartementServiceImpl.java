package tn.esprit.spring.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class DepartementServiceImpl implements IDepartementService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	private static final Logger l = LogManager.getLogger(DepartementServiceImpl.class);

	public List<Departement> getAllDepartements() {
		l.info("In getAllDepartements : ");
		List<Departement> Departements = (List<Departement>) deptRepoistory.findAll();
		for (Departement departement : Departements )
		{
			l.info("departement +++ : " + departement);
		}
		l.info("Out of getAllDepartements. ");
		return Departements;
	}

	public Departement ajouterDepartement(Departement dep) {
		try {
			l.info("In ajouterDepartement : ");
			Departement depSaved = deptRepoistory.save(dep);
			l.info("Out of ajouterDepartement. ");
			return depSaved;
			}
	   catch (Exception e ) {
		   l.error("erreur dans ajouterDepartement() : " + e); 
		   Departement depVide = new Departement ();
		   return depVide ;}
	}
	
	public Departement affecterDepartementAEntreprise(int depId, int entrepriseId) {
				//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
		
		try{
		       
				l.info("In affecterDepartementAEntreprise :  ");
				
		       
		        Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
				
				l.info("get entrepriseManagedEntity ");
				
				Departement depManagedEntity = deptRepoistory.findById(depId).get();
				
				l.info("get depManagedEntity ");
				
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				
				l.info("Affecter ");
				
				Departement d = deptRepoistory.save(depManagedEntity);
				
				l.info("Save");
				
				l.info("Out of affecterDepartementAEntreprise.  ");
				
				return d ;
				
		} 
		
		catch (Exception e) {
			
			l.error("erreur In affecterDepartementAEntreprise() : Failed to affect " + e);}
		return null ; 
	}
	
	
	@Override
	public Departement getDepartmentById(int departmentId) {
		try {
			l.info(" In getDepartementById() : ");
			l.info("departement : " + departmentId );
		return  deptRepoistory.findById(departmentId).get();
		} catch (Exception e) {
			l.error("get departement operation failed");	
		}
		l.info(" Out of getDepartementById(). ");
		return null;
	}
	
	@Transactional
	public void deleteDepartementById(int depId) {
		try {
		  l.info("In deleteDepartmentById  :  ");
		  
		  l.info(" department id= " + depId);
		  
		  deptRepoistory.delete(deptRepoistory.findById(depId).get());
		
		  l.info("Out of deleteDepartmentById.  ");
		  
		} 
		catch (Exception e) {
			
			l.error("erreur In deleteDepartementById() : could not be found " + e); }
	}
		
	
	}

