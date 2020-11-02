package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Departement;


public interface IDepartementService {
	
	
	public List<Departement> getAllDepartements();
	public Departement ajouterDepartement(Departement dep);
	public Departement affecterDepartementAEntreprise(int depId, int entrepriseId);
	public Departement desaffecterDepartementDuEntreprise (int depId , int entId);
	public Departement getDepartmentById(int departmentId);
	public int deleteDepartementById(int depId);


	
	
	

	
}
