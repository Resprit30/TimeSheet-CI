package tn.esprit.spring.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.EmployeRepository;

@Service
public class ContratServiceImpl implements IContratService {


	@Autowired
	ContratRepository contratRepository;
	@Autowired
	EmployeRepository employeRepository;

	private static final Logger l = LogManager.getLogger(ContratServiceImpl.class);

	public List<Contrat> getAllContrats() {
		l.info("In getAllContrats() : ");
		
		l.debug("Accéss à la base de données");
		l.debug("Récupération de la liste des contrats");
		
		l.info("Out getAllContrats() ");
		return (List<Contrat>) contratRepository.findAll();
	}

	public Integer ajouterContrat(Contrat contrat) {
		l.debug("In ajouterContrat");
		try{
			contratRepository.save(contrat);
			l.info("Contrat ajouter avec ref = "+contrat.getReference());
			l.debug("Out ajouterContrat");
			return contrat.getReference();
		} catch (Exception e) {
			l.error("erreur dans la methode ajouterContrat :"+e);
			return null;
		}
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		Contrat contratManagedEntity = contratRepository.findById(contratId).orElse(null);
		l.debug("Contrat par identifiant");
		Employe employeManagedEntity = employeRepository.findById(employeId).orElse(null);
		l.debug("Employe par identifiant");
		
		if(contratManagedEntity!=null){
		contratManagedEntity.setEmploye(employeManagedEntity);
		l.info("L'affectation a été faite");
		contratRepository.save(contratManagedEntity);
		}
	}

public int deleteContratById(int contratId) {
    l.debug("In deleteContratById ");
    try {
    	Contrat contratManagedEntity = contratRepository.findById(contratId).get();
    	l.info("Ce contrat a été supprimer avec succes");
		contratRepository.delete(contratManagedEntity);
		return 0;
    }catch (Exception e){
    	l.error("erreur methode deleteContratById :"+e);
    	return -1;
    	
    }

	}
	public void deleteAllContratJPQL() {
		l.debug("In deleteAllContratJPQL ");
		employeRepository.deleteAllContratJPQL();
		l.info("Liste de contrats a été supprimer");
	}

}
