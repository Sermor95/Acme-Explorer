
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Manager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ManagerServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private ManagerService	managerService;


	//Tests

	@Test
	public void testCreateListManager() {
		//Using create() to initialise a new entity.
		final Manager manager = this.managerService.create();

		manager.setName("testName");
		manager.setSurname("testSurname");
		manager.setEmail("test@mail.com");
		manager.setPhone("999888777");
		manager.setAddress("c/test, 1");
		manager.getUserAccount().setUsername("Fren");
		manager.getUserAccount().setPassword("12345678");

		final Manager saved = this.managerService.save(manager);
		final Manager bbdd = this.managerService.findOne(saved.getId());
		Assert.notNull(bbdd);
	}

}
