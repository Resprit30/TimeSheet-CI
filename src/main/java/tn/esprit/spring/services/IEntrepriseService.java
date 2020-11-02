package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Entreprise;

public interface IEntrepriseService {
	
	public Integer ajouterEntreprise(Entreprise entreprise);
	List<String> getAllDepartementsNamesByEntreprise(int entrepriseId);
	public int deleteEntrepriseById(int entrepriseId);
	public Entreprise getEntrepriseById(int entrepriseId);
}
