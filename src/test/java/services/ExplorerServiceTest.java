
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Explorer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ExplorerServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private ExplorerService	explorerService;


	//Tests

	@Test
	public void testCreateExplorer() {

		//Setting up the authority to execute services.

		//Using create() to initialise a new entity. Necessary Id's taken from populated database.

		final Explorer explorer = this.explorerService.create();

		explorer.setName("Curro");
		explorer.setSurname("Paquito");
		explorer.setEmail("currmaror@alum.us.es");
		explorer.setPhone("654888444");
		explorer.setAddress("C/ Manzanilla 32");
		explorer.getUserAccount().setUsername("CASOL");
		explorer.getUserAccount().setPassword("12345678");

		final Explorer saved = this.explorerService.save(explorer);
		final Explorer bbdd = this.explorerService.findOne(saved.getId());
		Assert.notNull(bbdd);
	}
}
