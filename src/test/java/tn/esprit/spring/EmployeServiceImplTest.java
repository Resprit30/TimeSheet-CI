package tn.esprit.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.IEmployeService;

@SpringBootTest(classes = TimesheetApplication.class)
@RunWith(SpringRunner.class)
public class EmployeServiceImplTest {
	@Autowired
	IEmployeService iemployeservice;
	
	@Autowired
    EmployeRepository employerepository;
	
	Integer idE;
	@Test
	public void testAjouterEmploye()  {      
		 idE=iemployeservice.addOrUpdateEmploye(new Employe("louay","ouja", "xxxx","ffdf", true, Role.ADMINISTRATEUR));
		assertNotNull(idE);
	}
	@Test
	public void testDeleteEmployeById()
	{
		if(idE!=null){
		int i = iemployeservice.deleteEmployeById(idE);
		
		assertEquals(0, i);}
		else {
			int i = iemployeservice.deleteEmployeById(6);
			
			assertEquals(0, i);}
	}
	@Test
	public void testGetEmployeById() {
		Employe e =iemployeservice.getEmployeeById(1); 
		assertEquals(1, e.getId());
	}

}
