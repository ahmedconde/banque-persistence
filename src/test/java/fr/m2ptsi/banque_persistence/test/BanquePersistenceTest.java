package fr.m2ptsi.banque_persistence.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import fr.m2ptsi.banque_persistence.config.HibernateConfig;
import fr.m2ptsi.banque_persistence.exceptions.BanqueException;
import fr.m2ptsi.banque_persistence.models.Type;
import fr.m2ptsi.banque_persistence.models.enums.TypeCompte;
import fr.m2ptsi.banque_persistence.models.services.IBanqueService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HibernateConfig.class)
public class BanquePersistenceTest extends AbstractJUnit4SpringContextTests {
 
	@Autowired
	private IBanqueService service;
	
	@Test
	public void testloadService() {
		Assert.assertNotNull(service);
	}
	// test de sauvegarge d'un type 
	@Test
	public void testSave() throws BanqueException {
		Type t1 = new Type(TypeCompte.PEL, null);
		service.save(t1);
		
		Type t2 = new Type(TypeCompte.COURANT, null);
		service.save(t2);
		
		Type t3 = new Type(TypeCompte.EPARGNE, null);
		service.save(t3);
	}
	//test de création d'une liste de type
	
//	@Test(dependsOnMethods = "testSave")
	@Test
	public void testLoadType() throws BanqueException {
		List<Type> listeTypes = service.allTypes();
		Assert.assertNotNull(listeTypes);
//		Assert.assertEquals(listeTypes.size(), 3);
	}
	
	
}
