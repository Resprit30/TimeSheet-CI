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
		List<Departement> depA = (List<Departement>) deptRepoistory.findAll();
		for (Departement departement : depA )
		{
			l.info("departement +++ : " + departement);
		}
		l.info("Out of getAllDepartements. ");
		return depA;
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
		   
		   return null;}
	}
	
	public Departement affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
		
		try {
			     l.info("In affecterDepartementAEntreprise :  ");
			
				    Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
		
					l.debug("get entrepriseManagedEntity ");
		
				    Departement depManagedEntity = deptRepoistory.findById(depId).orElse(null);
		
				    l.debug("get depManagedEntity ");
				    
				if(depManagedEntity!=null){
					l.debug("In If ");
					 depManagedEntity.setEntreprise(entrepriseManagedEntity);
				l.debug("Affecter ");
				   Departement d = deptRepoistory.save(depManagedEntity);
				l.debug("Save");	
				l.info("Out of affecterDepartementAEntreprise.  ");
				return d ;
				}
		
				return null ;
		}catch (Exception e) {
			
			l.error("erreur In affecterDepartementAEntreprise() : Failed to affect " + e);
			return null ; }
		 
		}

	
	

	@Transactional
	public Departement desaffecterDepartementDuEntreprise (int depId , int entId){
		try {
			l.info("In desaffecterDepartementDuEntreprise :  ");
			Entreprise ent = entrepriseRepoistory.findById(entId).orElse(null);
			l.info("get ent ");
			Departement depManagedEntity = deptRepoistory.findById(depId).orElse(null);
			l.info("get depManagedEntity ");
			if (depManagedEntity != null){
		  if (depManagedEntity.getEntreprise() == ent )
		    {
			l.debug("In if.. ");
			depManagedEntity.setEntreprise(null);
		
			l.debug("Removed. ");
		    }
	
		    l.info("Out of desaffecterDepartementDuEntreprise.  ");
		    return depManagedEntity ;
			}
			return null; 
	
		}catch (Exception e) {
			l.error("erreur In affecterDepartementAEntreprise() : Failed to affect " + e);
			return null ; 

		}

		}


			
				public Departement getDepartmentById(int departmentId) {
					try {
							l.info(" In getDepartementById() : ");
							
							Departement dep =  deptRepoistory.findById(departmentId).orElse(null);
							l.info(" Out of getDepartementById(). ");
							return dep ; 
					} catch (Exception e) {
						l.error("get departement operation failed");
						return null;
					}	

			}
	

	
	@Transactional
	public int deleteDepartementById(int depId) {
		try {
			  l.info("In deleteDepartmentById  :  ");
			  
			  l.info(" department id= " + depId);
			  Departement depA = deptRepoistory.findById(depId).orElse(null);
			  if (depA != null )
			  {		
		deptRepoistory.delete(deptRepoistory.findById(depId).orElse(null));	
		 l.info("Out of deleteDepartmentById.  ");
		  return 0;}
		  else { return -1; }
		 
		} 
		catch (Exception e) {
			
			l.error("erreur In deleteDepartementById() : could not be found " + e); 
			return -1 ;
			 }
			  
	}
	
	
}
