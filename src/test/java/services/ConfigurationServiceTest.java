
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Configuration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ConfigurationServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private ConfigurationService	configurationService;


	//Tests

	@Test
	public void testCreateConfiguration() {
		//Setting up the authority to execute services.
		this.authenticate("admin");

		//Using create() to initialise a new entity. Necessary Id's taken from populated database.
		final Configuration configuration = this.configurationService.create();

		//Saving entity to database and confirming it exists with findAll().
		final Configuration saved = this.configurationService.save(configuration);

		final Collection<Configuration> configurations = this.configurationService.findAll();
		Assert.isTrue(configurations.contains(saved));
	}

	@Test
	public void testListDeleteConfiguration() {
		//Setting up the authority to execute services.
		this.authenticate("admin");

		//We retrieve a list of all notes, and obtain the Id of one of them.
		Collection<Configuration> configurations = this.configurationService.findAll();
		final int id = configurations.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final Configuration configuration = this.configurationService.findOne(id);
		Assert.notNull(configuration);

		//Using delete() to delete the entity we retrieved.
		this.configurationService.delete(configuration);

		//Verifying the entity has been removed from the database.
		configurations = this.configurationService.findAll();
		Assert.isTrue(!configurations.contains(configuration));
	}
}
