
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
import domain.Zolet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ZoletServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private ZoletService	zoletService;

	@Autowired
	private TripService		tripService;


	//Test

	@SuppressWarnings("deprecation")
	@Test
	public void testCreateZolet() {
		// Setting up the authority to execute services.
		this.authenticate("manager1");

		// Using create() to initialise a new entity. Necessary Id's taken from
		// populated database.
		final Zolet zolet = this.zoletService.create();

		zolet.setTitle("asdfa");
		zolet.setDescription("vefwerfwrtb wrtwrtgv wrg");
		zolet.setGauge(1);
		zolet.setTrip(this.tripService.findAll().iterator().next());

		// Saving entity to database and confirming it exists with findAll().
		final Zolet saved = this.zoletService.save(zolet);

		final Zolet z = this.zoletService.findOne(saved.getId());
		Assert.notNull(z);

	}
	@Test
	public void testListDeleteZolet() {
		//Setting up the authority to execute services.
		this.authenticate("manager1");

		//We retrieve a list of all notes, and obtain the Id of one of them.
		Collection<Zolet> zolets = this.zoletService.findAll();
		final int id = zolets.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final Zolet zolet = this.zoletService.findOne(id);
		Assert.notNull(zolet);

		//Using delete() to delete the entity we retrieved.
		this.zoletService.delete(zolet);

		//Verifying the entity has been removed from the database.
		zolets = this.zoletService.findAll();
		Assert.isTrue(!zolets.contains(zolet));
	}
}
