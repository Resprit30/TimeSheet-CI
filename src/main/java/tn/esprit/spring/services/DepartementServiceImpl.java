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
			l.debug("departement +++ : " + departement);
		}
		l.info("Out of getAllDepartements. ");
		return Departements;
	}

	public int ajouterDepartement(Departement dep) {
		try {
			l.info("In ajouterDepartement : ");
			deptRepoistory.save(dep);
			l.info("Out of ajouterDepartement. ");
			return dep.getId();
			}
	   catch (Exception e ) {
		   l.error("erreur dans ajouterDepartement() : " + e); 
		   return 0 ;}
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
				//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
		
		try{
		        l.info("In affecterDepartementAEntreprise :  ");
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
				l.debug("get entrepriseManagedEntity ");
				Departement depManagedEntity = deptRepoistory.findById(depId).get();
				l.debug("get depManagedEntity ");
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				l.debug("Affecter ");
				deptRepoistory.save(depManagedEntity);
				l.debug("Save");
				l.info("Out of affecterDepartementAEntreprise.  ");
		} catch (Exception e) {l.error("erreur dans affecterDepartementAEntreprise() : " + e);}
	}
	
	@Transactional
	public void deleteDepartementById(int depId) {
		try {
		  l.info("In deleteDepartementById  :  ");
		  l.debug("departement id= ", + depId);
		deptRepoistory.delete(deptRepoistory.findById(depId).get());
		  l.info("Out of deleteDepartementById.  ");
		} catch (Exception e){l.error("erreur dans deleteDepartementById() : " + e); }
	}
	
	
}
