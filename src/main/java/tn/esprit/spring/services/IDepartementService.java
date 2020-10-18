package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Departement;


public interface IDepartementService {
	
	
	public List<Departement> getAllDepartements();
	public int ajouterDepartement(Departement dep);
	public void affecterDepartementAEntreprise(int depId, int entrepriseId);
	public void deleteDepartementById(int depId);

	
	
	

	
}
