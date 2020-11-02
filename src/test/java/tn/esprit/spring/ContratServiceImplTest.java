package tn.esprit.spring;

import java.util.Date;
import java.util.List;


import static org.junit.Assert.*;

import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.services.IContratService;

public class ContratServiceImplTest extends TimesheetApplicationTests{
	@Autowired
	IContratService cs;

	Integer idC=1;
	
	private static final Logger l = LogManager.getLogger(ContratServiceImplTest.class);
	
	@Test
	public void testGetAllContrats(){
		List<Contrat> lc =cs.getAllContrats();
		lc.forEach(e->l.info(e+"\n"));	
	}
	
	@Test
	public void testAjouterContrat() {
		Date date = new Date();
		Contrat c=new Contrat(date,"Stage",2000);
		idC=cs.ajouterContrat(c);
		assertNotNull(idC);
	}
	
	@Test
	public void testDeleteContratById() {
		int i=cs.deleteContratById(idC);
		assertEquals(0,i);
	}
	
}
