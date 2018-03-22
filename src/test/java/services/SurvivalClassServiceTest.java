
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Location;
import domain.SurvivalClass;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SurvivalClassServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private SurvivalClassService	survivalClassService;


	//Setting up the authority to execute services.
	@Test
	public void testCreateSurvivalClass() {
		//Setting up the authority to execute services.
		this.authenticate("manager1");

		//Using create() to initialise a new entity. Necessary Id's taken from populated database.
		final SurvivalClass sc = this.survivalClassService.create(3031);

		sc.setTitle("Title");
		sc.setDescription("Description");
		final Location location = new Location();
		location.setName("Sevilla");
		location.setGpsCoordinates("+90, -90");
		sc.setLocation(location);
		final Date moment = new GregorianCalendar(2015, Calendar.FEBRUARY, 11).getTime();
		sc.setMoment(moment);
		//Saving entity to database and confirming it exists with findAll().
		final SurvivalClass saved = this.survivalClassService.save(sc);

		final Collection<SurvivalClass> scList = this.survivalClassService.findAll();
		Assert.isTrue(scList.contains(saved));
	}

	@Test
	public void testListDeleteNote() {
		//Setting up the authority to execute services.
		this.authenticate("manager1");

		//We retrieve a list of all notes, and obtain the Id of one of them.
		Collection<SurvivalClass> scList = this.survivalClassService.findAll();
		final int id = scList.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final SurvivalClass sc = this.survivalClassService.findOne(id);
		Assert.notNull(sc);

		//Using delete() to delete the entity we retrieved.
		this.survivalClassService.delete(sc);

		//Verifying the entity has been removed from the database.
		scList = this.survivalClassService.findAll();
		Assert.isTrue(!scList.contains(sc));
	}
}
