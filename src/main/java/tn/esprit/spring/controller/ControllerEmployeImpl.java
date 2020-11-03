package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;


import javax.faces.context.FacesContext;


import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.services.IContratService;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.ITimesheetService;


@Scope(value = "session")
@Controller(value = "employeController")
@ELBeanName(value = "employeController")
@Join(path = "/", to = "/login.jsf")
public class ControllerEmployeImpl  {

	@Autowired
	IEmployeService employeService;
	@Autowired
	IContratService contratsService;
	@Autowired
	ITimesheetService itimesheetservice;

	private String login; 
	private String password; 
	private Boolean loggedIn;

	private Employe authenticatedUser = null; 
	private String prenom; 
	private String nom; 
	private String email;
	private boolean actif;
	private Role role;  
	
	
	private String nav="/login.xhtml?faces-redirect=true";
	public Role[] getRoles() { return Role.values(); }

	private List<Employe> employes; 

	private Integer employeIdToBeUpdated; 


	public String doLogout()
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	

	

	return nav;
	}


	public String addEmploye() {

		if (authenticatedUser==null || !loggedIn) return nav;

		employeService.addOrUpdateEmploye(new Employe(nom, prenom, email, password, actif, role)); 
		return "null"; 
	}  

	public String removeEmploye(int employeId) {

		
		if (authenticatedUser==null || !loggedIn) return nav;

		employeService.deleteEmployeById(employeId);
		return null; 
	} 

	public String displayEmploye(Employe empl) 
	{

		if (authenticatedUser==null || !loggedIn) return nav;



		this.setPrenom(empl.getPrenom());
		this.setNom(empl.getNom());
		this.setActif(empl.isActif()); 
		this.setEmail(empl.getEmail());
		this.setRole(empl.getRole());
		this.setPassword(empl.getPassword());
		this.setEmployeIdToBeUpdated(empl.getId());

		return null; 

	} 

	public String updateEmploye() 
	{ 
		
		if (authenticatedUser==null || !loggedIn) return nav;


		Employe e=new Employe( nom, prenom, email, password, actif, role);
		e.setId(employeIdToBeUpdated);
		employeService.addOrUpdateEmploye(e); 

		return null; 

	} 

	public IEmployeService getEmployeService() {
		return employeService;
	}

	public void setEmployeService(IEmployeService employeService) {
		this.employeService = employeService;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public List<Employe> getAllEmployes() {
		return employeService.getAllEmployes();
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public int ajouterEmploye(Employe employe)
	{
		employeService.addOrUpdateEmploye(employe);
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		employeService.mettreAjourEmailByEmployeId(email, employeId);

	}

	public void affecterEmployeADepartement(int employeId, int depId) {
		employeService.affecterEmployeADepartement(employeId, depId);

	}



	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		employeService.desaffecterEmployeDuDepartement(employeId, depId);
	}

	public int ajouterContrat(Contrat contrat) {
		contratsService.ajouterContrat(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId)
	{
		contratsService.affecterContratAEmploye(contratId, employeId);
	}


	public String getEmployePrenomById(int employeId) {
		return employeService.getEmployePrenomById(employeId);
	}

	public void deleteEmployeById(int employeId) {
		employeService.deleteEmployeById(employeId);

	}
	public void deleteContratById(int contratId) {
		contratsService.deleteContratById(contratId);
	}

	public int getNombreEmployeJPQL() {

		return employeService.getNombreEmployeJPQL();
	}

	public List<String> getAllEmployeNamesJPQL() {

		return employeService.getAllEmployeNamesJPQL();
	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeService.getAllEmployeByEntreprise(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {	
		employeService.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}

	public void deleteAllContratJPQL() {
		contratsService.deleteAllContratJPQL();

	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeService.getSalaireByEmployeIdJPQL(employeId);
	}


	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeService.getSalaireMoyenByDepartementId(departementId);
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return itimesheetservice.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}




	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Employe> getEmployes() {
		employes = employeService.getAllEmployes(); 
		return employes;
	}

	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}

	public Integer getEmployeIdToBeUpdated() {
		return employeIdToBeUpdated;
	}

	public void setEmployeIdToBeUpdated(Integer employeIdToBeUpdated) {
		this.employeIdToBeUpdated = employeIdToBeUpdated;
	}

	public Employe getAuthenticatedUser() {
		return authenticatedUser;
	}

	public void setAuthenticatedUser(Employe authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}

}
