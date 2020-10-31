package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import org.apache.log4j.Logger;
@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class);
	
	public Integer ajouterEntreprise(Entreprise entreprise) {
		l.debug("methode ajouterEntreprise");
		try {
			entrepriseRepoistory.save(entreprise);
			l.info("entreprise ajoutée avec id = "+entreprise.getId());
			return entreprise.getId();
		} catch (Exception e) {
       l.error("erreur methode ajouterEntreprise :" +e);	
       return null;       
		}		
		
	}

	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		l.debug("methode getAllDepartementsNamesByEntreprise ");
		List<String> depNames = new ArrayList<>();
		try {
			Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).isPresent() ? entrepriseRepoistory.findById(entrepriseId).get(): null;
			
			for(Departement dep : entrepriseManagedEntity.getDepartements()){
				depNames.add(dep.getName());
			}
			l.debug("getAllDepartementsNamesByEntreprise fini avec succes ");
			return depNames;
		} catch (Exception e) {
			l.error("erreur methode getAllDepartementsNamesByEntreprise : " +e);
			return null;
		}
	}

	@Transactional
	public int deleteEntrepriseById(int entrepriseId) {
		l.debug("methode deleteEntrepriseById ");
		
		try {
			entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());
			l.debug("deleteEntrepriseById fini avec succes ");
			return 0;
		} catch (Exception e) {
			l.error("erreur methode deleteEntrepriseById : " +e);
			return -1;
		}		
	}

	public Entreprise getEntrepriseById(int entrepriseId) {
		l.debug("methode getEntrepriseById ");
		
		
		try {
			Entreprise et= entrepriseRepoistory.findById(entrepriseId).get();
			l.debug("getEntrepriseById fini avec succes ");
			return et;
		} catch (Exception e) {
			l.error("erreur methode getEntrepriseById : " +e);
			return null;
		}	
		
		
	}

}
