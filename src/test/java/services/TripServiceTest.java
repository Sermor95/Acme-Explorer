
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
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TripServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private TripService	tripService;


	//Setting up the authority to execute services.
	@Test
	public void testCreateTrip() {
		//Setting up the authority to execute services.
		this.authenticate("manager1");

		//Using create() to initialise a new entity.
		final Trip trip = this.tripService.create();

		trip.setTitle("Title");
		trip.setDescription("Description");
		trip.setRequirements("Requirement1");
		final Date publicationDate = new GregorianCalendar(2019, Calendar.FEBRUARY, 11).getTime();
		trip.setPublicationDate(publicationDate);
		final Date startDate = new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime();
		trip.setStartDate(startDate);
		final Date endDate = new GregorianCalendar(2021, Calendar.FEBRUARY, 11).getTime();
		trip.setEndDate(endDate);

		//Saving entity to database and confirming it exists with findAll().
		final Trip saved = this.tripService.save(trip);
		final Trip bbdd = this.tripService.findOne(saved.getId());
		Assert.notNull(bbdd);
	}

	@Test
	public void testListDeleteTrip() {
		//Setting up the authority to execute services.
		this.authenticate("manager1");

		//We retrieve a list of all notes, and obtain the Id of one of them.
		final Collection<Trip> trips = this.tripService.findAll();
		final int id = trips.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final Trip trip = this.tripService.findOne(id);
		Assert.notNull(id);

		//Using delete() to delete the entity we retrieved.
		this.tripService.delete(trip);

		//Verifying the entity has been removed from the database.
		final Trip bbdd = this.tripService.findOne(trip.getId());
		Assert.isNull(bbdd);
	}
}
