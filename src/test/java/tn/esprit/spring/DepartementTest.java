package tn.esprit.spring;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IDepartementService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartementTest {

	@Autowired
	IDepartementService DepService; 
	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;

	
	@Test
	public void testGetAllDepartements()  {
		
		List <Departement> listDep = DepService.getAllDepartements();
		assertNotNull(listDep);
	}
	@Test
	public void testAjouterDepartement(){
		Departement d = new Departement ("IT Dep");
		Departement dAdded = DepService.ajouterDepartement(d);
		assertEquals(d.getName(), dAdded.getName());
	}
	@Test
	public void testAffecterDepartementAEntreprise() {
		int entrepriseId = 1 ;
		int depId = 48;  // +1  update this id  by one each time I  run Junit test 
		Departement Dep = deptRepoistory.findById(depId).get();
		Entreprise Ent = entrepriseRepoistory.findById(entrepriseId).get();
		Dep.setEntreprise(Ent);
		Departement dep = DepService.affecterDepartementAEntreprise(depId, entrepriseId);
		Assert.assertEquals (Dep.getEntreprise().getId(),dep.getEntreprise().getId());
			
	}
	@Test
	public void testDesaffecterDepartementDuEntreprise () {
		int entId = 1 ;
		int depId = 47 ;//+1
	    DepService.desaffecterDepartementDuEntreprise(depId, entId);
	    Departement Dep = deptRepoistory.findById(depId).get();
		assertNull (Dep.getEntreprise());
		
	}
	@Test
	  public void testGetDepartementById(){
		int depId = 48;//+1
		Departement dep= DepService.getDepartmentById(depId);
		
		assertEquals(47, dep.getId());//+1
	}
	
	
	@Test
	public void testDeleteDepartementById(){
		int depId = 46 ;//+1
		int i = DepService.deleteDepartementById(depId);
		assertEquals(0, i);
		
		

	}

}
