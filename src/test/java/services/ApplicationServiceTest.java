
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
import domain.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ApplicationServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private ApplicationService	applicationService;


	//Test

	@Test
	public void testCreateApplication() {
		// Setting up the authority to execute services.
		this.authenticate("explorer1");

		// Using create() to initialise a new entity. Necessary Id's taken from
		// populated database.
		final Application application = this.applicationService.create(2830);//ID del trip1
		// Saving entity to database and confirming it exists with findAll().
		final Application saved = this.applicationService.save(application);

		final Application bbdd = this.applicationService.findOne(saved.getId());
		Assert.notNull(bbdd);
	}

	@Test
	public void testListDeleteApplication() {
		//Setting up the authority to execute services.
		this.authenticate("explorer1");

		//We retrieve a list of all notes, and obtain the Id of one of them.
		Collection<Application> applications = this.applicationService.findAll();
		final int id = applications.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final Application application = this.applicationService.findOne(id);
		Assert.notNull(application);

		//Using delete() to delete the entity we retrieved.
		this.applicationService.delete(application);

		//Verifying the entity has been removed from the database.
		applications = this.applicationService.findAll();
		Assert.isTrue(!applications.contains(application));
	}
}
