
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StageRepository;
import utilities.AbstractTest;
import domain.Stage;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class StageServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private StageService	stageService;

	@Autowired
	private TripService		tripService;

	@Autowired
	private StageRepository	stageRepository;


	//Tests

	@Test
	public void testCreateStage() {
		//Setting up the authority to execute services.
		this.authenticate("manager1");

		//Using create() to initialise a new entity. Necessary Id's taken from populated database.
		final Stage stage = this.stageService.create();

		stage.setTitle("Stage 1");
		stage.setPrice(20.0);
		stage.setDescription("Description");

		//Saving entity to database and confirming it exists with findAll().
		final Stage saved = this.stageService.save(stage);

		final Collection<Stage> stages = this.stageService.findAll();
		Assert.isTrue(stages.contains(saved));
	}

	@Test
	public void testListDeleteStage() {
		//Setting up the authority to execute services.
		this.authenticate("manager2");

		//We retrieve a list of all stages, and obtain the Id of one of them.
		final Collection<Stage> stages = this.stageService.findAll();
		final int id = stages.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final Stage stage = this.stageService.findOne(id);
		Assert.notNull(stage);

		//Getting the ID of the trip that stage is associated to from the manager of that Trip.
		final Collection<Trip> trips = stage.getManager().getTrip();

		//Using delete() to delete the entity we retrieved from the service and from the trip it belonged.
		//Deleting the Stage from its service from the list of Stages of the trip it belonged and deleting the trip in case of being the last stage it had.
		for (final Trip t : trips)
			if (t.getStage().contains(stage))
				if (t.getStage().size() == 1) {
					this.stageService.delete(stage);
					this.tripService.delete(t);

				} else {
					this.stageService.delete(stage);
					t.getStage().remove(stage); // Not sure if this is needed
					this.tripService.save(t);
				}
		this.stageRepository.flush();
		//Verifying the entity has been removed from the database.
		final Stage stage1 = this.stageService.findOne(stage.getId());
		Assert.notNull(stage1);
	}
}
