package base;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {

	private static PersonDomainModel per1 = new PersonDomainModel();
	private static PersonDomainModel per2 = new PersonDomainModel();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		per1.setBirthday(new Date(0));
		per1.setCity("Townsend");
		per1.setFirstName("Bert");
		per1.setLastName("Gibbons");
		per1.setPostalCode(19734);
		per1.setStreet("214 Labrador Lane");
		
		per2.setBirthday(new Date(0));
		per2.setCity("Kansas");
		per2.setFirstName("John");
		per2.setLastName("Smith");
		per2.setPostalCode(19091);
		per2.setStreet("O'erTheHill Lane");
		
		
		PersonDAL.addPerson(per1);
		PersonDAL.addPerson(per2);
	}

	@After
	public void tearDown() throws Exception {
		PersonDAL.deletePerson(per1.getPersonID());
		PersonDAL.deletePerson(per2.getPersonID());
	}

	@Test
	public void TestAddPerson() {		
		PersonDomainModel per3 = PersonDAL.getPerson(per1.getPersonID());
		assertEquals(per1.getPersonID(), per3.getPersonID());
	}
	
	@Test
	public void TestGetPersons(){
		UUID wrongNumber;
		do{
			wrongNumber = UUID.randomUUID();			
		}while (wrongNumber.equals(per1.getPersonID()) || wrongNumber.equals(per2.getPersonID()));
		PersonDomainModel per3 = PersonDAL.getPerson(wrongNumber);
		ArrayList<PersonDomainModel> pers = new ArrayList<PersonDomainModel>();
		pers.add(per1);
		pers.add(per2);
		pers.add(per3);
		assertEquals(pers.size(), 3);
		assertTrue(pers.get(0).getPersonID().equals(per1.getPersonID()));
		assertTrue(pers.get(1).getPersonID().equals(per2.getPersonID()));
		assertNull(pers.get(2));
	}
	
	@Test
	public void TestGetPerson1(){
		UUID wrongNumber;
		do{
			wrongNumber = UUID.randomUUID();			
		}while (wrongNumber.equals(per1.getPersonID()) && wrongNumber.equals(per2.getPersonID()));
		PersonDomainModel per3 = PersonDAL.getPerson(wrongNumber);
		assertNull(per3);		
	}
	
	@Test
	public void TestGetPerson2(){
		PersonDomainModel per3 = PersonDAL.getPerson(per1.getPersonID());
		assertEquals(per1.getPersonID(), per3.getPersonID());		
	}
	
	@Test
	public void TestDeletePerson() {
		UUID per1ID = per1.getPersonID();

		PersonDAL.deletePerson(per1.getPersonID());
		assertNull(PersonDAL.getPerson(per1ID));
	}
	
	@Test
	public void TestUpdatePerson() {
		UUID per2ID = per2.getPersonID();
		per2.setBirthday(new Date(0));
		per2.setCity("New York");
		per2.setFirstName("Bob");
		per2.setLastName("Jobs");
		per2.setPostalCode(99999);
		per2.setStreet("2020 Prospect Ave");
		
		PersonDAL.updatePerson(per2);
		assertEquals(per2.getPersonID(), per2ID);
	//Not in Kansas anymore :)
		assertFalse(PersonDAL.getPerson(per2.getPersonID()).getCity().equals("Kansas"));
		assertTrue(PersonDAL.getPerson(per2.getPersonID()).getCity().equals("New York"));
	}
	
	

}
